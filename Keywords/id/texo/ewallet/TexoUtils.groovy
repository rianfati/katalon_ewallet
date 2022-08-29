package id.texo.ewallet

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.google.zxing.BinaryBitmap as BinaryBitmap
import com.google.zxing.EncodeHintType as EncodeHintType
import com.google.zxing.MultiFormatReader as MultiFormatReader
import com.google.zxing.NotFoundException as NotFoundException
import com.google.zxing.Result as Result
import com.google.zxing.client.j2se.BufferedImageLuminanceSource as BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer as HybridBinarizer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel as ErrorCorrectionLevel
import javax.imageio.ImageIO as ImageIO

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

public class TexoUtils {
	@Keyword
	void addGlobalVariable(String name, def value) {
		GroovyShell shell1 = new GroovyShell()
		MetaClass mc = shell1.evaluate("internal.GlobalVariable").metaClass
		String getterName = "get" + name.capitalize()
		mc.'static'."$getterName" = { -> return value }
		mc.'static'."$name" = value
	}

	public static File export(String base64data, String filepath) {
		FileOutputStream fos = null;
		try {
			byte[] data = Base64.getDecoder().decode(base64data);
			File file = new File(filepath);
			fos = new FileOutputStream(file);
			fos.write(data);
			return file;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return null;
	}

	public static String readQRCode(String filePath, String charset, Map<EncodeHintType, ErrorCorrectionLevel> hintMap) throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(
				filePath)))))

		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap)

		return qrCodeResult.getText()
	}
}