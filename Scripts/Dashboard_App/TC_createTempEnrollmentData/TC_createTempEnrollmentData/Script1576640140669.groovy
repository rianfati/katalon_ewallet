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
//String plain = ('{ "user_agent": "'+var_user_agent+'", "merchant_name": "'+var_merchant_name+'", "merchant_type": "'+var_merchant_type+'", "merchant_category": "'+var_merchant_category+'", "category": "'+var_category+'", "address": "'+var_address+'", "kabupatenkota_code": "'+var_kabupatenkota_code+'", "representative_email": "'+var_representative_email+'", "representative_phone": "'+var_representative_phone+'", "representative_firstname": "'+var_representative_firstname+'", "representative_lastname": "'+var_representative_lastname+'", "postal_code": "'+var_postal_code+'", "bank_id": "'+var_bank_id+'", "account_number": "'+var_account_number+'", "account_holder_name": "'+var_account_holder_name+'", "package_total_price": "'+var_package_total_price+'", "outlets": [{ "outlet_name": "'+var_address_outlet+'", "address": "'+var_address_outlet+'", "kabupatenkota_code": "'+var_kabupatenkota_code_outlet+'", "province_code": "'+var_province_code_outlet+'", "postal_code": "'+var_postal_code_outlet+'", "packages": '+var_packages+', "pic_firstname": "'+var_pic_firstname+'", "pic_lastname": "'+var_pic_lastname+'", "pic_email": "'+var_pic_email+'", "pic_phone_number": "'+var_pic_phone_number+'" }, { "outlet_name": "'+var_outlet_name2+'", "address": "'+var_address_outlet2+'", "kabupatenkota_code": "'+var_kabupatenkota_code_outlet2+'", "province_code": "'+var_province_code_outlet2+'", "postal_code": "'+var_postal_code_outlet2+'", "packages": '+var_packages2+', "pic_firstname": "'+var_pic_firstname_outlet2+'", "pic_lastname": "'+var_pic_lastname2+'", "pic_email": "'+pic_email2+'", "pic_phone_number": "'+var_pic_phone_number2+'" } ], "reference": "'+var_reference+'" }')
String backup_token = GlobalVariable.access_token_merchant
String data_model = var_data_model
String plain
if (data_model == '1'){
plain = ('{ "user_agent": "'+var_user_agent+'", "enrolment_id":"'+var_enrolment_id+'", "merchant_name": "'+var_merchant_name+'", "merchant_type": "'+var_merchant_type+'", "merchant_category_id": "'+var_merchant_category+'", "category": "'+var_category+'", "address": "'+var_address+'", "province_code":"'+var_province_code+'","kabupatenkota_code": "'+var_kabupatenkota_code+'", "representative_email": "'+var_representative_email+'", "representative_phone": "'+var_representative_phone+'", "representative_firstname": "'+var_representative_firstname+'", "representative_lastname": "'+var_representative_lastname+'", "postal_code": "'+var_postal_code+'", "bank_id": "'+var_bank_id+'", "account_number": "'+var_account_number+'", "account_holder_name": "'+var_account_holder_name+'", "package_total_price": "'+var_package_total_price+'", "outlets": [{ "outlet_id":"'+var_outlet_id+'", "outlet_name": "'+var_outlet_name+'", "address": "'+var_address_outlet+'", "kabupatenkota_code": "'+var_kabupatenkota_code_outlet+'", "province_code": "'+var_province_code_outlet+'", "postal_code": "'+var_postal_code_outlet+'", "subscriptions": '+subscriptions+', "pic_firstname": "'+var_pic_firstname+'", "pic_lastname": "'+var_pic_lastname+'", "pic_email": "'+var_pic_email+'", "pic_phone_number": "'+var_pic_phone_number+'" }], "reference": "'+var_reference+'" }')	
}
else if (data_model =='2'){
plain = ('{ "user_agent": "'+var_user_agent+'", "enrolment_id":"'+var_enrolment_id+'", "merchant_name": "'+var_merchant_name+'", "merchant_type": "'+var_merchant_type+'", "merchant_category_id": "'+var_merchant_category+'", "category": "'+var_category+'", "address": "'+var_address+'", "kabupatenkota_code": "'+var_kabupatenkota_code+'", "representative_email": "'+var_representative_email+'", "representative_phone": "'+var_representative_phone+'", "representative_firstname": "'+var_representative_firstname+'", "representative_lastname": "'+var_representative_lastname+'", "postal_code": "'+var_postal_code+'", "bank_id": "'+var_bank_id+'", "account_number": "'+var_account_number+'", "account_holder_name": "'+var_account_holder_name+'", "package_total_price": "'+var_package_total_price+'", "outlets": [{ "outlet_id":"'+var_outlet_id+'", "outlet_name": "'+var_outlet_name+'", "address": "'+var_address_outlet+'", "kabupatenkota_code": "'+var_kabupatenkota_code_outlet+'", "province_code": "'+var_province_code_outlet+'", "postal_code": "'+var_postal_code_outlet+'", "subscriptions": '+subscriptions+', "pic_firstname": "'+var_pic_firstname+'", "pic_lastname": "'+var_pic_lastname+'", "pic_email": "'+var_pic_email+'", "pic_phone_number": "'+var_pic_phone_number+'" }, { "outlet_id":"'+var_outlet_id2+'", "outlet_name": "'+var_outlet_name2+'", "address": "'+var_address_outlet2+'", "kabupatenkota_code": "'+var_kabupatenkota_code_outlet2+'", "province_code": "'+var_province_code_outlet2+'", "postal_code": "'+var_postal_code_outlet2+'", "subscriptions": '+subscriptions2+', "pic_firstname": "'+var_pic_firstname_outlet2+'", "pic_lastname": "'+var_pic_lastname2+'", "pic_email": "'+pic_email2+'", "pic_phone_number": "'+var_pic_phone_number2+'" } ], "reference": "'+var_reference+'" }')
}
else {
plain = ('{ "user_agent": "'+var_user_agent+'", "enrolment_id":"'+var_enrolment_id+'"}')
}

GlobalVariable.partner_token = partner_token
System.out.println(GlobalVariable.partner_token)
System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
if (var_merchant_token == '0'){
	System.out.println("token bisa di edit")
	GlobalVariable.access_token_merchant = input_token
	System.out.println("using access_token = " + GlobalVariable.access_token_merchant)
} else {
	System.out.println("using access_token = " + GlobalVariable.access_token_merchant)

}

def request = (RequestObject) findTestObject('Dashboard_App/obj_createTempEnrollmentData')

println (GlobalVariable.hostname)
println (GlobalVariable.access_token_merchant)
println (request.getRestUrl())
println (GlobalVariable.partner_token)

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))


ArrayList listQuery = new ArrayList()
request.setRestParameters(listQuery)
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
System.out.println("var_merchant_token" + GlobalVariable.access_token_merchant)
System.out.println("var_partner_token" + GlobalVariable.partner_token)
System.out.println(GlobalVariable.hostname)
System.out.println (GlobalVariable.access_token_merchant)
System.out.println(request.getRestUrl())

//compare the result
result = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
if (rc_ref.getRCName() != 'RC00') {
	System.out.println(rc_ref.getRCMessage())
	System.out.println(rc_ref.getRCDescription())
} else {
	//deciphered the result if exist/success
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)
		System.out.println(decipheredRespDataMap)
		String dechiperedResponse = new String (decipheredResponseData)
		println (dechiperedResponse)
}
GlobalVariable.access_token_merchant = backup_token