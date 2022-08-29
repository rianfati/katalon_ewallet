import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.crypto.Cipher as Cipher
import javax.crypto.spec.IvParameterSpec as IvParameterSpec
import javax.crypto.spec.SecretKeySpec as SecretKeySpec

import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper
import id.texo.ewallet.ReturnCodeWrapper
import id.texo.ewallet.ReturnCodes
import id.texo.ewallet.TexoUtils as TexoUtils
import id.texo.ewallet.secure.SecureUtils as SecureUtils
import internal.GlobalVariable as GlobalVariable

/* First prepare the data */
//encrpyt the pin
int phone_number;
phone_number = ((Math.random() * 1000000000) as int)

int email_number;
email_number = ((Math.random() * 1000) as int)

int name_number;
name_number = ((Math.random() * 1000) as int)
 
var_enciphered_password = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_organisation_password)
var_enciphered_confirmation_password = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_organisation_confirmation_password)

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"email_address":"'+email_address_letter+''+email_address_number+''+email_attribute+'",' +
'"name":"tester '+var_name+'",'+
'"password":"' + var_enciphered_password + '",'+
'"confirmation_password":"' + var_enciphered_confirmation_password +
'"}'

GlobalVariable.partner_token = partner_token

System.out.println(plain)
System.out.println("partner_token" + partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

TestObjectProperty header1 = new TestObjectProperty("partner-token",ConditionType.EQUALS, partner_token)
TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
ArrayList defaultHeaders = Arrays.asList(header1, header2)

def request = (RequestObject) findTestObject('Account/obj_signUpOrganisation')

request.setHttpHeaderProperties(defaultHeaders)
println (GlobalVariable.hostname)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)
println (body_content)

//get the response as enum
rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())

//compare the result
result = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())

if (result == false) {
	System.out.println(rc_ref.getRCMessage())
	System.out.println(rc_ref.getRCDescription())
} else {

	WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())

	//deciphered the result if exist/success
	if (rc_ref == ReturnCodes.RC00) {
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

		System.out.println(decipheredRespDataMap)
		String dechiperedResponse = new String (decipheredResponseData)
		println (dechiperedResponse)
		}
}