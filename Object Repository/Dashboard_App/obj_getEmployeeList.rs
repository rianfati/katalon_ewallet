<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_getEmployeeList</name>
   <tag></tag>
   <elementGuidId>d827c96e-2f28-4618-844a-2ca702eade13</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
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
      <value>${partner_token}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/merchantweb/employee/getEmployeeList?page_number=${page_number}&amp;page_size=${page_size}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>31dc3111-e00b-4100-bb9f-501c423787df</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.partner_token</defaultValue>
      <description></description>
      <id>390d36e4-19fa-49f7-a3b9-a2cb1cd6f43e</id>
      <masked>false</masked>
      <name>partner_token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_number</defaultValue>
      <description></description>
      <id>29fc4c6c-4f9e-4e5b-b744-645f657de840</id>
      <masked>false</masked>
      <name>page_number</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.page_size</defaultValue>
      <description></description>
      <id>40814cd6-fe8c-4b23-8337-188ec659dcfe</id>
      <masked>false</masked>
      <name>page_size</name>
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
