<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_rianProjectAddSofEDC</name>
   <tag></tag>
   <elementGuidId>1235dcf9-0bf1-48d3-ac4b-8dce9bced78f</elementGuidId>
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
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>edc-info</name>
      <type>Main</type>
      <value>${edc_id}||${trx_type_id}||${outlet_id}</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${hostname}/ewallet/sof/addSoF/registrationEDC?token=${token}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.access_token_customer</defaultValue>
      <description></description>
      <id>6cfaae23-5cbc-4bdc-a379-cced130c8108</id>
      <masked>false</masked>
      <name>token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.partner_token</defaultValue>
      <description></description>
      <id>3182a49d-14d6-4838-88de-7488b1a7fbc7</id>
      <masked>false</masked>
      <name>partner-token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>3b8485db-4773-4574-9e61-cbee0a233148</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.edc_id</defaultValue>
      <description></description>
      <id>bcc34651-06b7-4640-8979-a91bbaa09aac</id>
      <masked>false</masked>
      <name>edc_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.trx_type_id</defaultValue>
      <description></description>
      <id>855cf17e-9c32-4705-9dca-35e490a3d0ca</id>
      <masked>false</masked>
      <name>trx_type_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.out_id</defaultValue>
      <description></description>
      <id>957dccce-8be8-4c14-8360-afa8e904f1ce</id>
      <masked>false</masked>
      <name>outlet_id</name>
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
