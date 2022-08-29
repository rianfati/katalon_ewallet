import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import id.texo.ewallet.ReturnCodeWrapper as ReturnCodeWrapper
import id.texo.ewallet.ReturnCodes
import id.texo.ewallet.secure.SecureUtils as SecureUtils
import internal.GlobalVariable



/* First prepare the data */
//encrpyt the pin
int phone_number;
phone_number = ((Math.random() * 1000000000) as int)

int email_number;
email_number = ((Math.random() * 1000) as int)

int name_number;
name_number = ((Math.random() * 1000) as int)

var_enciphered_pin = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_user_pin)
String plain =
'{'+
'"phone_number":"081'+phone_number+'",' +
'"email_address":"omg'+email_number+'@mail.com",' +
'"fullname":"tester '+name_number+'",'+
'"pin":"' + var_enciphered_pin +
'"}'

System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

def request = (RequestObject) findTestObject('Account/obj_signUpMerchant')

println (GlobalVariable.hostname)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)

rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())

rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())
System.out.println(plain)