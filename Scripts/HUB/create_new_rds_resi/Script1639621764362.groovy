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


random = ((Math.random() * 1000000000) as int)
println random
String resi_random = "035" + random
GlobalVariable.resi = ('"'+resi_random+'"')
println GlobalVariable.resi
def request = (RequestObject) findTestObject('new_rds_endpoint')
def response = WS.sendRequest(request)

def body_content = response.responseBodyContent
def status_code = response.statusCode
def respDataMap = new JsonSlurper().parseText(body_content)
System.out.println(respDataMap)
System.out.println("xxxxxxxxxxxxxxxxx  "+GlobalVariable.resi)

//for ( int i=1; i<100; i++){
//GlobalVariable.resi = "03502600001" + i
//def request = (RequestObject) findTestObject('new_rds_endpoint')
//def response = WS.sendRequest(request)
//def body_content = response.responseBodyContent
//def status_code = response.statusCode
//def respDataMap = new JsonSlurper().parseText(body_content)
//System.out.println(respDataMap)
//System.out.println("xxxxxxxxxxxxxxxxx  "+GlobalVariable.resi)
//}
