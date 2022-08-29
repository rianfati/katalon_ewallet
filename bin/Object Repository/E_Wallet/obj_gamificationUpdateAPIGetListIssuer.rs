<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_gamificationUpdateAPIGetListIssuer</name>
   <tag></tag>
   <elementGuidId>cffb6f47-58a0-4419-b465-64e60eeadc4b</elementGuidId>
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
      <name>edc-info</name>
      <type>Main</type>
      <value>${edc_id}||${trx_type_id}||${out_id}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${hostname}/ewallet/sof/getListIssuer</restUrl>
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
      <defaultValue>GlobalVariable.edc_id</defaultValue>
      <description></description>
      <id>69c43f10-85c3-4490-934a-4d452e5addf7</id>
      <masked>false</masked>
      <name>edc_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.trx_type_id</defaultValue>
      <description></description>
      <id>d9745fd8-8295-48a1-ba79-3b2e1825c240</id>
      <masked>false</masked>
      <name>trx_type_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.out_id</defaultValue>
      <description></description>
      <id>d46c69da-f19f-415f-b7f5-ad9486f857b1</id>
      <masked>false</masked>
      <name>out_id</name>
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
