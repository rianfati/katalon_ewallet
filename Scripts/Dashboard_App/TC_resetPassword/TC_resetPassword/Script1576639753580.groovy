import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import id.texo.ewallet.ReturnCodeWrapper as ReturnCodeWrapper
import id.texo.ewallet.ReturnCodes
import id.texo.ewallet.secure.SecureUtils as SecureUtils
import internal.GlobalVariable

//encrpyt the pin
var_enciphered_password = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, "Merchant1231!")
//var_enciphered_password1 = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, "Merchant1231!")

String plain =
'{'+
'"user_agent":"1",'+
'"password":"'+ var_enciphered_password + '",' +
'"confirm_password":"'+ var_enciphered_password +
'"}'

System.out.println(plain)

//String decpas = SecureUtils.doDecryptPinRsaEwallet(GlobalVariable.dev_private_pem_file, var_enciphered_password)

//println(decpas)
//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"1",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"en"}')

System.out.println(body)

def request = (RequestObject) findTestObject('Dashboard_App/obj_resetPassword')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

System.out.println(body_content)

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)

rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())

rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())

if (rc_ref == ReturnCodes.RC00) {

	decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
	def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

	System.out.println(decipheredRespDataMap)

}