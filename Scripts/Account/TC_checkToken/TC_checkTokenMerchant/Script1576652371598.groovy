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

System.out.println("var_merchant_token" + var_merchant_token)
System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
//encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('Account/obj_checkToken')


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
