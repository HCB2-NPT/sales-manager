package helper;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import config.AppConfig;

public class Security {
	private static final String ALGORITHM = "DESede";
	private static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
	private Cipher ecipher;
	private Cipher dcipher;
	
	public Security(String salt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException{
		byte[] key = salt.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 24);

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
		
		ecipher = Cipher.getInstance(CIPHER_ALGORITHM);
		ecipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		
		dcipher = Cipher.getInstance(CIPHER_ALGORITHM);
		dcipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	}
	
	public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }

	private static Security ser = null;
    static {
    	try {
			ser = new Security(AppConfig.SECURITY_SECRET_KEY);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String Encrypt(String text){
    	return ser.encrypt(text);
    }
    
    public static String Decrypt(String text){
    	return ser.decrypt(text);
    }
}
