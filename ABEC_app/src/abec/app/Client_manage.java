package abec.app;

import abec.encryption.EncryptionKeys;
import java.io.*;
import java.security.KeyPair;

import javax.swing.*;

public class Client_manage{
	
	private KeyPair keys;
	
    public Client_manage(){}
    
    ////////////////////////////////////////////////////////////////////////////////
    // Envoi de message sur la socket 
    public void sendMessage(Client_info client,String msg) {
        System.out.println("---------------------- sendMessage()");
        try {
            // Recuperation of output stream
            OutputStream out = client.getSocket().getOutputStream();
            DataOutputStream sortie = new DataOutputStream(out);

            if (!msg.isEmpty()) sortie.writeUTF(EncryptionKeys.encrypt(msg.getBytes(), client.getKeys().getPublic()).toString());
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
