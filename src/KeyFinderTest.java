import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author juanvallejo / Cory Bowles
 */

public class KeyFinderTest {

    public static void main(String[] args) {

        byte[] plaintext;
        byte[] ciphertext2;
        Cipher cipher;
        SecretKeySpec key;

        String plaintext2 = "Captains";

        // given plaintext from docs
        byte[] ciphertext = {(byte)0xA5, (byte)0x99, (byte)0x04, (byte)0x72, (byte)0x39, (byte)0x95, (byte)0x41, (byte)0xEC};
        byte[] kbytes     = {(byte)0x90, (byte)0x4E, (byte)0xF2, (byte)0xCC, (byte)0x86, (byte)0x03, (byte)0x4A, (byte)0x16};

        // encrypt using key and DES spec
        key = new SecretKeySpec(kbytes, "DES");

        try {

            // create DES cipher
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // encrypt plaintext, print byte array
            ciphertext2 = cipher.doFinal(plaintext2.getBytes());
            Utils.printByteArray(ciphertext2);
            // System.out.println(new String(plaintext));

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}