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
System.out.println("var_partner_token" + var_partner_token)

String plain = '{"user_agent": "'+var_user_agent+'", "data_rules": {"filter": {"billing_status":"'+var_billing_status+'", "issuer_id":"'+var_issuer_id+'", "start_date":"2019-07-29","end_date":"2019-07-30"}, "search": {"billing_id": "5573"}, "sort": ["amount"]}}'

GlobalVariable.page_number = var_page_number
GlobalVariable.page_size = var_page_size

System.out.println(plain)

/* view string query */
println (GlobalVariable.page_number)
println (GlobalVariable.page_size)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+var_organisation_id+'","data":"' + encipheredData + '","lang":"'+var_lang+'"}')

System.out.println(body)

def request = (RequestObject) findTestObject('Dashboard_App/obj_getBilling')

println (GlobalVariable.hostname)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))


def response = WS.sendRequest(request)

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
