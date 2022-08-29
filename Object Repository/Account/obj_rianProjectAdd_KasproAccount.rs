<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>obj_rianProjectAdd_KasproAccount</name>
   <tag></tag>
   <elementGuidId>1444cb53-a52c-416e-8bf8-e60b010d27f4</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\&quot;subscriber\&quot;: {\&quot;password\&quot;: \&quot;121212\&quot;,\&quot;middle-name\&quot;: \&quot; \&quot;,\&quot;valid-id-desc\&quot;: \&quot;EMAIL\&quot;,\&quot;resident-address\&quot;: {\&quot;specific-address\&quot;: \&quot;NULL ISLAND\&quot;,\&quot;region-code\&quot;: \&quot;NCR\&quot;,\&quot;coordinates\&quot;: \&quot;0,0\&quot;,\&quot;postal-code\&quot;: \&quot;1222\&quot;,\&quot;city-code\&quot;: \&quot;MAKATI\&quot;},\&quot;business-name\&quot;: \&quot;\&quot;,\&quot;valid-id\&quot;: \&quot;tes1234@texo.id\&quot;,\&quot;account-name\&quot;: \&quot;Qwerty, Qwerty\&quot;,\&quot;authorized-mobile\&quot;: \&quot;08999734544\&quot;,\&quot;first-name\&quot;: \&quot;Qwerty\&quot;,\&quot;last-name\&quot;: \&quot;Qwerty\&quot;},\&quot;auth\&quot;: {\&quot;password\&quot;: \&quot;1234\&quot;},\&quot;request-id\&quot;: \&quot;ahflashfahfl3432434wdfdsf\&quot;}\n&quot;,
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
      <name>Token</name>
      <type>Main</type>
      <value>WLu28cXFYvrdtQ7KFNxDUI3hpufmj+EbNknAEL9i7pfdjx69s/lnu3YSScaxUv+7Iere9Or5f1AvNC3rO8l+U3gkcU87vUrlHu6llGJeZiolpM2mD1ZePTlPyjVrArkmlK5Ui8vnGmu55anh2jq2Y4KD9HIj2FI8ENzfFqPX3/vmVH2e8ImkxsDuK1Ot+oH6BVxUKThhqcVPFfv3Qe52AA==</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>http://dev.kaspro.id/api/111234595630/partner/subscribers</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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
