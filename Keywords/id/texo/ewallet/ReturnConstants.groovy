package id.texo.ewallet
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException



@Keyword
public class ReturnCode {
	String RC
	int httpResponseCode
	String message
	String description

	ReturnCode(String rcName, String message, String description, int responseCode){
		this.RC = rcName
		this.message = message
		this.description = description
		this.httpResponseCode = responseCode
	}
	@Keyword
	public String getRCName(){
		return this.RC
	}
	@Keyword
	public String getMessage(){
		return this.message
	}
	@Keyword
	public String getDescription(){
		return this.description
	}
	@Keyword
	public int getHttpResponseCode(){
		return this.httpResponseCode
	}
}

@Keyword
public enum ReturnCodes {

	RC00(new ReturnCode("RC00", "success", "Berhasil", 200)),
	RC01(new ReturnCode("RC01", "invalid parameter", "The parameter sent is not correspond, wrong or incomplete", 400)),
	RC02(new ReturnCode("RC02", "data not found", "The specified data not found in the system", 400)),
	RC03(new ReturnCode("RC03", "data too long", "An error occur with maximum data request exceeded", 400)),
	//
	RC05(new ReturnCode("RC05", "data cannot be empty", "One or more data is empty", 400)),
	RC06(new ReturnCode("RC06", "authentication failure", "wrong credential or not found", 401)),
	RC07(new ReturnCode("RC07", "authentication failure", "old password does not match", 401)),
	RC08(new ReturnCode("RC08", "access denied", "access denied", 401)),
	RC09(new ReturnCode("RC09", "authentication failure", "partner-token atau org id yg berkesesuaian tidak ditemukan", 401)),
	//
	RC10(new ReturnCode("RC10", "invalid account", "wrong credential or (email, phone number) not found in the system", 401)),
	//
	RC12(new ReturnCode("RC12", "blocked account", "Your account is blocked, please contact customer support", 401)),
	RC13(new ReturnCode("RC13", "invalid otp", "Invalid OTP", 401)),
	RC14(new ReturnCode("RC14", "locked account", "Account is locked for a period", 401)),
	//
	RC16(new ReturnCode("RC16", "duplicate process", "Transaksi sedang di proses mohon tunggu beberapa saat lagi", 409)),
	RC17(new ReturnCode("RC17", "duplicate data found", "Duplicate data found in the system", 409)),
	RC18(new ReturnCode("RC18", "wrong credential", "Token/credential is wrong or not matched", 401)),
	//
	//
	RC21(new ReturnCode("RC21", "error creating va billing", "Virtual account failed to process", 500)),
	RC22(new ReturnCode("RC22", "internal server error", "Internal error occured during processing your request", 500)),
	RC23(new ReturnCode("RC23", "Request otp exceed", "otp request exceed maximum limit", 401)),
	RC24(new ReturnCode("RC24", "otp request denied", "cannot request otp", 401)),
	RC25(new ReturnCode("RC25", "va status is not active", "this service only can be use for va with status active", 401)),
	RC26(new ReturnCode("RC26", "otp request locked", "otp request is locked for sometime", 401)),
	//
	//
	//
	RC30(new ReturnCode("RC30", "Add SOF Failed", "SOF cannot be added into the account", 400)),
	//RC40-RC49 are reserved for billing
	RC40(new ReturnCode("RC40", "Billing already paid", "Billing already paid", 400)),
	RC41(new ReturnCode("RC41", "Insufficient Fund", "Fund Insufficient", 400)),
	RC42(new ReturnCode("RC42", "QR expired", "QR expired", 400)),
	RC43(new ReturnCode("RC43", "Account Wrong Info", "First name or Last name wrong, please update profile", 400)),
	RC44(new ReturnCode("RC44", "Wallet not found", "Wallet Not found", 400)),
	RC45(new ReturnCode("RC45", "Billing expired", "Billing expired", 400)),
	RC46(new ReturnCode("RC46", "Payment failed", "Payment failed", 400)),
	//RC49
	RC50(new ReturnCode("RC50", "Token or activation code not found", "Token or activation code not found", 400)),
	///
	RC90(new ReturnCode("RC90", "unknown error", "Internal server error", 500)),
	RC91(new ReturnCode("RC91", "server unreachable", "Server unreachable", 502)),
	RC92(new ReturnCode("RC92", "service unavailable", "Error as API can not be used but accesible from client", 503)),
	RC93(new ReturnCode("RC93", "Request timeout", "Server not responding", 504)),
	RC94(new ReturnCode("RC94", "Request timeout", "no internet access", 504)),
	RC95(new ReturnCode("RC95", "invalid parameter", "your combination does not match", 504)),
	RC96(new ReturnCode("RC96", "invalid format request", "your combination does not match", 400)),


