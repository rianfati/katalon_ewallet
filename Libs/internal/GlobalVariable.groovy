package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object hostname
     
    /**
     * <p></p>
     */
    public static Object dev_private_pem_file
     
    /**
     * <p></p>
     */
    public static Object dev_public_pem_file
     
    /**
     * <p></p>
     */
    public static Object access_token_customer
     
    /**
     * <p></p>
     */
    public static Object access_token_merchant
     
    /**
     * <p></p>
     */
    public static Object aes_key_org_texo
     
    /**
     * <p></p>
     */
    public static Object partner_token_texo
     
    /**
     * <p></p>
     */
    public static Object user_pin
     
    /**
     * <p></p>
     */
    public static Object var_billing_id
     
    /**
     * <p></p>
     */
    public static Object date
     
    /**
     * <p></p>
     */
    public static Object page_number
     
    /**
     * <p></p>
     */
    public static Object page_size
     
    /**
     * <p></p>
     */
    public static Object settlement_detil_id
     
    /**
     * <p></p>
     */
    public static Object trace_number
     
    /**
     * <p></p>
     */
    public static Object edc_id
     
    /**
     * <p></p>
     */
    public static Object trx_type_id
     
    /**
     * <p></p>
     */
    public static Object out_id
     
    /**
     * <p></p>
     */
    public static Object partner_token
     
    /**
     * <p></p>
     */
    public static Object cicdemail
     
    /**
     * <p></p>
     */
    public static Object cicdphonenumber
     
    /**
     * <p></p>
     */
    public static Object cicdfirstname
     
    /**
     * <p></p>
     */
    public static Object cicdlastname
     
    /**
     * <p></p>
     */
    public static Object cicdfullname
     
    /**
     * <p></p>
     */
    public static Object email_name
     
    /**
     * <p></p>
     */
    public static Object email_number
     
    /**
     * <p></p>
     */
    public static Object login_phone_number
     
    /**
     * <p></p>
     */
    public static Object login_access_code
     
    /**
     * <p></p>
     */
    public static Object user_agent
     
    /**
     * <p></p>
     */
    public static Object fcm_token
     
    /**
     * <p></p>
     */
    public static Object device_id
     
    /**
     * <p></p>
     */
    public static Object organisation_id
     
    /**
     * <p></p>
     */
    public static Object lang
     
    /**
     * <p></p>
     */
    public static Object expected_rc
     
    /**
     * <p></p>
     */
    public static Object trx_type_id_billing
     
    /**
     * <p></p>
     */
    public static Object to
     
    /**
     * <p></p>
     */
    public static Object to_id
     
    /**
     * <p></p>
     */
    public static Object amount
     
    /**
     * <p></p>
     */
    public static Object wallet_id
     
    /**
     * <p></p>
     */
    public static Object billing_ref1
     
    /**
     * <p></p>
     */
    public static Object billing_ref2
     
    /**
     * <p></p>
     */
    public static Object billing_ref3
     
    /**
     * <p></p>
     */
    public static Object need_verify
     
    /**
     * <p></p>
     */
    public static Object qr_unique_code
     
    /**
     * <p></p>
     */
    public static Object customer_token
     
    /**
     * <p></p>
     */
    public static Object pin_kaspro
     
    /**
     * <p></p>
     */
    public static Object start_time
     
    /**
     * <p></p>
     */
    public static Object end_time
     
    /**
     * <p></p>
     */
    public static Object page_number_mandatory
     
    /**
     * <p></p>
     */
    public static Object page_size_mandatory
     
    /**
     * <p></p>
     */
    public static Object password
     
    /**
     * <p></p>
     */
    public static Object login_email_merchant
     
    /**
     * <p></p>
     */
    public static Object merchant_outlet_id
     
    /**
     * <p></p>
     */
    public static Object transaction_date
     
    /**
     * <p></p>
     */
    public static Object merchant_token
     
    /**
     * <p></p>
     */
    public static Object resi
     
    /**
     * <p></p>
     */
    public static Object distric_id
     
    /**
     * <p></p>
     */
    public static Object distric_code
     
    /**
     * <p></p>
     */
    public static Object distric_name
     
    /**
     * <p></p>
     */
    public static Object aes_key_org_QR
     
    /**
     * <p></p>
     */
    public static Object hsm_pem_file
     
    /**
     * <p></p>
     */
    public static Object customer_access_code_kaspro
     
    /**
     * <p></p>
     */
    public static Object sysadmin_email_address
     
    /**
     * <p></p>
     */
    public static Object sysadmin_password
     
    /**
     * <p></p>
     */
    public static Object login_merchant_email
     
    /**
     * <p></p>
     */
    public static Object login_merchant_password
     
    /**
     * <p></p>
     */
    public static Object end_date
     
    /**
     * <p></p>
     */
    public static Object start_date
     
    /**
     * <p></p>
     */
    public static Object grouping
     
    /**
     * <p></p>
     */
    public static Object token_tester
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            hostname = selectedVariables['hostname']
            dev_private_pem_file = selectedVariables['dev_private_pem_file']
            dev_public_pem_file = selectedVariables['dev_public_pem_file']
            access_token_customer = selectedVariables['access_token_customer']
            access_token_merchant = selectedVariables['access_token_merchant']
            aes_key_org_texo = selectedVariables['aes_key_org_texo']
            partner_token_texo = selectedVariables['partner_token_texo']
            user_pin = selectedVariables['user_pin']
            var_billing_id = selectedVariables['var_billing_id']
            date = selectedVariables['date']
            page_number = selectedVariables['page_number']
            page_size = selectedVariables['page_size']
            settlement_detil_id = selectedVariables['settlement_detil_id']
            trace_number = selectedVariables['trace_number']
            edc_id = selectedVariables['edc_id']
            trx_type_id = selectedVariables['trx_type_id']
            out_id = selectedVariables['out_id']
            partner_token = selectedVariables['partner_token']
            cicdemail = selectedVariables['cicdemail']
            cicdphonenumber = selectedVariables['cicdphonenumber']
            cicdfirstname = selectedVariables['cicdfirstname']
            cicdlastname = selectedVariables['cicdlastname']
            cicdfullname = selectedVariables['cicdfullname']
            email_name = selectedVariables['email_name']
            email_number = selectedVariables['email_number']
            login_phone_number = selectedVariables['login_phone_number']
            login_access_code = selectedVariables['login_access_code']
            user_agent = selectedVariables['user_agent']
            fcm_token = selectedVariables['fcm_token']
            device_id = selectedVariables['device_id']
            organisation_id = selectedVariables['organisation_id']
            lang = selectedVariables['lang']
            expected_rc = selectedVariables['expected_rc']
            trx_type_id_billing = selectedVariables['trx_type_id_billing']
            to = selectedVariables['to']
            to_id = selectedVariables['to_id']
            amount = selectedVariables['amount']
            wallet_id = selectedVariables['wallet_id']
            billing_ref1 = selectedVariables['billing_ref1']
            billing_ref2 = selectedVariables['billing_ref2']
            billing_ref3 = selectedVariables['billing_ref3']
            need_verify = selectedVariables['need_verify']
            qr_unique_code = selectedVariables['qr_unique_code']
            customer_token = selectedVariables['customer_token']
            pin_kaspro = selectedVariables['pin_kaspro']
            start_time = selectedVariables['start_time']
            end_time = selectedVariables['end_time']
            page_number_mandatory = selectedVariables['page_number_mandatory']
            page_size_mandatory = selectedVariables['page_size_mandatory']
            password = selectedVariables['password']
            login_email_merchant = selectedVariables['login_email_merchant']
            merchant_outlet_id = selectedVariables['merchant_outlet_id']
            transaction_date = selectedVariables['transaction_date']
            merchant_token = selectedVariables['merchant_token']
            resi = selectedVariables['resi']
            distric_id = selectedVariables['distric_id']
            distric_code = selectedVariables['distric_code']
            distric_name = selectedVariables['distric_name']
            aes_key_org_QR = selectedVariables['aes_key_org_QR']
            hsm_pem_file = selectedVariables['hsm_pem_file']
            customer_access_code_kaspro = selectedVariables['customer_access_code_kaspro']
            sysadmin_email_address = selectedVariables['sysadmin_email_address']
            sysadmin_password = selectedVariables['sysadmin_password']
            login_merchant_email = selectedVariables['login_merchant_email']
            login_merchant_password = selectedVariables['login_merchant_password']
            end_date = selectedVariables['end_date']
            start_date = selectedVariables['start_date']
            grouping = selectedVariables['grouping']
            token_tester = selectedVariables['token_tester']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
