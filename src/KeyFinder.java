import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class KeyFinder {

	public static void main(String[] args) {

		String plaintext;
		Cipher cipher;
		SecretKeySpec key;
		byte[] ptbytes;

		char[] hexGlyphs  = {'A', 'B', 'C', 'D', 'E', 'F'};
		byte[] ciphertext = {(byte)0xA5, (byte)0x99, (byte)0x04, (byte)0x72, (byte)0x39, (byte)0x95, (byte)0x41, (byte)0xEC};
		byte[] kbytes     = new byte[8];

		plaintext         = "Captains";

		kbytes[0] = (byte)0x90;
		kbytes[1] = (byte)0x4E;
		kbytes[2] = (byte)0xF2;
		kbytes[3] = (byte)0xCC;

		System.out.println("Brute-forcing right key half...");

		try {

			String byte_a;
			String byte_b;
			String byte_c;
			String byte_d;
			String byte_e;
			String byte_f;
			String byte_g;
			String byte_h;

            // create DES cipher
            cipher = Cipher.getInstance("DES/ECB/NoPadding");

            for(int a = 0; a < 16; a++) {
            	for(int b = 0; b < 16; b++) {
            		for(int c = 0; c < 16; c++) { 
            			for(int d = 0; d < 16; d++) { 
            				for(int e = 0; e < 16; e++) { 
            					for(int f = 0; f < 16; f++) { 
            						for(int g = 0; g < 16; g++) { 
            							for(int h = 0; h < 16; h++) { 

	            							byte_a    = a > 9 ? "" + hexGlyphs[a - 10] : "" + a;
	            							byte_b    = b > 9 ? "" + hexGlyphs[b - 10] : "" + b;
	            							kbytes[4] = (byte)(Integer.parseInt(byte_a + byte_b, 16) & 0xff);

	            							byte_c    = c > 9 ? "" + hexGlyphs[c - 10] : "" + c;
	            							byte_d    = d > 9 ? "" + hexGlyphs[d - 10] : "" + d;
	            							kbytes[5] = (byte)(Integer.parseInt(byte_c + byte_d, 16) & 0xff);

	            							byte_e    = e > 9 ? "" + hexGlyphs[e - 10] : "" + e;
	            							byte_f    = f > 9 ? "" + hexGlyphs[f - 10] : "" + f;
	            							kbytes[6] = (byte)(Integer.parseInt(byte_e + byte_f, 16) & 0xff);
	            							
	            							byte_g    = g > 9 ? "" + hexGlyphs[g - 10] : "" + g;
	            							byte_h    = h > 9 ? "" + hexGlyphs[h - 10] : "" + h;
	            							kbytes[7] = (byte)(Integer.parseInt(byte_g + byte_h, 16) & 0xff);

	            							key = new SecretKeySpec(kbytes, "DES");

		            						cipher.init(Cipher.DECRYPT_MODE, key);
								            // encrypt plaintext, print byte array
								            ptbytes = cipher.doFinal(ciphertext);

								            if(new String(ptbytes).equals(plaintext)) {
								            	System.out.println("Key found:");
								            	Utils.printByteArray(kbytes);
								            	break;
								            }

		        						}
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