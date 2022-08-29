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

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"app_version_id":"'+ var_app_version_id + '",'+
'"min_edc_app_version":"'+ var_min_edc_app_version + '",'+
'"latest_edc_app_version":"'+ var_latest_edc_app_version + '",'+
'"min_web_app_version":"'+ var_min_web_app_version + '",'+
'"latest_web_app_version":"'+ var_latest_web_app_version + '",'+
'"min_android_app_version":"'+ var_min_android_app_version + '",'+
'"latest_android_app_version":"'+ var_latest_android_app_version + '",'+
'"min_ios_app_version":"'+ var_min_ios_app_version + '",'+
'"latest_ios_app_version":"'+ var_latest_ios_app_version + '",'+
'"edc_code_version":"'+ var_edc_code_version + '",'+
'"web_code_version":"'+ var_web_code_versionn + '",'+
'"android_code_version":"'+ var_android_code_version + '",'+
'"ios_code_version":"'+ var_ios_code_version + '",'+
'"maintenance_status":"'+ var_maintenance_status + '",'+
'"updated_datetime":"'+ var_updated_datetime +
'"}'

System.out.println(plain)
System.out.println("var_customer_token" + var_customer_token)
System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_updateAppVersion')
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
	}
}