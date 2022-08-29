
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String


def static "id.texo.ewallet.TexoUtils.addGlobalVariable"(
    	String name	
     , 	Object value	) {
    (new id.texo.ewallet.TexoUtils()).addGlobalVariable(
        	name
         , 	value)
}

def static "id.texo.ewallet.secure.SecureUtils.getKey"(
    	String filename	) {
    (new id.texo.ewallet.secure.SecureUtils()).getKey(
        	filename)
}

def static "id.texo.ewallet.secure.SecureUtils.getPrivateKey"(
    	String filename	) {
    (new id.texo.ewallet.secure.SecureUtils()).getPrivateKey(
        	filename)
}

def static "id.texo.ewallet.secure.SecureUtils.getPrivateKeyFromString"(
    	String key	) {
    (new id.texo.ewallet.secure.SecureUtils()).getPrivateKeyFromString(
        	key)
}

def static "id.texo.ewallet.secure.SecureUtils.getPublicKey"(
    	String filename	) {
    (new id.texo.ewallet.secure.SecureUtils()).getPublicKey(
        	filename)
}

def static "id.texo.ewallet.secure.SecureUtils.getPublicKeyFromString"(
    	String key	) {
    (new id.texo.ewallet.secure.SecureUtils()).getPublicKeyFromString(
        	key)
}

def static "id.texo.ewallet.secure.SecureUtils.doEncryptPinRsaEwallet"(
    	String publicKeyLoc	
     , 	String pin	) {
    (new id.texo.ewallet.secure.SecureUtils()).doEncryptPinRsaEwallet(
        	publicKeyLoc
         , 	pin)
}

def static "id.texo.ewallet.secure.SecureUtils.doDecryptPinRsaEwallet"(
    	String privateKeyLoc	
     , 	String cipherString	) {
    (new id.texo.ewallet.secure.SecureUtils()).doDecryptPinRsaEwallet(
        	privateKeyLoc
         , 	cipherString)
}

def static "id.texo.ewallet.secure.SecureUtils.encryptAesEwallet"(
    	String plain	
     , 	String key	) {
    (new id.texo.ewallet.secure.SecureUtils()).encryptAesEwallet(
        	plain
         , 	key)
}

def static "id.texo.ewallet.secure.SecureUtils.decryptAesEwallet"(
    	String cipheredText	
     , 	String key	) {
    (new id.texo.ewallet.secure.SecureUtils()).decryptAesEwallet(
        	cipheredText
         , 	key)
}

def static "id.texo.ewallet.secure.SecureUtils.encryptAesQR"(
    	String plain	
     , 	String key	) {
    (new id.texo.ewallet.secure.SecureUtils()).encryptAesQR(
        	plain
         , 	key)
}

def static "id.texo.ewallet.secure.SecureUtils.decryptAesQR"(
    	String cipheredText	
     , 	String key	) {
    (new id.texo.ewallet.secure.SecureUtils()).decryptAesQR(
        	cipheredText
         , 	key)
}

def static "id.texo.ewallet.secure.SecureUtils.calcSha256"(
    	String input	) {
    (new id.texo.ewallet.secure.SecureUtils()).calcSha256(
        	input)
}

def static "id.texo.ewallet.ReturnCode.getRCName"() {
    (new id.texo.ewallet.ReturnCode()).getRCName()
}

def static "id.texo.ewallet.ReturnCode.getMessage"() {
    (new id.texo.ewallet.ReturnCode()).getMessage()
}

def static "id.texo.ewallet.ReturnCode.getDescription"() {
    (new id.texo.ewallet.ReturnCode()).getDescription()
}

def static "id.texo.ewallet.ReturnCode.getHttpResponseCode"() {
    (new id.texo.ewallet.ReturnCode()).getHttpResponseCode()
}

def static "id.texo.ewallet.ReturnCodeWrapper.getEnumRC"() {
    (new id.texo.ewallet.ReturnCodeWrapper()).getEnumRC()
}
