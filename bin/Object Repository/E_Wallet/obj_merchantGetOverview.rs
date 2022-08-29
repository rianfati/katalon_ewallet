<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_merchantGetOverview</name>
   <tag></tag>
   <elementGuidId>62c84213-b7b6-4418-ae3f-93ad1d9b09ac</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>${hostname}/merchantapp/dashboard/getOverview?lang=${var_lang}&amp;start_date=${var_start_date}&amp;end_date=${var_end_date}&amp;grouping=${var_grouping}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.hostname</defaultValue>
      <description></description>
      <id>ed07222b-c3cf-49fc-a4a4-7137f4a0777a</id>
      <masked>false</masked>
      <name>hostname</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.access_token_merchant</defaultValue>
      <description></description>
      <id>41e35d34-c14f-498a-b5e4-601e01296cc3</id>
      <masked>false</masked>
      <name>access_token</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.lang</defaultValue>
      <description></description>
      <id>2368ab2a-1ba9-4627-8d77-5efbda94c2b9</id>
      <masked>false</masked>
      <name>var_lang</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.start_date</defaultValue>
      <description></description>
      <id>bba3da8e-e88f-42ae-bfe8-17d3b0f35758</id>
      <masked>false</masked>
      <name>var_start_date</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.end_date</defaultValue>
      <description></description>
      <id>062510bf-8ec0-456f-af4c-f80a85ed73b4</id>
      <masked>false</masked>
      <name>var_end_date</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.grouping</defaultValue>
      <description></description>
      <id>ab57ead3-25e6-4609-aaee-a2f32beb8dca</id>
      <masked>false</masked>
      <name>var_grouping</name>
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
