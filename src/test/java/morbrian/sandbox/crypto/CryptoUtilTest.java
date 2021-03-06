package morbrian.sandbox.crypto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CryptoUtilTest {

  @Test
  public void testEncryptDecryptAes() throws Exception {

    String sampleText = "Hello World!";

    int aesKeyLength = 128;
    String aesKey = CryptoUtil.generateAesKeyString(aesKeyLength);
    String encryptedText = CryptoUtil.encryptAes(sampleText, aesKey, aesKeyLength);
    String decryptedText = CryptoUtil.decryptAes(encryptedText, aesKey, aesKeyLength);

    assertEquals(sampleText, decryptedText);
  }

}
