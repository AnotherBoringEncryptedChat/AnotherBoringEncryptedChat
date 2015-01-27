/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//http://www.ibm.com/developerworks/java/tutorials/j-sec1/j-sec1.html
//http://www.mkyong.com/java/jce-encryption-data-encryption-standard-des-tutorial/
//http://www.giuseppeurso.eu/en/asymmetric-rsa-encryption-in-java/
//http://docs.oracle.com/javase/7/docs/api/java/security/KeyPairGenerator.html
//https://github.com/giuseppeurso-eu/sample-security/blob/master/src/test/java/eu/giuseppeurso/security/crypto/AsymmetricCipherTest.java
//http://stackoverflow.com/questions/8480918/java-how-to-create-a-rsa-public-key-from-the-string


package encryptionprototype;

import java.util.Scanner;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.*;

/**
 *
 * @author Quagliatini jordan
 */
public class Main {
    public static int cpt=0;
    
    public void Main(){
        cpt ++;
    }
    
    public static void main(String args[]) {
        Scanner s;
        s = new Scanner(System.in);
        String line;
        
        ZonedDateTime now;
        KeyPair keys;
        try{
            keys = EncryptionPrototype.keyPairGenerator();
            PublicKey pubKey = keys.getPublic();
            PrivateKey privKey = keys.getPrivate();
            
            try{
                writePublicKeyToFile(pubKey);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
            FileInputStream in;
            System.out.println("Name of the public key file of client: ");
            line = s.nextLine();
            PublicKey foreignPublicKey;
            foreignPublicKey = readPubKeyFromFile(line);
            
            System.out.println("Write a sentence to encrypt:");
            line = s.nextLine();
            
            writeEncryptedStringToFile(line.getBytes(), foreignPublicKey);
            
            System.out.println("Tell me the encrypted String file:");
            line = s.nextLine();
            
            
            
            System.out.println("The initial string was :");
            System.out.println(readEncryptedForeignString(line,privKey));
        }catch(NoSuchAlgorithmException e ){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void writeEncryptedStringToFile(byte[] string, PublicKey pk) throws Exception{
        byte[] encryptedString;
        encryptedString = EncryptionPrototype.encrypt(string, pk);
        Path out;
        out = Paths.get(getTimerFileName());
        Files.write(out, encryptedString);
    }
    
    private static void writePublicKeyToFile(PublicKey pk) throws IOException{
        Path out = Paths.get(getTimerFileName());
        byte[] b = pk.getEncoded();
        if (b  == null){
            throw new IOException("The key doesn't supports enconding");
        }else{
            Files.write(out,b);
        }
    }
    
    private static PublicKey readPubKeyFromFile(String file) throws NoSuchAlgorithmException, 
                                                                      InvalidKeySpecException, 
                                                                      IOException,
                                                                      OutOfMemoryError,
                                                                      SecurityException,
                                                                      Exception {
        Path pathFile;
        pathFile = Paths.get(file);
        
        byte[] pubKeyReadFromFile;
        pubKeyReadFromFile = Files.readAllBytes(pathFile);
        
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubKeyReadFromFile));
    }
    
    private static byte[] readEncryptedForeignString(String file, PrivateKey pk) throws IOException,
                                                                                            OutOfMemoryError,
                                                                                            SecurityException,
                                                                                            Exception{
        Path pathFile;
        pathFile = Paths.get(file);
        
        byte[] returnedValue;
        returnedValue = Files.readAllBytes(pathFile);
        
        return EncryptionPrototype.decrypt(returnedValue, pk);
    }
    
    private static String getTimerFileName(){
        ZonedDateTime now;
        now = ZonedDateTime.now();
        
        return "pubKey_"+now.getHour()+now.getMinute()+now.getSecond();
    }
}
