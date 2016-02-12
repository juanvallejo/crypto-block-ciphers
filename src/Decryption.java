import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Plaintext: CPSC 428
 * @author juanvallejo / Cory Bowles
 */

public class Decryption {

    public static void main(String[] args) {

        byte[] plaintext;
        Cipher cipher;
        SecretKeySpec key;

        // given plaintext from docs
        byte[] ciphertext = {(byte)0x9D, (byte)0x1C, (byte)0x1D, (byte)0x94, (byte)0x8F, (byte)0x21, (byte)0x55, (byte)0xC5};
        byte[] kbytes     = {(byte)0x46, (byte)0xAA, (byte)0x20, (byte)0x1E, (byte)0xF4, (byte)0x3C, (byte)0x92, (byte)0xD2};

        // encrypt using key and DES spec
        key = new SecretKeySpec(kbytes, "DES");

        try {

            // create DES cipher
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            // encrypt plaintext, print byte array
            plaintext = cipher.doFinal(ciphertext);
            // Utils.printByteArray(plaintext);
            System.out.println(new String(plaintext));

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}