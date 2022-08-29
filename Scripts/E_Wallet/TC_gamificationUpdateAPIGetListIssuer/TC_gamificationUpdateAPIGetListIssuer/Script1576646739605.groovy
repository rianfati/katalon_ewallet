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
//var_expected_rc = findTestData("null").getValue("expected_rc", 1)
//var_customer_token = findTestData("null").getValue("customer_token", 1)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 1)


System.out.println("var_customer_token" + var_customer_token)
System.out.println("var_partner_token" + var_partner_token)
GlobalVariable.edc_id = edc_id
GlobalVariable.trx_type_id = trx_type_id
GlobalVariable.out_id = out_id
//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_gamificationUpdateAPIGetListIssuer')

if (var_customer_token == '0'){
	System.out.println("using access_token = " + GlobalVariable.access_token_customer)
	ArrayList listQuery = new ArrayList()
	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_customer))
	request.setRestParameters(listQuery)
} else {
	System.out.println("using access_token = " + var_token)
	ArrayList listQuery = new ArrayList()
	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, var_token))
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

	//deciphered the result if exist/success
	if (rc_ref == ReturnCodes.RC00) {
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

		System.out.println(decipheredRespDataMap)
		String dechiperedResponse = new String (decipheredResponseData)
		println (dechiperedResponse)
	}
}