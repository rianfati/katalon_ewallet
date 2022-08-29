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

//encrpyt the pin
String chars = "abcdefghijklmnopqrstuvwxyz0123456789"

public static String randomString(String chars, int length) {
  Random rand = new Random();
  StringBuilder sb = new StringBuilder();
  for (int i=0; i<length; i++) {
	sb.append(chars.charAt(rand.nextInt(chars.length())));
  }
  return sb.toString();
}

println randomString(chars, 51)
String plain =
'{'+
'"csrf_token":"'+chars+'",' +
'"db":"sicepat_ticketing",' +
'"login":"admin",'+
'"password":"admin",'+
'"redirect":""}'

System.out.println(plain)

////encrypt plain data
//encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)
//
////compose standard request
//String body = ('{"organisation_id":"1","data":"' + encipheredData + '","lang":"id"}')
//
//System.out.println(body)
//
//def request = (RequestObject) findTestObject('Account/obj_loginCustomer')
//request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
//
//def response = WS.sendRequest(request)
//
////get response
//def body_content = response.responseBodyContent
//def status_code = response.statusCode
//
//def respDataMap = new JsonSlurper().parseText(body_content)
//
//System.out.println(respDataMap)
//
//rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()
//
//System.out.println(var_expected_rc)
//System.out.println(rc_ref.getRCName())
//System.out.println(rc_ref.getHttpResponse())
//
//rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
//httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())
//
//if (rc_ref == ReturnCodes.RC00) {
//
//	decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
//	def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)
//
//	System.out.println(decipheredRespDataMap)
//
//	//save the access token as global variable
//	GlobalVariable.access_token_customer = decipheredRespDataMap.access_token
//}


