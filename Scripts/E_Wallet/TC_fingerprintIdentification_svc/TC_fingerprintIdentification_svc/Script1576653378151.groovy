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

//var_enciphered_pin = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_user_pin)
//println (var_enciphered_pin)

File file = new File("./resources/rakhmat/"+var_image+"")
println(file)
FileInputStream fis = new FileInputStream(file);
byte[] imgBytes = new byte[(int) file.length()];
fis.read(imgBytes);
String basse64 = java.util.Base64.getEncoder().encodeToString(imgBytes)
println(basse64)

String plain ='{"fingerprint":"' + basse64+ '"}'

System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"request_id":"'+var_request_id+'","data":"' + encipheredData + '"}')

System.out.println(body)

def request = (RequestObject) findTestObject('E_Wallet/obj_fingerprintIdentification_svc')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

//def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(body_content)

//rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()
//
System.out.println(var_expected_rc)
//System.out.println(rc_ref.getRCName())
//System.out.println(rc_ref.getHttpResponse())
//
//rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, var_expected_rc)

