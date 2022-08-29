import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent
//import com.bni.encryption.BniEncryption as BniEncryption
import javax.crypto.Cipher;
import javax.crypto.Mac;
import java.security.Security;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.io.File as File
import java.io.UnsupportedEncodingException
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonOutput
import groovy.json.JsonSlurper


def request = ((findTestObject('get')) as RequestObject)

//String body = ('')
//println(body)

//request.setBodyContent(new HttpTextBodyContent(body, 'UTF-8', 'application/json'))

	request.setRestUrl('https://api-ewallet.texofilos.net/ewallet/system/getAppVersion?lang=id')
//  request.setRestUrl('http://localhost:8080/api/bni/createbilling')


def res = WS.sendRequest(request)

println (res.responseText)

//WS.verifyEqual(res.statusCode, 200)

def rc = res.responseBodyContent
println (rc)
println (res.statusCode)
def aduh = new JsonSlurper().parseText(rc)

println(aduh.response_code)
//WS.verifyEqual(aduh.response_code, "RC00")
def aduh1 = aduh.data
println (aduh1)
