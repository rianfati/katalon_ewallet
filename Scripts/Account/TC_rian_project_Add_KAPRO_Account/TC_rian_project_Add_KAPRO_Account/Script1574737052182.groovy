import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper
import id.texo.ewallet.ReturnCodeWrapper as ReturnCodeWrapper
import id.texo.ewallet.ReturnCodes as ReturnCodes
import id.texo.ewallet.secure.SecureUtils as SecureUtils
import internal.GlobalVariable as GlobalVariable

/* First prepare the data */
String body = '{"subscriber": {"password": "123456","middle-name": " ","valid-id-desc": "EMAIL","resident-address": {"specific-address": "NULL ISLAND","region-code": "NCR","coordinates": "0,0","postal-code": "1222","city-code": "MAKATI"},"business-name": "","valid-id": "bondhan@texo.id","account-name": "Qwerty, Qwerty","authorized-mobile": "087881807877","first-name": "Qwerty","last-name": "Qwerty"},"auth": {"password": "1234"},"request-id": "ahflashfahfl3432434wdfdsf"}'

System.out.println(body)

//compose standard request
def request = ((findTestObject('Account/obj_rianProjectAdd_KasproAccount')) as RequestObject)

request.setBodyContent(new HttpTextBodyContent(body, 'UTF-8'))

int a

List<TestObjectProperty> headerProps = request.getHttpHeaderProperties();

for (a = 0; a < headerProps.size(); a++) {
    println(request.getHttpHeaderProperties().get(a).getName() + ": " + request.getHttpHeaderProperties().get(a).getValue())
}

println(request.httpBody)

def response = WS.sendRequest(request)

println(request.getRestUrl())

println(response)

//get response
def body_content = response.responseBodyContent

println(body_content)

def respDataMap = new JsonSlurper().parseText(body_content)

System.out.println(respDataMap)