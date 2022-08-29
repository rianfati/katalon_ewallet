<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>TS_signUpOrganisationRian</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <testSuiteGuid>9b4a358a-a301-4b09-b966-6331e0b2e26a</testSuiteGuid>
   <testCaseLink>
      <guid>f27ab6b9-77fe-4ddf-a85c-e9502c500bc3</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>false</isRun>
      <testCaseId>Test Cases/Account/TC_signUpOrganisation/TC_signUpOrganisation</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>e5633854-4644-4eda-bde4-d38bc6b4e191</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-1</value>
         </iterationEntity>
         <testDataId>Data Files/Account/DF_signUpOrganisation_Rian</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>password</value>
         <variableId>9045378a-b1f6-452b-b4d3-be668cd2fe24</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>confirmation_password</value>
         <variableId>3a2f7361-9a72-40dc-aec7-69951ef22432</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>user_agent</value>
         <variableId>71f4049f-7d96-4d7a-8b97-ee6b41706b50</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>email_address</value>
         <variableId>96620e2f-2bf1-4bbe-a715-c85aeba4c9c8</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>name</value>
         <variableId>fb86452f-edbc-466e-992f-a39024506402</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>organisation_id</value>
         <variableId>d53dbb28-0f9a-45bc-90c7-ec37663ce65b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>lang</value>
         <variableId>b781273b-3947-44d8-88e2-0c9c855303cf</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>expected_rc</value>
         <variableId>9cef6b8d-fec5-4a75-9207-614f23d146c8</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>763581d1-525b-43ab-a2ef-c77483e69132</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e5633854-4644-4eda-bde4-d38bc6b4e191</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>partner_token</value>
         <variableId>acf92336-84ce-4abf-bc9c-fcf787c78f2d</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>e516ae97-5075-4359-bb34-43a378cae142</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Account/TC_signUpOrganisation/Sign_Up_Organisation_Rian_Project_CI_CD</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>e89449ec-66a0-483b-8c44-0359bdf61bb7</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-1</value>
         </iterationEntity>
         <testDataId>Data Files/Account/DF_signUpOrganisation_CICD</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>password</value>
         <variableId>20c7f740-e640-4e31-aad3-c5f185069f2b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>confirmation_password</value>
         <variableId>8c353b65-dd12-4405-a1af-111edc3d136b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>user_agent</value>
         <variableId>0e334a9c-7d40-4ede-8113-6ba06b9ef19b</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>email_address_number</value>
         <variableId>cdc68efa-423f-4b4b-bfed-704e15882196</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>name</value>
         <variableId>28b1a354-f28b-413e-98bc-665e58b2d1ae</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>organisation_id</value>
         <variableId>b6d17745-7183-4134-8a39-4492ac49fe37</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>lang</value>
         <variableId>040ed198-582f-4950-9f20-d2a412daeaa3</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>expected_rc</value>
         <variableId>253f6af7-a423-4b08-b19b-d33239ffb51d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>f830b50d-9e7d-4a92-b5e2-1250210b130d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>partner_token</value>
         <variableId>1d891395-14ca-43c2-ae28-64ed9dc46c1c</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>email_attribute</value>
         <variableId>8cc7b7e4-6071-4e0c-9647-b5840411ab92</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e89449ec-66a0-483b-8c44-0359bdf61bb7</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>email_address_letter</value>
         <variableId>8a23b062-4847-46b6-9a7f-4d4e295aeab1</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
