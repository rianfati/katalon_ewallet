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
//var_user_agent = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("user_agent", 1)
//var_data_rules = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("data_rules", 1)
//var_filter = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("filter", 1)
//var_org_id = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("org_id", 1)
//var_settlement_id = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("settlement_id", 1)
//var_settlement_date = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("settlement_date", 1)
//var_approval_status = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("approval_status", 1)
//var_approval_date = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("approval_date", 1)
//var_search = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("search", 1)
//var_merchant_name = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("merchant_name", 1)
//var_organisation_id = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("organisation_id", 1)
//var_lang = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("lang", 1)
//var_expected_rc = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("expected_rc", 1)
//var_customer_token = findTestData("Dashboard_App/DF_getSettlementHistory/DF_getSettlementHistory").getValue("customer_token", 1)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 1)

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"task_id":"'+ var_task_id + '",' +
'"sales_id":"' + var_sales_id +'",'+
'"status":"'+ var_status + '",' +
'"task_start_date":"'+ var_task_start_date + '",'+
'"task_end_date":"' + var_task_end_date +'",'+
'"description":"'+ var_description + '",' +
'"outlet_id":"'+ var_outlet_id +
'"}'

GlobalVariable.partner_token = partner_token

System.out.println(plain)
System.out.println("var_merchant_token" + var_merchant_token)
System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('Dashboard_App/obj_updateSalesTask')

println (GlobalVariable.hostname)
println (GlobalVariable.access_token_merchant)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

if (var_merchant_token == 0){
	System.out.println("using partner-token")
	request.setHttpHeaderProperties("partner-token",var_partner_token)
} else {
	System.out.println("using access_token = " + GlobalVariable.access_token_merchant)
	ArrayList listQuery = new ArrayList()

	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_merchant))
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

//	//deciphered the result if exist/success
	if (rc_ref == ReturnCodes.RC00) {
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

		System.out.println(decipheredRespDataMap)
		String dechiperedResponse = new String (decipheredResponseData)
		println (dechiperedResponse)
	}
}