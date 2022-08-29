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

//String resi = GlobalVariable.resi

//String plain =
//'['+
//'"'+resi+'"'+
//']'

//String plain =
//'['+
//'"000092114271",' +
//'"000092114272"'+
//']'

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

String plain =
'['+
''+GlobalVariable.resi+''+
']'

System.out.println(plain)
String body = ('{"reference_numbers":'+ plain +','+
				'"chute_number":"84",'+
				'"sorting_branch_id": 4101}')

def request2 = (RequestObject) findTestObject('hubdansmd/create_gabung_sortir')
request2.setBodyContent(new HttpTextBodyContent(body, "UTF-8", "application/json"))
def response2 = WS.sendRequest(request2)
def slurper = new groovy.json.JsonSlurper()
def result = slurper.parseText(response2.getResponseBodyContent())
def status_code2 = response2.statusCode
println status_code2
println (result)
