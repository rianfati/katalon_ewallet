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



/* First prepare the data */

//var_user_agent = findTestData("Account/DF_login").getValue("user_agent", 1)
//var_user_pin = findTestData("Account/DF_login").getValue("user_pin", 1)
//var_phone_number = findTestData("Account/DF_login").getValue("phone_number", 1)
//var_device_id = findTestData("Account/DF_login").getValue("device_id", 1)
//var_fcm_token = findTestData("Account/DF_login").getValue("fcm_token", 1)
//var_expected_rc = findTestData("Account/DF_login").getValue("expected_rc", 1)

//encrpyt the pin
var_enciphered_pin = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, GlobalVariable.login_access_code)
String model = model
println (model)
String plain
println (GlobalVariable.cicdphonenumber)
String global_phone_number = GlobalVariable.cicdphonenumber
if (model == '0'){
	plain =
	'{'+
	'"user_agent":"'+ var_user_agent + '",' +
	'"phone_number":"'+var_phone_number+ '",'+
	'"pin":"' + var_enciphered_pin +'",'+
	'"device_id":"' + var_device_id +'",'+
	'"fcm_token":"'+ var_fcm_token +
	'"}'
	
}
else 
{
	plain =
	'{'+
	'"user_agent":"'+ GlobalVariable.user_agent + '",' +
	'"phone_number":"'+ GlobalVariable.login_phone_number + '",'+
	'"pin":"' + var_enciphered_pin +'",'+
	'"device_id":"' + GlobalVariable.device_id +'",'+
	'"fcm_token":"'+ GlobalVariable.fcm_token +
	'"}'
}

System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, GlobalVariable.aes_key_org_texo)

//compose standard request
String body = ('{"organisation_id":"'+GlobalVariable.organisation_id+'","data":"' + encipheredData + '","lang":"'+GlobalVariable.lang+'"}')

System.out.println(body)

def request = (RequestObject) findTestObject('Account/obj_loginCustomer')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)

rc_ref = new ReturnCodeWrapper(GlobalVariable.expected_rc).getEnumRC()

System.out.println(GlobalVariable.expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())

rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())

if (rc_ref == ReturnCodes.RC00) {

	decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, GlobalVariable.aes_key_org_texo)
	def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

	System.out.println(decipheredRespDataMap)

	//save the access token as global variable
	GlobalVariable.access_token_customer = decipheredRespDataMap.access_token
}

