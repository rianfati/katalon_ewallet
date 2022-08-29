<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_Enroll_Fingerprint</name>
   <tag></tag>
   <elementGuidId>bb4795b5-eb84-46f8-91df-15d4038e7cf0</elementGuidId>
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
   <restUrl>${hostname}/ewallet/account/biometric/signupFingerprint</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>44efdce4-7941-4013-8c86-206024c5b177</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.edc_id</defaultValue>
      <description></description>
      <id>0eaa4a71-3e2e-454a-a154-55f1b937f9bf</id>
      <masked>false</masked>
      <name>edc_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.trx_type_id</defaultValue>
      <description></description>
      <id>6ac8ef7e-dc6f-4aea-998a-357d932f35a1</id>
      <masked>false</masked>
      <name>trx_type_id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.out_id</defaultValue>
      <description></description>
      <id>4fdff285-0d4a-48b3-bd53-793a17f8b565</id>
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
