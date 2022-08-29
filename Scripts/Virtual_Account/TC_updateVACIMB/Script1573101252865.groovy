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
//var_outlet_id = findTestData("Dashboard_App/DF_getHistoryTransaction/DF_getHistoryTransaction").getValue("outlet_id", 1)
//var_transaction_date = findTestData("Dashboard_App/DF_getHistoryTransaction/DF_getHistoryTransaction").getValue("transaction_date", 1)
//var_organisation_id = findTestData("Dashboard_App/DF_getHistoryTransaction/DF_getHistoryTransaction").getValue("organisation_id", 1)
//var_lang = findTestData("Dashboard_App/DF_getHistoryTransaction/DF_getHistoryTransaction").getValue("lang", 1)
//var_expected_rc = findTestData("Dashboard_App/DF_getHistoryTransaction/DF_getHistoryTransaction").getValue("expected_rc", 1)
//var_customer_token = findTestData("Dashboard_App/DF_getHistoryTransaction/DF_getHistoryTransaction").getValue("customer_token", 1)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 1)


String plain =
'{'+
'"va_number":"'+ var_va_number + '",' +
'"amount":"'+ var_amount + '",' +
'"customer_email":"'+ var_customer_email + '",' +
'"customer_fullname":"'+ var_customer_fullname + '",' +
'"customer_phone_number":"'+ var_customer_phone_number + '",' +
'"description_id":"'+ var_description_id + '",' +
'"description_en":"'+ var_description_en + '",' +
'"va_type":"'+ var_va_type + '",' +
'"va_status":"'+ var_va_status + '",' +
'"expiry_date":"'+ var_expiry_date + '",' +
'"trace_number":"'+ var_trace_number +
'"}'

System.out.println(plain)
//System.out.println("var_merchant_token" + var_merchant_token)
//System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('Virtual_Account/obj_updateVACIMB')

println (GlobalVariable.hostname)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

//if (var_merchant_token == 0){
//	System.out.println("using partner-token")
//	request.setHttpHeaderProperties("partner-token",var_partner_token)
//} else {
//	System.out.println("using access_token = " + GlobalVariable.access_token_merchant)
//	ArrayList listQuery = new ArrayList()
//
//	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_merchant))
//	request.setRestParameters(listQuery)
//}

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