	private ReturnCode retCode;

	public ReturnCodes(ReturnCode RC)  {
		this.retCode = RC
	}

	public ReturnCode getRC(){
		return this.retCode
	}

	public String getRCName(){
		return this.retCode.getRCName()
	}

	public String getRCMessage(){
		return this.retCode.getMessage()
	}

	public String getRCDescription(){
		return this.retCode.getDescription()
	}

	public String getHttpResponse(){
		return this.retCode.getHttpResponseCode()
	}
}

@Keyword
public class ReturnCodeWrapper{

	private ReturnCodes rc

	@Keyword
	ReturnCodeWrapper(String rc){
		switch(rc){
			case "RC00":
				this.rc = ReturnCodes.RC00
				break
			case "RC01":
				this.rc = ReturnCodes.RC01
				break
			case "RC02":
				this.rc = ReturnCodes.RC02
				break
			case "RC03":
				this.rc = ReturnCodes.RC03
				break
			//			case "RC04":
			//				this.rc = ReturnCodes.RC04
			//				break
			case "RC05":
				this.rc = ReturnCodes.RC05
				break
			case "RC06":
				this.rc = ReturnCodes.RC06
				break
			case "RC07":
				this.rc = ReturnCodes.RC07
				break
			case "RC08":
				this.rc = ReturnCodes.RC08
				break
			case "RC09":
				this.rc = ReturnCodes.RC09
				break
			case "RC10":
				this.rc = ReturnCodes.RC10
				break
			//			case "RC11":
			//				this.rc = ReturnCodes.RC11
			//				break
			case "RC12":
				this.rc = ReturnCodes.RC12
				break
			case "RC13":
				this.rc = ReturnCodes.RC13
				break
			case "RC14":
				this.rc = ReturnCodes.RC14
				break
			//			case "RC15":
			//				this.rc = ReturnCodes.RC15
			//				break
			case "RC16":
				this.rc = ReturnCodes.RC16
				break
			case "RC17":
				this.rc = ReturnCodes.RC17
				break
			case "RC18":
				this.rc = ReturnCodes.RC18
				break
			//			case "RC19":
			//				this.rc = ReturnCodes.RC19
			//				break
			//			case "RC20":
			//				this.rc = ReturnCodes.RC20
			//				break
			case "RC21":
				this.rc = ReturnCodes.RC21
				break
			case "RC22":
				this.rc = ReturnCodes.RC22
				break
			case "RC23":
				this.rc = ReturnCodes.RC23
				break
			case "RC24":
				this.rc = ReturnCodes.RC24
				break
			case "RC25":
				this.rc = ReturnCodes.RC25
				break
			case "RC26":
				this.rc = ReturnCodes.RC26
				break
			//
			case "RC30":
				this.rc = ReturnCodes.RC30
				break
			//
			case "RC40":
				this.rc = ReturnCodes.RC40
				break
			case "RC41":
				this.rc = ReturnCodes.RC41
				break
			case "RC42":
				this.rc = ReturnCodes.RC42
				break
			case "RC43":
				this.rc = ReturnCodes.RC43
				break
			case "RC44":
				this.rc = ReturnCodes.RC44
				break
			case "RC45":
				this.rc = ReturnCodes.RC45
				break
			case "RC46":
				this.rc = ReturnCodes.RC46
				break
			//
			case "RC50":
				this.rc = ReturnCodes.RC50
				break
			//
			case "RC90":
				this.rc = ReturnCodes.RC90
				break
			case "RC91":
				this.rc = ReturnCodes.RC91
				break
			case "RC92":
				this.rc = ReturnCodes.RC92
				break
			case "RC93":
				this.rc = ReturnCodes.RC93
				break
			case "RC94":
				this.rc = ReturnCodes.RC94
				break
			case "RC95":
				this.rc = ReturnCodes.RC95
				break
			case "RC96":
				this.rc = ReturnCodes.RC96
				break
			default:
				new RuntimeException("Unrecognized RC code")
		}
	}

	@Keyword
	ReturnCodes getEnumRC(){
		return this.rc
	}
}

