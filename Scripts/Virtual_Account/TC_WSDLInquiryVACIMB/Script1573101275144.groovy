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

String inquiryRequest = '<SOAP-ENV:Envelope xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">' +
			'<SOAP-ENV:Body>' +
			  '<m:CIMB3rdParty_InquiryRq xmlns:m="http://CIMB3rdParty/BillPaymentWS">' +
				'<m:InquiryRq>' +
				  '<bil:TransactionID>'+var_TransactionID+'</bil:TransactionID>' +
				  '<bil:ChannelID>'+var_TransactionID+'</bil:ChannelID>' +
				  '<bil:TerminalID>'+var_TerminalID+'</bil:TerminalID>' +
				  '<bil:TransactionDate>'+var_TransactionDate+'</bil:TransactionDate>' +
				  '<bil:CompanyCode>'+var_CompanyCode+'</bil:CompanyCode>' +
				  '<bil:CustomerKey1>'+var_CustomerKey1+'</bil:CustomerKey1>' +
				  '<bil:CustomerKey2>'+var_CustomerKey2+'</bil:CustomerKey2>' +
				  '<bil:CustomerKey3>'+var_CustomerKey3+'</bil:CustomerKey3>' +
				  '<bil:AdditionalData1>'+var_AdditionalData1+'</bil:AdditionalData1>' +
				  '<bil:AdditionalData2>'+var_AdditionalData2+'</bil:AdditionalData2>' +
				  '<bil:AdditionalData3>'+var_AdditionalData3+'</bil:AdditionalData3>' +
				  '<bil:AdditionalData4>'+var_AdditionalData4+'</bil:AdditionalData4>' +
				'</m:InquiryRq>' +
			  '</m:CIMB3rdParty_InquiryRq>' +
			'</SOAP-ENV:Body>' +
			'</SOAP-ENV:Envelope>'
println(inquiryRequest)

//compose standard request

def request = (RequestObject) findTestObject('Virtual_Account/obj_WSDLInquiryVACIMB')

println (GlobalVariable.hostname)
println (request.getRestUrl())

request.setBodyContent(new HttpTextBodyContent(inquiryRequest, "UTF-8", "application/json"))


def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode
println (status_code)
println (body_content)
WS.verifyEqual(status_code, var_sc)
		


