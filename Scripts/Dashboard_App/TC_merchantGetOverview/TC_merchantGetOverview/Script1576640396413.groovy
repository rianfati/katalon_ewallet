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

//prepare the http request

def request = (RequestObject) findTestObject('E_Wallet/obj_merchantGetOverview')
String token_type = token_type
println (token_type)

if (token_type == '0'){
	System.out.println("using token_tester")
	ArrayList listQuery = new ArrayList()

	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.token_tester))
	request.setRestParameters(listQuery)
} else if(token_type == '1'){
	System.out.println("using access_token = " + GlobalVariable.access_token_merchant)
	ArrayList listQuery = new ArrayList()

	listQuery.add(new TestObjectProperty("token", ConditionType.EQUALS, GlobalVariable.access_token_merchant))
	request.setRestParameters(listQuery)
}

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

if (rc_ref == ReturnCodes.RC00) {

	decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
	def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

	System.out.println(decipheredRespDataMap)

	//save the access token as global variable
	GlobalVariable.access_token_merchant = decipheredRespDataMap.access_token
}