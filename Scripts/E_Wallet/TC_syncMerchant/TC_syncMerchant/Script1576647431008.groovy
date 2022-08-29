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


//String plain =
//'{'+
//'"page":"' + var_page + '",' +
//'"page_size":"'+ var_page_size + 
//'"date":"' + var_date +
//'"}'
//
//System.out.println(plain)	
//
////encrypt plain data
//encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"page":'+var_page+',"page_size":' + var_page_size + ',"datetime":"'+var_date+'"}')

System.out.println(body)

def request = (RequestObject) findTestObject('E_Wallet/obj_syncMerchant')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)

//rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()
//
System.out.println(var_expected_rc)
//System.out.println(rc_ref.getRCName())
//System.out.println(rc_ref.getHttpResponse())
//
//rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, var_expected_rc)

