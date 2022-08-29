<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_getListUnsettledTransaction</name>
   <tag></tag>
   <elementGuidId>aea5a69f-f492-4a9d-beb0-c9d611a3ae63</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/merchantweb/settlement/unsettled_transactions?token=${access_token}&amp;page_number=${var_pagenumber}&amp;page_size=${var_pagesize}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>10702fbd-f5ef-4212-8df9-223ceba73a28</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.access_token_merchant</defaultValue>
      <description></description>
      <id>c9fe2759-2ac9-473c-a318-b41964353c4a</id>
      <masked>false</masked>
      <name>access_token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_size</defaultValue>
      <description></description>
      <id>0ab6a344-9984-4b72-ae7f-eed93a553a28</id>
      <masked>false</masked>
      <name>var_pagesize</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_number</defaultValue>
      <description></description>
      <id>6ed00a7a-43ba-4607-a2bf-b407a1f89aa7</id>
      <masked>false</masked>
      <name>var_pagenumber</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
