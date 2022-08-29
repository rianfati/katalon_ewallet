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
//var_user_agent = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("user_agent", 1)
//var_trx_type_id = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("trx_type_id", 1)
//var_to = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("to", 1)
//var_to_id = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("to_id", 1)
//var_amount = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("amount", 1)
//var_wallet_id = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("wallet_id", 1)
//var_billing_ref1 = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("billing_ref1", 1)
//var_billing_ref2 = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("billing_ref2", 1)
//var_billing_ref3 = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("billing_ref3", 1)
//var_trace_number = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("trace_number", 1)
//var_need_verify = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("need_verify", 1)
//var_phone_number = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("phone_number", 1)
//var_unique_code = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("qr_unique_code", 1)
//var_expected_rc = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("expected_rc", 1)
//var_customer_token = findTestData("E_Wallet/DF_createBilling/DF_createBillingCustomer").getValue("customer_token", 1)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 1)
int trace_number;
trace_number = ((Math.random() * 1000000000) as int)

//String plain = ('{"trx_type_id":"'+var_trx_type_id+'","user_agent":"'+var_user_agent+'","to":"'+var_to+'","to_id":"'+var_to_id+'","amount":"'+var_amount+'","wallet_id":"'+var_wallet_id+'","billing_ref1":"'+var_billing_ref1+'","billing_ref2":"'+var_billing_ref2+'","billing_ref3":"'+var_billing_ref3+'","trace_number":"TN'+var_trace_number+'","qr_unique_code":"'+var_unique_code+'"}')
GlobalVariable.partner_token = var_partner_token
String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"user_agent":"'+ var_user_agent2 + '",' +
'"trx_type_id":"'+ var_trx_type_id + '",'+
'"to":"' + var_to +'",'+
'"to_id":"' + var_to_id +'",'+
'"amount":"' + var_amount +'",'+
'"wallet_id":"' + var_wallet_id +'",'+
'"billing_ref1":"' + var_billing_ref1 +'",'+
'"billing_ref2":"' + var_billing_ref2 +'",'+
'"billing_ref3":"' + var_billing_ref3 +'",'+
'"trace_number":"' + var_trace_number +'",'+
'"need_verify":"' + var_need_verify +'",'+
'"phone_number":"' + var_phone_number +'",'+
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
def request
if(obj_model == '1'){ 
request = (RequestObject) findTestObject('E_Wallet/obj_createBillingCustomer')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
}
else{
	request = (RequestObject) findTestObject('E_Wallet/obj_createBillingCustomer2')
	request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
}

if (var_customer_token == '0'){
	GlobalVariable.access_token_customer= ""

	System.out.println("using partner-token")
	request.setHttpHeaderProperties("partner-token",var_partner_token)

	ArrayList listQuery = new ArrayList()

	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, var_partner_token))
	request.setRestParameters(listQuery)

} else {
	System.out.println("using access_token = " + GlobalVariable.access_token_customer)
	System.out.println(request.getRestUrl())
}


def response = WS.sendRequest(request)
println (GlobalVariable.partner_token)
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