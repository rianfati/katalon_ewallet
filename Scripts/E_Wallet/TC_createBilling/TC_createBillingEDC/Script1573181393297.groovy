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

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"trx_type_id":"'+ var_trx_type_id + '",'+
//'"from":"' + var_from +'",'+
//'"from_id":"' + var_from_id +'",'+
'"to":"' + var_to +'",'+
'"to_id":"' + var_to_id +'",'+
'"amount":"' + var_amount +'",'+
'"wallet_id":"' + var_wallet_id +'",'+
'"billing_ref1":"' + var_billing_ref1 +'",'+
'"billing_ref2":"' + var_billing_ref2 +'",'+
'"billing_ref3":"' + var_billing_ref3 +'",'+
'"trace_number":"' + var_trace_number +'",'+
'"qr_unique_code":"'+ var_unique_code +
'"}'

System.out.println(plain)
System.out.println("var_customer_token" + var_customer_token)
System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"1","data":"' + encipheredData + '","lang":"id"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_createBillingEDC')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
println (var_customer_token)
if (var_customer_token == '0'){
	System.out.println("using partner-token")
	ArrayList listHeader = new ArrayList()
	listHeader.add(new TestObjectProperty("partner-token", ConditionType.EQUALS,var_partner_token))
	request.setHttpHeaderProperties(listHeader)
	println (var_partner_token)
} else {
	System.out.println("using access_token = " + GlobalVariable.access_token_customer)
	ArrayList listQuery = new ArrayList()

	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_customer))
	request.setRestParameters(listQuery)
}

println (request.getRestUrl())
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