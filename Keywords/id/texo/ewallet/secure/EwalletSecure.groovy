package id.texo.ewallet.secure


import java.security.GeneralSecurityException as GeneralSecurityException

import java.security.KeyFactory as KeyFactory
import java.security.PrivateKey as PrivateKey
import java.security.PublicKey as PublicKey
import java.security.SecureRandom as SecureRandom
import java.security.Security as Security
import java.security.interfaces.RSAPrivateKey as RSAPrivateKey
import java.security.interfaces.RSAPublicKey as RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec as PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec as X509EncodedKeySpec
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher as Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

import org.apache.commons.codec.binary.Base64
import org.bouncycastle.jce.provider.BouncyCastleProvider

import com.kms.katalon.core.annotation.Keyword


class SecureUtils {

	static {
		// add provider only if it's not in the JVM
		// https://stackoverflow.com/questions/45197948/adding-security-provider-multiple-times-in-java-application
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}

	public static String ivExample = "1234567812345678"
	public static String ivExampleQR = "8334567812343671"

	@Keyword
	public static String getKey(String filename) throws IOException {
		String strKeyPEM = ''

		BufferedReader br = new BufferedReader(new FileReader(filename))

		String line

		while ((line = br.readLine()) != null) {
			strKeyPEM += (line + '\n')
		}

		br.close()

		return strKeyPEM
	}

	@Keyword
	public static RSAPrivateKey getPrivateKey(String filename) throws IOException, GeneralSecurityException {
		String privateKeyPEM = getKey(filename)

		return getPrivateKeyFromString(privateKeyPEM)
	}

	@Keyword
	public static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {

		//		println 'Private key PEM: ' + privateKeyPEM
		String privateKeyPEM = key
		privateKeyPEM = privateKeyPEM.replace('-----BEGIN RSA PRIVATE KEY-----\n', '')
		privateKeyPEM = privateKeyPEM.replace('-----END RSA PRIVATE KEY-----', '')

		byte[] encoded = Base64.decodeBase64(privateKeyPEM)

		KeyFactory kf = KeyFactory.getInstance('RSA')
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded)
		RSAPrivateKey privKey = ((kf.generatePrivate(keySpec)) as RSAPrivateKey)

		return privKey
	}

	@Keyword
	public static RSAPublicKey getPublicKey(String filename) throws IOException, GeneralSecurityException {
		String publicKeyPEM = getKey(filename)

		return getPublicKeyFromString(publicKeyPEM)
	}

	@Keyword
	public static RSAPublicKey getPublicKeyFromString(String key) throws IOException, GeneralSecurityException {
		String publicKeyPEM = key
		//		println 'Public key PEM: ' + publicKeyPEM

		publicKeyPEM = publicKeyPEM.replace('-----BEGIN RSA PUBLIC KEY-----\n', '')
		publicKeyPEM = publicKeyPEM.replace('\n-----END RSA PUBLIC KEY-----', '')
		publicKeyPEM = publicKeyPEM.replace('-----BEGIN PUBLIC KEY-----\n', '')
		publicKeyPEM = publicKeyPEM.replace('-----END PUBLIC KEY-----\n', '')

		//		println 'Replaced: ' + publicKeyPEM

		byte[] encoded = Base64.decodeBase64(publicKeyPEM)

		KeyFactory kf = KeyFactory.getInstance('RSA')

		RSAPublicKey pubKey = ((kf.generatePublic(new X509EncodedKeySpec(encoded))) as RSAPublicKey)

		return pubKey
	}

	@Keyword
	public static String doEncryptPinRsaEwallet(String publicKeyLoc, String pin){

		Cipher cipher = Cipher.getInstance('RSA/None/OAEPwithSHA-256andMGF1Padding', 'BC')

		SecureRandom random = new SecureRandom()

		PublicKey pubKey = getPublicKey(publicKeyLoc)

		cipher.init(Cipher.ENCRYPT_MODE, pubKey, random)

		byte[] cipherText = cipher.doFinal(pin.getBytes())

		//		System.out.println('cipher: ' + Base64.encodeBase64String(cipherText))

		return Base64.encodeBase64String(cipherText)
	}

	@Keyword
	public static String doDecryptPinRsaEwallet(String privateKeyLoc, String cipherString){

		Cipher cipher = Cipher.getInstance('RSA/None/OAEPwithSHA-256andMGF1Padding', 'BC')

		SecureRandom random = new SecureRandom()

		PrivateKey privKey = getPrivateKey(privateKeyLoc)

		byte[]cipherText = cipherString.getBytes()

		cipher.init(Cipher.DECRYPT_MODE, privKey)

		byte[] plainText = cipher.doFinal(cipherText)

		//		System.out.println('plain : ' + new String(plainText))

		return new String(plainText)
	}

	@Keyword
	public static String encryptAesEwallet(String plain, String key){

		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

		byte[] iv = ivExample.getBytes("UTF-8");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

		Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		cipher1.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encrypted = cipher1.doFinal(plain.getBytes("UTF-8"));

		String encodedString = Base64.encodeBase64String(encrypted);

		return encodedString
	}

	@Keyword
	public static String decryptAesEwallet(String cipheredText, String key){

		byte[] iv = ivExample.getBytes("UTF-8");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipherdec = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		cipherdec.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

		byte[] decodedText = cipheredText.decodeBase64()

		byte[] decrypted = cipherdec.doFinal(decodedText);
		String str = new String(decrypted);

		return str
	}

	@Keyword
	public static String encryptAesQR(String plain, String key){

		SecretKeySpec secretKeySpecQR = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

		byte[] ivQR = ivExampleQR.getBytes("UTF-8");
		IvParameterSpec ivParameterSpecQR = new IvParameterSpec(ivQR);

		Cipher cipher1QR = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		cipher1QR.init(Cipher.ENCRYPT_MODE, secretKeySpecQR, ivParameterSpecQR);
		byte[] encryptedQR = cipher1QR.doFinal(plain.getBytes("UTF-8"));

		String encodedStringQR = Base64.encodeBase64String(encryptedQR);

		return encodedStringQR
	}

	@Keyword
	public static String decryptAesQR(String cipheredText, String key){

		byte[] ivQR = ivExampleQR.getBytes("UTF-8");
		IvParameterSpec ivParameterSpecQR = new IvParameterSpec(ivQR);
		SecretKeySpec secretKeySpecQR = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipherdecQR = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
		cipherdecQR.init(Cipher.DECRYPT_MODE, secretKeySpecQR, ivParameterSpecQR);

		byte[] decodedTextQR = cipheredText.decodeBase64()

		byte[] decryptedQR = cipherdecQR.doFinal(decodedTextQR);
		String strQR = new String(decryptedQR);

		return strQR
	}

	public static String toHexString(byte[] hash)
	{
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32)
		{
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}
	@Keyword
	public static String calcSha256(String input) {

		MessageDigest md = MessageDigest.getInstance("SHA-256");

		return toHexString(md.digest(input.getBytes(StandardCharsets.UTF_8)));
	}
}