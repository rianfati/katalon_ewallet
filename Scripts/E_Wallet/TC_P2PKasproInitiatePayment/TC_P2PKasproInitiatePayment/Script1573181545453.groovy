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
//var_user_agent = findTestData("null").getValue("user_agent", 1)
//var_from = findTestData("null").getValue("from", 1)
//var_from_account_number = findTestData("null").getValue("from_account_number", 1)
//var_to = findTestData("null").getValue("to", 1)
//var_to_account_number = findTestData("null").getValue("to_account_number", 1)
//var_amount = findTestData("null").getValue("amount", 1)
//var_description = findTestData("null").getValue("description", 1)
//var_trace_number = findTestData("null").getValue("trace_number", 1)
//var_organisation_id = findTestData("null").getValue("organisation_id", 1)
//var_lang = findTestData("null").getValue("lang", 1)
//var_expected_rc = findTestData("null").getValue("expected_rc", 1)
//var_customer_token = findTestData("null").getValue("customer_token", 1)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 1)

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"from":"'+ var_from + '",'+
'"from_account_number":"' + var_from_account_number +'",'+
'"to":"' + var_to +'",'+
'"to_account_number":"' + var_to_account_number +'",'+
'"amount":"' + var_amount +'",'+
'"description":"' + var_description +'",'+
'"trace_number":"' + var_trace_number +
'"}'

System.out.println(plain)
//System.out.println("var_customer_token" + var_customer_token)
//System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_P2PKasproInitiatePayment')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

//if (var_customer_token == 0){
//	System.out.println("using partner-token")
//	request.setHttpHeaderProperties("partner-token",var_partner_token)
//} else {
//	System.out.println("using access_token = " + GlobalVariable.access_token_customer)
//	ArrayList listQuery = new ArrayList()
//
//	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_customer))
//	request.setRestParameters(listQuery)
//}

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