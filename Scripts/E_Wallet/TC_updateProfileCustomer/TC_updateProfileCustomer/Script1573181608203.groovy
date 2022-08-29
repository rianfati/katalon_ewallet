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
//var_user_agent = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("user_agent", 1)
//var_first_name = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("first_name", 1)
//var_last_name = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("last_name", 1)
//var_place_of_birth = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("place_of_birth", 1)
//var_date_of_birth = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("date_of_birth", 1)
//var_address = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("address", 1)
//var_kabupatenkota_code = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("kabupatenkota_code", 1)
//var_province_code = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("province_code", 1)
//var_postal_code = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("postal_code", 1)
//var_email = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("email", 1)
//var_identity_type = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("identity_type", 1)
//var_identity_number = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("identity_number", 1)
//var_identity_photo = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("identity_photo", 1)
//var_tax_number_npwp = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("tax_number_npwp", 1)
//var_organisation_id = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("organisation_id", 1)
//var_lang = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("lang", 1)
//var_expected_rc = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("expected_rc", 1)
//var_customer_token = findTestData("E_Wallet/DF_updateProfileCustomer/DF_updateProfileCustomer").getValue("customer_token", 1)
//var_partner_token = findTestData("Token/partnertoken").getValue("partner_token", 1)


String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"first_name":"'+ var_first_name + '",'+
'"last_name":"' + var_last_name +'",'+
'"place_of_birth":"' + var_place_of_birth +'",'+
'"date_of_birth":"' + var_date_of_birth +'",'+
'"address":"' + var_address +'",'+
'"kabupatenkota_code":"' + var_kabupatenkota_code +'",'+
'"province_code":"' + var_province_code +'",'+
'"postal_code":"' + var_postal_code +'",'+
'"email":"' + var_email +'",'+
'"identity_type":"' + var_identity_type +'",'+
'"identity_number":"'+ var_identity_number +'",'+
'"identity_photo":"' + var_identity_photo +'",'+
'"tax_number_npwp":"'+ var_tax_number_npwp +
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
def request = (RequestObject) findTestObject('E_Wallet/obj_updateProfileCustomer')
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