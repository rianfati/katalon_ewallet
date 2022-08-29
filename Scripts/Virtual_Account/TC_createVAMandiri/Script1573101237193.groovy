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
'"trx_type_id":"'+ var_trx_type_id + '",' +
'"bank_code":"'+ var_bank_code + '",' +
'"amount":"'+ var_amount + '",' +
'"customer_email":"'+ var_customer_email + '",' +
'"customer_fullname":"'+ var_customer_fullname + '",' +
'"customer_phone_number":"'+ var_customer_phone_number + '",' +
'"description_id":"'+ var_description_id + '",' +
'"description_en":"'+ var_description_en + '",' +
'"va_type":"'+ var_va_type + '",' +
'"expiry_date":"'+ var_expiry_date + '",' +
'"trace_number":"002'+ var_trace_number +
'"}'

System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
        '"data":"' + encipheredData +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('Virtual_Account/obj_createVAMandiri')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

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