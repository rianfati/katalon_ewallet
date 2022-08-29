import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.crypto.Cipher as Cipher
import javax.crypto.spec.IvParameterSpec as IvParameterSpec
import javax.crypto.spec.SecretKeySpec as SecretKeySpec

import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper
import id.texo.ewallet.ReturnCodeWrapper
import id.texo.ewallet.ReturnCodes
import id.texo.ewallet.TexoUtils as TexoUtils
import id.texo.ewallet.secure.SecureUtils as SecureUtils
import internal.GlobalVariable as GlobalVariable


String plain =
'{'+
'"user_agent":"'+ var_user_agent + '",' +
'"chip_id":"'+ var_chip_id + '",'+
'"raspberry_id":"' + var_raspberry_id +'",'+
'"action":"' + var_action +'",'+
'"pixel_size":"'+ var_pixel_size +
'"}'

System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

TestObjectProperty header1 = new TestObjectProperty("partner-token",ConditionType.EQUALS, var_partner_token)
TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
ArrayList defaultHeaders = Arrays.asList(header1, header2)

def request = (RequestObject) findTestObject('E_Wallet/obj_generateQRLaundry')

request.setHttpHeaderProperties(defaultHeaders)
println (GlobalVariable.hostname)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))

def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)
println (body_content)

//get the response as enum
rc_ref = new ReturnCodeWrapper(var_expected_rc).getEnumRC()

System.out.println(var_expected_rc)
System.out.println(rc_ref.getRCName())
System.out.println(rc_ref.getHttpResponse())

//compare the result
result = WS.verifyEqual(respDataMap.response_code, rc_ref.getRCName())

if (result == false) {
	System.out.println(rc_ref.getRCMessage())
	System.out.println(rc_ref.getRCDescription())
} else {

	WS.verifyEqual(response.statusCode, rc_ref.getHttpResponse())

	//deciphered the result if exist/success
	if (rc_ref == ReturnCodes.RC00) {
		decipheredResponseData = SecureUtils.decryptAesEwallet(respDataMap.data, var_aes_key)
		def decipheredRespDataMap = new JsonSlurper().parseText(decipheredResponseData)

		System.out.println(decipheredRespDataMap)
		String dechiperedResponse = new String (decipheredResponseData)
		println (dechiperedResponse)
		
		def abdu = new JsonSlurper().parseText(dechiperedResponse)
		
		def abdu2 = abdu.qr_image
		
		String filename = ('D:\\QR_Laundry\\qr_temp_' + System.currentTimeMillis()) + '.png'
		
		TexoUtils.export(abdu2, filename)
		
		println(abdu2)
		
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>()
		
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L)
		
		def result = TexoUtils.readQRCode(filename, 'UTF-8', hintMap)
		
		println(result)
		
		decipheredResponseDataQR = SecureUtils.decryptAesQR(result, var_aes_key_QR)
		println(decipheredResponseDataQR)

	}
	
}