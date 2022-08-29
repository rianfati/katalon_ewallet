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
//Rian keren
var_enciphered_pin = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_user_pin)

String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"issuer_code":"'+ var_issuer_code + '",'+
'"account_no_cid":"'+ var_account_no_cid + '",'+
'"authentication":"' + var_enciphered_pin +'",'+
'"customer_email":"'+ var_customer_email + '",'+
'"wallet_ref1":"' + var_wallet_ref1 +'",'+
'"wallet_ref2":"'+ var_wallet_ref2 +'",'+
'"wallet_ref3":"'+ var_wallet_ref3 +'",'+
'"first_name":"'+ var_first_name + '",'+
'"last_name":"'+ var_last_name + '"'+
'}'

System.out.println(plain)
System.out.println("var_customer_token" + var_customer_token)
System.out.println("var_partner_token" + var_partner_token)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+var_organisation_id+'","data":"' + encipheredData + '","lang":"id"}')

System.out.println(body)


//prepare the http request
def request = (RequestObject) findTestObject('E_wallet/Add_SoF_EDC_URL')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

System.out.println("partner-token = " + var_partner_token)
ArrayList listHeader = new ArrayList()
listHeader.add(new TestObjectProperty("partner-token", ConditionType.EQUALS, var_partner_token))
request.setHttpHeaderProperties(listHeader)

System.out.println("using access_token = " + GlobalVariable.access_token_customer)
def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

println (body_content)

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)

rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())
println (request.getRestUrl())

rcIsTrue = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())
httpRespIsTrue = WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())

if (rc_ref.getRCName() == 'RC00'){
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)
	
		System.out.println(decipheredRespDataMap)
}
else {
	System.out.println(rc_ref.getRCName())
}
