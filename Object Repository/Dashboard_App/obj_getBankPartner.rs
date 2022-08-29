<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_getBankPartner</name>
   <tag></tag>
   <elementGuidId>aea94e16-b930-4291-8e04-f4ccaa4826a5</elementGuidId>
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
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/merchantweb/enrolment/pbanklist?token=${access_token}&amp;page_number=${page_number}&amp;page_size=${page_size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>7b8e6088-9e42-4001-8dfb-1f4549ec0462</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_number</defaultValue>
      <description></description>
      <id>0db3d1b2-2783-453a-9c74-76ef60b399a5</id>
      <masked>false</masked>
      <name>page_number</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_size</defaultValue>
      <description></description>
      <id>9831a8ec-821b-4178-926e-e0cc324ca853</id>
      <masked>false</masked>
      <name>page_size</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.access_token_merchant</defaultValue>
      <description></description>
      <id>b0322aa4-f10d-422f-8adc-048ea6a2ab96</id>
      <masked>false</masked>
      <name>access_token</name>
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
