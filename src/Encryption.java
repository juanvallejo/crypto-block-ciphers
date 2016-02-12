import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Juan Vallejo / Cory Bowles
 * Ciphertext: [E5, 0E, A6, 1E, 50, A3, D2, 69]
 */

public class Encryption {

    public static void main(String[] args) {

        byte[] ciphertext;
        Cipher cipher;
        SecretKeySpec key;

        // given plaintext from docs
        String plaintext = "Dee Bugg";
        byte[] ptbytes   = plaintext.getBytes();
        byte[] kbytes    = {(byte)0x7A, (byte)0x90, (byte)0xC8, (byte)0x36, (byte)0x44, (byte)0x0E, (byte)0x18, (byte)0x76};

        // encrypt using key and DES spec
        key = new SecretKeySpec(kbytes, "DES");

        try {

            // create DES cipher
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // encrypt plaintext, print byte array
            ciphertext = cipher.doFinal(ptbytes);
            Utils.printByteArray(ciphertext);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}