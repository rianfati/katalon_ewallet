import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.RequestObject

//for ( int i=0; i<4; i++){
//GlobalVariable.resi = "03502800011" + i

def request = (RequestObject) findTestObject('binar')
def response = WS.sendRequest(request)

def body_content = response.responseBodyContent
def status_code = response.statusCode
def respDataMap = new JsonSlurper().parseText(body_content)
System.out.println(body_content)

System.out.println(respDataMap)
System.out.println(respDataMap.data[0].email)
result = WS.verifyEqual(respDataMap.data[0].email, 'michael.lawson@reqres.in')
if (result == true){
	System.out.println('BENAR')
}
else {
	System.out.println('SALAH')
}
//System.out.println("xxxxxxxxxxxxxxxxx  "+GlobalVariable.resi)
//def token = respDataMap.token
//System.out.println(token)
//}

//String plain2 = ('{"channel":"sms","token":"'+token+'"}')
//def request2 = (RequestObject) findTestObject('req_otp')
//request2.setBodyContent(new HttpTextBodyContent(plain2, "UTF-8", "application/json"))
//def response2 = WS.sendRequest(request2)
//def body_content2 = response2.responseBodyContent
//def status_code2 = response2.statusCode
//def respDataMap2 = new JsonSlurper().parseText(body_content2)
//System.out.println(respDataMap2)
