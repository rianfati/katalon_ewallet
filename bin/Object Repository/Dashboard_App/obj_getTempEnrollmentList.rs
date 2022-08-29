<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_getTempEnrollmentList</name>
   <tag></tag>
   <elementGuidId>1ea79198-3e97-4153-9de3-8a65a9527dce</elementGuidId>
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
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>partner-token</name>
      <type>Main</type>
      <value>${partner-token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/merchantweb/enrolment/enrlist?token=${access_token}&amp;page_number=${var_page_number}&amp;page_size=${var_page_size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.access_token_merchant</defaultValue>
      <description></description>
      <id>94a3b59b-53b3-4a44-bec3-0072f3d7ef28</id>
      <masked>false</masked>
      <name>access_token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_number</defaultValue>
      <description></description>
      <id>2c691fa2-d53a-42c2-8fa7-7dfd54fd6970</id>
      <masked>false</masked>
      <name>var_page_number</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_size</defaultValue>
      <description></description>
      <id>ec9360d6-fef1-4b57-afdf-59ddf5033ff4</id>
      <masked>false</masked>
      <name>var_page_size</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.partner_token</defaultValue>
      <description></description>
      <id>d0159d22-7344-4f1f-bdb1-78d11f5964b2</id>
      <masked>false</masked>
      <name>partner-token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>53777fae-3ab6-41b3-93c1-dfa90bd435e8</id>
      <masked>false</masked>
      <name>hostname</name>
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
