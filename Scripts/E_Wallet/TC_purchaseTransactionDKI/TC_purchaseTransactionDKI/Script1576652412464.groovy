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
GlobalVariable.partner_token = var_partner_token
println (GlobalVariable.partner_token)
String panpin = '{"pan_5A":"'+var_pan_5A+'", "pin_block":"'+var_pin_block+'"}'
card_data= SecureUtils.doEncryptPinRsaEwallet(GlobalVariable.hsm_pem_file, panpin)

String plain =
'{'+
'"user_agent":"' + var_user_agent + '",' +
'"billing_id":"' + var_billing_id + '",' +
'"card_type":"' + var_card_type +'",'+
'"card_arqc": {"aip_82": "7400","app_cryptogram_9F26": "D34E982C92C26BEE","app_version_number_9F09": "0000","atc_9f36": "01E4","authorized_amount_9F02": "000000005000","cid_9F27": "80","cvm_result_9F34": "A00080","df_name_84": "A0000006021010","ifd_serial_number_9F1E": "3032313637343433","issuer_app_data_9F10": "0101A0008000007AEE76EB00112233445566778899AABBCCDDEEFF0011223344","other_amount_9F03": "000000000000","pan_sequence_number_5F34": "01","terminal_capabilities_9F33": "E0F1C8","terminal_country_code_9F1A": "0360","terminal_type_9F35": "22","transaction_currency_code_5F2A": "0360","transaction_date_9A": "190822","transaction_type_9C": "22","tsc_9f41": "00000000","tvr_95": "0000040000","unpredictable_number_9F37": "4EC04B20"}, '+
'"track_2_57":"' + var_track_2_57 +'",'+
'"track_1_56":"' + var_track_1_56 +'",'+
'"card_data":"' + card_data +'",'+
'"plain_pan":"' + var_plain_pan +'",'+
'"card_holder_name_5F20":"' + var_card_holder_name_5F20 +'",'+
'"icvv":"' + var_icvv +'",'+
'"expiry_date_5F24":"' + var_expiry_date_5F24 +'",'+
'"bank_name":"' + var_bank_name +'",'+
'"card_switcher":"' + var_card_switcher +
'"}'

System.out.println(plain)

//encrypt plain data
encipheredData = SecureUtils.encryptAesEwallet(plain, var_aes_key)

//compose standard request
String body = ('{"organisation_id":"'+ var_organisation_id +'",'+
				'"data":"' + encipheredData +'",'+
				'"lang":"'+ var_lang +'"}')

System.out.println(body)

//prepare the http request
def request = (RequestObject) findTestObject('E_Wallet/obj_purchaseTransactionDKI')

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
	}
}