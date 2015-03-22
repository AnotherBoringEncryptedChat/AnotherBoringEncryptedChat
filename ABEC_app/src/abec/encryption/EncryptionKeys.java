package abec.encryption;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionKeys {
    
    public static KeyPair keyPairGenerator () throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
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
    
    public static SecretKey KeyGenerator() throws NoSuchAlgorithmException{
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        return kg.generateKey();
    }
    
    public static byte[] encryptAES(byte[] data, SecretKey key) throws NoSuchAlgorithmException, 
            InvalidKeyException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            NoSuchPaddingException{
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(data);
    }
    
    public static byte[] decryptAES(byte[] data, SecretKey key) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, 
            InvalidKeyException, 
            IllegalBlockSizeException, 
            BadPaddingException{
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, key);
        return c.doFinal(data);
    }
    
    public static byte[] encryptAES(byte[] data, SecretKeySpec key) throws NoSuchAlgorithmException, 
            InvalidKeyException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            NoSuchPaddingException{
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(data);
    }
    
    public static byte[] decryptAES(byte[] data, SecretKeySpec key) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, 
            InvalidKeyException, 
            IllegalBlockSizeException, 
            BadPaddingException{
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, key);
        return c.doFinal(data);
    }
    
    public static String encryptString(String sToEncrypt, String key){
        String toReturn = "";
        int cpt = 0;
        for(char c : sToEncrypt.toCharArray()){
            char encodingChar = key.charAt(cpt);

            int diff = Character.valueOf(encodingChar).compareTo(Character.valueOf('A'));
            int element = ((int)c +diff - (byte)'A')%26;
            byte newByte = (byte)( element + (byte)'A' );
            toReturn += (char) newByte;
            cpt = (cpt+1)%key.length();
        }
        
        return toReturn;
    }
    
    public static String decryptString(String sToDecrypt, String key){
        
        String toReturn = "";
        int cpt = 0;
        
        for(char c : sToDecrypt.toCharArray()){
            char encodingChar = key.charAt(cpt);
            
            int diff = Character.valueOf(encodingChar).compareTo(Character.valueOf('A'));
            int element = (26 + ((int)c -diff - (byte)'A') )%26;
            byte newByte = (byte)( element + (byte)'A' );
            toReturn += (char)newByte;
            cpt = (cpt+1)%key.length();
        }
        
        return toReturn;
    }
}
