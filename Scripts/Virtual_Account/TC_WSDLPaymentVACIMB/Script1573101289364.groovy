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
String body =
'<?xml version="1.0"?>'+
'-<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">'+
'-<SOAP-ENV:Body>'+
'-<m:CIMB3rdParty_PaymentRq xmlns:m="http://CIMB3rdParty/BillPaymentWS">'+
'-<m:PaymentRq>'+
'<m:TransactionID>034126504564</m:TransactionID>'+
'<m:ChannelID>NGA</m:ChannelID>'+
'<m:TerminalID>1</m:TerminalID>'+
'<m:TransactionDate>2017022713014200</m:TransactionDate>'+
'<m:CompanyCode>2739</m:CompanyCode>'+
'<m:CustomerKey1>'+CustomerKey1+'</m:CustomerKey1>'+
'<m:CustomerKey2/>'+
'<m:CustomerKey3/>'+
'<m:Language/>'+
'<m:Currency>IDR</m:Currency>'+
'<m:Amount>'+Amount+'</m:Amount>'+
'<m:Fee>0</m:Fee>'+
'<m:PaidAmount>10000</m:PaidAmount>'+
'<m:ReferenceNumberTransaction>170227CL01640077</m:ReferenceNumberTransaction>'+
'<m:FlagPaymentList>10000</m:FlagPaymentList>'+
'<m:CustomerName/>'+
'<m:AdditionalData1>TEST</m:AdditionalData1>'+
'<m:AdditionalData2/>'+
'<m:AdditionalData3/>'+
'<m:AdditionalData4/>'+
'</m:PaymentRq>'+
'</m:CIMB3rdParty_PaymentRq>'+
'</SOAP-ENV:Body>'+
'</SOAP-ENV:Envelope>'
println(body)

//compose standard request
def request = (RequestObject) findTestObject('Virtual_Account/obj_WSDLPaymentVACIMB')
println (GlobalVariable.hostname)
println (request.getRestUrl())
request.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
def response = WS.sendRequest(request)

//get response
def body_content = response.responseBodyContent
def status_code = response.statusCode
println (status_code)
println (body_content)
WS.verifyEqual(status_code, var_sc)