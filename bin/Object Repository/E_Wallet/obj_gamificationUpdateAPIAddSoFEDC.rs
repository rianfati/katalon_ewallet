<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_gamificationUpdateAPIAddSoFEDC</name>
   <tag></tag>
   <elementGuidId>a0036c12-7745-42b3-87ba-486fc8d91b55</elementGuidId>
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
      <name>partner-token</name>
      <type>Main</type>
      <value>${partner_token}</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>edc-info</name>
      <type>Main</type>
      <value>${edc_id}||${trx_type_id}||${out_id}</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/ewallet/sof/addSoF/registrationEDC</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>7fc4f036-cea8-41ea-92ab-118b0a89f01e</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.partner_token</defaultValue>
      <description></description>
      <id>b99a4419-e5b0-46b9-8afa-6980e30fe7e9</id>
      <masked>false</masked>
      <name>partner_token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.edc_id</defaultValue>
      <description></description>
      <id>c7bcaf7b-80aa-4f9c-8458-2b62863f8a31</id>
      <masked>false</masked>
      <name>edc_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.trx_type_id</defaultValue>
      <description></description>
      <id>a3ced9e4-64cb-4e90-a640-1e25baaf0cbd</id>
      <masked>false</masked>
      <name>trx_type_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.out_id</defaultValue>
      <description></description>
      <id>726293be-fef1-4855-9a27-683baefd4eca</id>
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
