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

var_enciphered_pin = SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.dev_public_pem_file, var_user_pin)
GlobalVariable.partner_token = var_partner_token
String model = model
String plain
String plain1
if (model == '0'){
	plain ='{"user_agent":"'+var_user_agent+'", "issuer_code":"'+var_issuer_code+'", "account_no_cid":"'+GlobalVariable.cicdphonenumber+'", "authentication":"'+var_enciphered_pin+'","wallet_ref1":"'+var_wallet_ref1+'", "wallet_ref2":"'+var_wallet_ref2+'", "wallet_ref3":"'+var_wallet_ref3+'"}'
	System.out.println(plain)
	}
else if (model == '1'){
	plain ='{"user_agent":"'+var_user_agent+'", "issuer_code":"'+var_issuer_code+'", "account_no_cid":"'+GlobalVariable.cicdphonenumber+'", "authentication":"'+var_enciphered_pin+'", "wallet_ref1":"'+var_wallet_ref1+'", "wallet_ref2":"'+var_wallet_ref2+'", "wallet_ref3":"'+var_wallet_ref3+'"}'
	System.out.println(plain)
	}
else if (model == '3'){
	plain ='{"user_agent":"'+var_user_agent+'", "issuer_code":"'+var_issuer_code+'", "account_no_cid":"'+GlobalVariable.cicdphonenumber+'", "authentication":"'+var_enciphered_pin+'", "wallet_ref1":"'+var_wallet_ref1+'", "wallet_ref2":"'+var_wallet_ref2+'", "wallet_ref3":"'+var_wallet_ref3+'"}'
	System.out.println(plain)
	GlobalVariable.access_token_customer = var_token
	}
else if (model == '4'){
	plain ='{"user_agent":"'+var_user_agent+'", "issuer_code":"'+var_issuer_code+'", "account_no_cid":"'+account_no_cid+'", "authentication":"'+var_enciphered_pin+'", "wallet_ref1":"'+var_wallet_ref1+'", "wallet_ref2":"'+var_wallet_ref2+'", "wallet_ref3":"'+var_wallet_ref3+'"}'
	System.out.println(plain)
	}
else
{
	plain ='{"user_agent":"'+var_user_agent+'", "issuer_code":"'+var_issuer_code+'", "account_no_cid":"'+var_account_no_cid+'", "authentication":"'+var_enciphered_pin+'", "wallet_ref1":"'+var_wallet_ref1+'", "wallet_ref2":"'+var_wallet_ref2+'", "wallet_ref3":"'+var_wallet_ref3+'"}'
	System.out.println(plain)
}

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+var_organisation_id+'","data":"' + encipheredData + '","lang":"'+var_lang+'"}')
System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_addSoFKaspro')
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
//ArrayList listHeader = new ArrayList()
//listHeader.add(new TestObjectProperty("partner-token", ConditionType.EQUALS, var_partner_token))
//request.setHttpHeaderProperties(listHeader)
def response = WS.sendRequest(request)

//get response
System.out.println("partner-token = " + var_partner_token)
System.out.println("var_customer_token" + var_customer_token)
System.out.println("using global access_token = " + GlobalVariable.access_token_customer)
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