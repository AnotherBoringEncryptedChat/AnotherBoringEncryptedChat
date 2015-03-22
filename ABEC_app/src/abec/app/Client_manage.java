package abec.app;

import abec.encryption.ClefDuJour;
import abec.encryption.EncryptionKeys;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javax.swing.*;

public class Client_manage{
	
	private PublicKey serverPublicKey;
        private String clefDuJour;
	
    public Client_manage(){
        ClefDuJour clefsDuJour = new ClefDuJour();
        clefsDuJour.generateKeys();
        
        clefDuJour = clefsDuJour.getKeyOfTheDay();
    }

    public PublicKey getServerPublicKey() {
        return serverPublicKey;
    }

    public void setServerPublicKey(PublicKey serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }
    
    ////////////////////////////////////////////////////////////////////////////////
    // Envoi de message sur la socket 
    public void sendMessage(Client_info client,String msg) {
        System.out.println("---------------------- sendMessage()");
        try {
            // Recuperation of output stream
            OutputStream out = client.getSocket().getOutputStream();
            DataOutputStream sortie = new DataOutputStream(out);
            
            String encryptedMsg = EncryptionKeys.encryptString(msg, this.clefDuJour);
            sortie.writeUTF(encryptedMsg);
            
            
            System.out.println("Send :   " + msg);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void sendUnencryptedMessage(Client_info client, String msg) {
        System.out.println("---------------------- sendUnencryptedMessage()");
        try {
            // Recuperation of output stream
            OutputStream out = client.getSocket().getOutputStream();
            DataOutputStream sortie = new DataOutputStream(out);

            if (!msg.isEmpty()) sortie.writeUTF(msg);
            System.out.println("Send :   " + msg);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public String ReceiveMessage(Client_info client){
        String msg = null;
        System.out.println("---------------------- ReceiveMessage()");

        try {
            DataInputStream entree = new DataInputStream(client.getSocket().getInputStream());
            msg = entree.readUTF();
        }catch(IOException e){e.printStackTrace(System.out);}
        System.out.println("Receive :   " + msg);
        return msg; 
    }
    
    public String ReceiveEncryptedMessage(Client_info client){
        String msg = null;
        System.out.println("---------------------- ReceiveEncryptedMessage()");

        try {
            DataInputStream entree = new DataInputStream(client.getSocket().getInputStream());
            String readMsg = entree.readUTF();
            byte[] decryptedAESKey = EncryptionKeys.decrypt(readMsg.getBytes(), client.getKeys().getPrivate());
            SecretKeySpec ks = new SecretKeySpec(decryptedAESKey,"AES");
            //On lit le message crypté avec AES
            readMsg = entree.readUTF();
            byte[] decryptedMSG = EncryptionKeys.decryptAES(readMsg.getBytes(), ks);

            msg = new String(decryptedMSG);
        }catch(IOException e){e.printStackTrace(System.out);} catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (NoSuchPaddingException ex) {
                ex.printStackTrace();
            } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            } catch (IllegalBlockSizeException ex) {
                ex.printStackTrace();
            } catch (BadPaddingException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        System.out.println("Receive :   " + msg);
        return msg; 
    }
    
    //////////////////////////////////////////////////////////////////////////////// 
    public void sendFile(Client_info client,File f) {
        System.out.println("---------------------- SendFile()");
        try {
            final int TAILLE_MAX = 20000;

            DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());
            FileInputStream fo = new FileInputStream(f);

            if (f.length() <= TAILLE_MAX) {
            	System.out.println(f.getName());
            	System.out.println(f.length());
                out.writeUTF("--Send file :");
                out.writeUTF(f.getName());
                out.writeLong(f.length());
                byte b[] = new byte[(int) f.length()];
                fo.read(b, 0, (int) f.length());
                out.write(b, 0, (int) f.length());

                fo.close();

            } else JOptionPane.showMessageDialog(null, "Taille du fichier excessive !\n Le fichier ne doit pas faire plus de " + String.valueOf(TAILLE_MAX) + " octets", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void receiveFile(Client_info client, String fname, String fsize) {
        System.out.println("---------------------- receiveFile()");
        DataInputStream entree = null;  
        try {
             entree = new DataInputStream(client.getSocket().getInputStream());
        }catch(IOException ioe){ioe.printStackTrace(System.out);}

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnfile = jfc.showSaveDialog(null);

        if (returnfile != JFileChooser.APPROVE_OPTION){
            File file = new File(jfc.getSelectedFile().getPath() + "//" + fname);
            FileOutputStream fileOut = null;
            try{
                file.createNewFile();
                fileOut = new FileOutputStream(file);
            }catch(IOException e){e.printStackTrace(System.out);}

            byte b[] = new byte[Integer.valueOf(fsize)];
            try{
                if (entree != null && fileOut != null) {
                    entree.read(b);
                    fileOut.write(b, 0, Integer.valueOf(fsize));
                    fileOut.close();
                    entree.close();
                }
            }catch(IOException e){e.printStackTrace(System.out);}
            System.out.println("File received for : " + client.getPseudo());
        }
    }


}
