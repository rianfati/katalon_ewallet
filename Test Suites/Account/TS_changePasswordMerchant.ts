<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>TS_changePasswordMerchant</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <testSuiteGuid>988f0ad2-c2a7-4e75-a83f-9fdcd5f4ea2c</testSuiteGuid>
   <testCaseLink>
      <guid>7b8678c3-cfab-4852-8651-d4b9cf71505c</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Account/TC_loginMerchant/TC_loginMerchantPewe</testCaseId>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>c642f486-7ae1-474a-9118-c2c02f9345d6</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>d05aa94f-ea2e-41ef-8b0a-2fdefd300a6a</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>5b209898-4dfb-4766-8fae-fa19daf7c348</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>fdea11c2-3605-45c1-b8c9-bb36873a2ecb</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>b8b88eb0-c135-4d3b-a3b8-742f5ef6e6f2</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>f9e3adec-60bf-45a7-be45-97f8dc2f49aa</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>728c868a-4f55-41b9-8ade-80aef5017e8d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>ddb6f98a-2f91-4030-9a30-343839f8f00d</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>c96fe4a8-28d9-48b5-9291-ec47aa8a8a10</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>false</isRun>
      <testCaseId>Test Cases/Account/TC_changePasswordMerchant/TC_changePasswordMerchant</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>8f7494cb-d7d1-4638-8426-a92d496faf94</id>
         <iterationEntity>
            <iterationType>RANGE</iterationType>
            <value>1-1</value>
         </iterationEntity>
         <testDataId>Data Files/Account/DF_changePasswordMerchant</testDataId>
      </testDataLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_old_password</value>
         <variableId>90934f75-7004-4d95-88c2-fa4dad8ff36d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_new_password</value>
         <variableId>41a3ab96-ed46-4024-83ab-1b95806963fb</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_confirm_password</value>
         <variableId>d11e4422-18d8-4805-8324-65c3e3953118</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>00552a10-8251-4126-937c-2a0d36940b32</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_organisation_id</value>
         <variableId>9a9427a3-7db8-4db9-ae8b-0a429c44548c</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_lang</value>
         <variableId>5eed4a52-9258-4353-a78e-07c0ae686d94</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_expected_rc</value>
         <variableId>a6d677a5-bb6b-488d-84bd-6235663cd718</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>8f7494cb-d7d1-4638-8426-a92d496faf94</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>var_merchant_token</value>
         <variableId>51502d63-ac6d-4403-a816-04865986b42d</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>6e899aef-110b-4e0f-a549-61fe80ef5427</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>b6f634b4-f07d-4123-9c8a-800ede538859</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
