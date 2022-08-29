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
'"billing_id":"'+ var_billing_id + '",'+
'"wallet_id":"' + var_wallet_id +'",'+
'"phone_number":"' + var_phone_number +'",'+
'"authentication":"' + var_enciphered_pin +'",'+
'"promo_code":"'+ var_promo_code +
'"}'
System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+var_organisation_id+'","data":"' + encipheredData + '","lang":"'+var_lang+'"}')

System.out.println(body)

def request = (RequestObject) findTestObject('E_Wallet/obj_gamificationUpdateApiTexoEmoneyPayment1')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
def response = WS.sendRequest(request)
println (GlobalVariable.hostname)
println (request.getRestUrl())

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

println (body_content)

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)

rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())


rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())

if (rc_ref.getRCName() == 'RC00'){
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)
	
		System.out.println(decipheredRespDataMap)
}
else {
	System.out.println(rc_ref.getRCName())
}
