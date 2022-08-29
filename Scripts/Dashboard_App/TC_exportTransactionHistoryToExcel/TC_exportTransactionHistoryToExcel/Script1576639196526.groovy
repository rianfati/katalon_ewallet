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
//var_user_agent = findTestData("Dashboard_App/DF_getOverview/DTF_getOverviewTransactionWeb").getValue("user_agent", 2)
//var_organisation_id = findTestData("Dashboard_App/DF_getOverview/DTF_getOverviewTransactionWeb").getValue("organisation_id", 2)
//var_lang = findTestData("Dashboard_App/DF_getOverview/DTF_getOverviewTransactionWeb").getValue("lang", 2)
//var_expected_rc = findTestData("Dashboard_App/DF_getOverview/DTF_getOverviewTransactionWeb").getValue("expected_rc",2)
//var_merchant_token = findTestData("Dashboard_App/DF_getOverview/DTF_getOverviewTransactionWeb").getValue("merchant_token", 2)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 2)

String plain =
'{'+
'"start_date":"'+ var_start_date + '",' +
'"end_date":"' + var_end_date +'",'+
'"data_type":"'+ var_data_type +
'"}'

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
def request = (RequestObject) findTestObject('E_Wallet/obj_exportTransactionHistoryToExcel')


println (GlobalVariable.hostname)
println (GlobalVariable.access_token_merchant)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

//	System.out.println("using partner-token") 
//	request.setHttpHeaderProperties("partner-token",var_partner_token)
//	System.out.println("using access_token = " + GlobalVariable.access_token_merchant)
//	ArrayList listQuery = new ArrayList()
	
	TestObjectProperty header1 = new TestObjectProperty("partner-token",ConditionType.EQUALS, var_partner_token)
	TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
	ArrayList defaultHeaders = Arrays.asList(header1, header2)
	request.setHttpHeaderProperties(defaultHeaders)

//	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_merchant))
//	request.setRestParameters(listQuery)



def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

//def respDataMap = new JsonSlurper().parseText(body_content)

//System.out.println(respDataMap)
println (body_content)
println (status_code)
//get the response as enum
rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
WS.verifyEqual(status_code, var_expected_rc)
//System.out.println(rc_ref.getRCName())
//System.out.println(rc_ref.getHttpResponse())

//compare the result
//result = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())

//if (result == false) {
//	System.out.println(rc_ref.getRCMessage())
//	System.out.println(rc_ref.getRCDescription())
//} else {
//
//	WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())
//
//	//deciphered the result if exist/success
//	if (rc_ref == ReturnCodes.RC00) {
//		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
//		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)
//
//		System.out.println(decipheredRespDataMap)
//		String dechiperedResponse = new String (decipheredResponseData)
//		println (dechiperedResponse)
//	}
//}