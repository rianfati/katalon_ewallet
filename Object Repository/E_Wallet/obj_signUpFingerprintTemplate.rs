<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_signUpFingerprintTemplate</name>
   <tag></tag>
   <elementGuidId>41ab7808-9e0e-4c2e-924f-2e2bf9dc237a</elementGuidId>
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
      <name>edc-info</name>
      <type>Main</type>
      <value>${edc_id}||${trx_type_id}||${out_id}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/ewallet/account/biometric/signupFingerprintTemplate</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>05b8d215-8ff1-4249-8b52-e367d0e0365c</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.edc_id</defaultValue>
      <description></description>
      <id>4d9afd3b-619b-4ce2-ab13-6d02832a773e</id>
      <masked>false</masked>
      <name>edc_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.trx_type_id</defaultValue>
      <description></description>
      <id>af347276-4cc1-40ad-b555-11a68271de42</id>
      <masked>false</masked>
      <name>trx_type_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.out_id</defaultValue>
      <description></description>
      <id>3dc7ca4c-de19-440d-b540-b4eb1ad7a821</id>
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
