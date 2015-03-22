package abec.encryption;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class EncryptionKeys {
    
    public static KeyPair keyPairGenerator () throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(512);
        KeyPair kp = kpg.generateKeyPair();
        return kp;
    }
    public static byte[] encrypt (byte[] inputBytes, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(inputBytes);
    }
    public static byte[] decrypt (byte[] inputBytes, PrivateKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(inputBytes);
    }
    
    public static PublicKey getPublicKeyFromByteArray(byte[] array) 
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(array));
    }
    public static String retrieveKey(byte[] b) {	
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i]);
			if (s.length() < 2) {
				sb.append("0" + Integer.toHexString((0xFF) & b[i]));
			}
			else {
				sb.append(Integer.toHexString((0xFF) & b[i]));
			}
		}
		return sb.toString();
	}    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
