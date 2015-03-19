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
}
