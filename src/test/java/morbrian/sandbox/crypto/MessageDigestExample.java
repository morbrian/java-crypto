package morbrian.sandbox.crypto;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestExample {

  @Test
  public void testMessageDigest() throws NoSuchAlgorithmException {
    String input = "sample_input_string";
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(input.getBytes(), 0, input.length());
    String hashed = new BigInteger(1, md.digest()).toString(16);
    System.out.println("Hashed = " + hashed);
  }
}
