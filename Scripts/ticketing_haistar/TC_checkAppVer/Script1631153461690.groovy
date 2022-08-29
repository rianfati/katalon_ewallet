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


/* Post Request */
GlobalVariable.lang = lang
def request = (RequestObject) findTestObject('obj_ticketing_haistar/obj_checkAppVer')
println (GlobalVariable.hostname)
println (request.getRestUrl())
def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode
println (body_content)
println (status_code)
def respDataMap = new JsonSlurper().parseText(body_content)
System.out.println(respDataMap)
//rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()
//System.out.println(var_expected_rc)



//System.out.println(rc_ref.getRCName())
//System.out.println(rc_ref.getHttpResponse())
//System.out.println(respDataMap.response_code())
//rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
//httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())
//
//if (respDataMap.response_code == 'RC00'){
//		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
//		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)
//		System.out.println(decipheredRespDataMap)
//}
//else {
//	System.out.println(rc_ref.getRCName())
//}
