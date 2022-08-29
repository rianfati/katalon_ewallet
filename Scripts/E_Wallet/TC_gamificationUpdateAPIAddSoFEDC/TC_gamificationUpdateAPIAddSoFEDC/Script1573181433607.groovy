import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import id.texo.ewallet.ReturnCodeWrapper
import id.texo.ewallet.ReturnCodes
import id.texo.ewallet.secure.SecureUtils as SecureUtils
import internal.GlobalVariable


/* First prepare the data */

var_enciphered_pin = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_user_pin)

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"issuer_code":"'+ var_issuer_code + '",'+
'"account_no_cid":"'+ var_account_no_cid + '",'+
'"authentication":"' + var_enciphered_pin +'",'+
'"customer_email":"'+ var_customer_email + '",'+
'"wallet_ref1":"' + var_wallet_ref1 +'",'+
'"wallet_ref2":"'+ var_wallet_ref2 +'",'+
'"wallet_ref3":"'+ var_wallet_ref3 +'",'+
'"first_name":"'+ var_first_name + '",'+
'"last_name":"'+ var_last_name + '"'+
'}'

System.out.println(plain)
System.out.println("var_customer_token" + var_customer_token)
System.out.println("var_partner_token" + var_partner_token)

GlobalVariable.edc_id = edc_id
GlobalVariable.trx_type_id = trx_type_id
GlobalVariable.out_id = out_id
GlobalVariable.partner_token = partner_token

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_gamificationUpdateAPIAddSoFEDC')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
if (var_customer_token == 0){
	System.out.println("using partner-token")
	request.setHttpHeaderProperties("partner-token",var_partner_token)
} else {
	System.out.println("using access_token = " + GlobalVariable.access_token_customer)
	ArrayList listQuery = new ArrayList()

	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_customer))
	request.setRestParameters(listQuery)
}

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