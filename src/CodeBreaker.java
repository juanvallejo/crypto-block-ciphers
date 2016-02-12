import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Plaintext is "Good Job"
 * Key is [BA, 54, 68, 08, 12, D4, C6, 9E]
 */

public class CodeBreaker {

	public static boolean isValidString(String string) {
		for(int i = 0; i < string.length(); i++) {
			if(((int)string.charAt(i)) != 32
				&& !(((int)string.charAt(i)) >= 65 && ((int)string.charAt(i)) <= 90)
				&& !(((int)string.charAt(i)) >= 97 && ((int)string.charAt(i)) <= 122)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		String plaintext;
		Cipher cipher;
		SecretKeySpec key;
		byte[] ptbytes;

		char[] hexGlyphs  = {'A', 'B', 'C', 'D', 'E', 'F'};
		byte[] ciphertext = {(byte)0xB1, (byte)0x80, (byte)0xE8, (byte)0x05, (byte)0x4E, (byte)0x7D, (byte)0xD6, (byte)0x4C};
		byte[] kbytes     = new byte[8];

		kbytes[0] = (byte)0xBA;
		kbytes[1] = (byte)0x54;
		kbytes[2] = (byte)0x68;
		kbytes[3] = (byte)0x08;
		kbytes[4] = (byte)0x12;

		System.out.println("Brute-forcing...");

		try {

			String byte_a;
			String byte_b;
			String byte_c;
			String byte_d;
			String byte_e;
			String byte_f;

            // create DES cipher
            cipher = Cipher.getInstance("DES/ECB/NoPadding");

            for(int a = 0; a < 16; a++) {
            	for(int b = 0; b < 16; b++) {
            		for(int c = 0; c < 16; c++) { 
            			for(int d = 0; d < 16; d++) { 
            				for(int e = 0; e < 16; e++) { 
            					for(int f = 0; f < 16; f++) { 

        							byte_a    = a > 9 ? "" + hexGlyphs[a - 10] : "" + a;
        							byte_b    = b > 9 ? "" + hexGlyphs[b - 10] : "" + b;
        							kbytes[5] = (byte)(Integer.parseInt(byte_a + byte_b, 16) & 0xff);

        							byte_c    = c > 9 ? "" + hexGlyphs[c - 10] : "" + c;
        							byte_d    = d > 9 ? "" + hexGlyphs[d - 10] : "" + d;
        							kbytes[6] = (byte)(Integer.parseInt(byte_c + byte_d, 16) & 0xff);

        							byte_e    = e > 9 ? "" + hexGlyphs[e - 10] : "" + e;
        							byte_f    = f > 9 ? "" + hexGlyphs[f - 10] : "" + f;
        							kbytes[7] = (byte)(Integer.parseInt(byte_e + byte_f, 16) & 0xff);
        							
        							key = new SecretKeySpec(kbytes, "DES");

            						cipher.init(Cipher.DECRYPT_MODE, key);
						            // encrypt plaintext, print byte array
						            ptbytes = cipher.doFinal(ciphertext);
						            plaintext = new String(ptbytes);

						            if(isValidString(plaintext)) {
						            	System.out.println("Plaintext: " + plaintext);
						            	System.out.print("Key:       ");
						            	Utils.printByteArray(kbytes);
						            }

		        				}
		        			}
		        		}
		        	}
		        }
	        }

        } catch(Exception e) {
            e.printStackTrace();
        }

	}

}