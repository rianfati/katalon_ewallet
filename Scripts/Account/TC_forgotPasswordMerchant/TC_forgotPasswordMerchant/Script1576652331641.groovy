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
'"email":"'+ username + '",' +
'"user_agent":"' + user_agent +
'"}'

System.out.println(plain)

//encrypt plain data  
encipheredData = SecureUtils.encryptAesEwallet(plain, aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ lang +'"}')

System.out.println(body)

def request = (RequestObject) findTestObject('Account/obj_forgotPasswordMerchant')

println (GlobalVariable.hostname)
println (request.getRestUrl())

TestObjectProperty header1 = new TestObjectProperty("partner-token",ConditionType.EQUALS, var_partner_token)
TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
ArrayList defaultHeaders = Arrays.asList(header1, header2)
request.setHttpHeaderProperties(defaultHeaders)
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)
println (body_content)

rc_ref = new ReturnCodeWrapper(expected_rc).getEnumRC()

System.out.println(expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())

rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())
