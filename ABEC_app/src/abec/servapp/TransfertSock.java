/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec.servapp;

import abec.encryption.EncryptionKeys;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
/**
 *
 * @author Max
 */
public class TransfertSock extends Thread {
    
    private final Client_info client;
    private final Serveur_info server;
    
    public TransfertSock(Serveur_info server, Client_info client){
        super();
        System.out.println("---------------------- TransfertSock()");
        this.client = client;
        this.client.setConnexion(true);
        this.server = server;
    }
    
    @Override
    public void run(){
        System.out.println("---------------------- Main transfer Socket Thread");

        Serveur_manage serverManage = new Serveur_manage();
        InputStream in = null;
        DataInputStream entree = null;
        getClientPublicKey();
        sendMyPublicKey(serverManage);
        
        while(client.getConnexion()){
            try {
                in = client.getSocket().getInputStream();
                entree = new DataInputStream(in);
                String readMsg = entree.readUTF();
                /*
                Debug
                */
                System.out.println("Entry Message : "+readMsg);
                /*
                Debug
                */
                
                String msg;
                try{
                    byte[] decryptedMsg = EncryptionKeys.decrypt(readMsg.getBytes(), server.getKeys().getPrivate());
                    msg = new String(decryptedMsg);
                    
                    /*
                    DEBUG
                    */
                    System.out.println(msg);
                    /*
                    DEBUG
                    */
                    
                    // FIRST CONNEXION OF THE CLIENT
                    if (!msg.contains("--Send file :")) {
                        serverManage.sendMessage(this.server, this.client, msg);
                        System.out.println("Send :   " + msg);
                    }
                    else if (msg.contains("--ChangeName:")){this.server.getHashMap().get(client.getNumClient()).setPseudo(msg.substring(12));}
                    
                    else if (msg.contains(" --getUserList:")){
                    	serverManage.sendMessage(server, client, String.valueOf(this.server.getHashMap().size()));
                    	for(Client_info cli : this.server.getHashMap().values()){
                    		serverManage.sendMessage(server, cli, cli.getPseudo());
                    	}
                    }
                    else{
                         serverManage.sendMessage(server, client, msg);

                        //RECUPERATION NOM FICHIER
                        String fileName = entree.readUTF();
                        serverManage.sendFileInfo(fileName, client, server);
                        String fileSize = entree.readUTF();
                        serverManage.sendFileInfo(fileSize,client, server);
                        byte b[] = new byte[Integer.valueOf(fileSize)];
                        try{
                            entree.read(b, 0, Integer.valueOf(fileSize));
                            serverManage.sendFile(server, client, b);
                        }catch(IOException e){ e.printStackTrace(System.out);}

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }catch (IOException e) {
                e.printStackTrace(System.out);
                server.getHashMap().remove(client.getNumClient());
                client.setConnexion(false);
            }
        }
        try{
            if (in != null) in.close();
            if (entree != null) entree.close();
            client.getSocket().close();
        }catch(IOException e){e.printStackTrace(System.out);}
    }
    
    
    public void getClientPublicKey(){
        if(client.getConnexion()){
            InputStream in = null;
            DataInputStream entree = null;
            try{
                in = client.getSocket().getInputStream();
                entree = new DataInputStream(in);
                String messageAsString = entree.readUTF();
                byte[] readMsg = messageAsString.getBytes();
                System.out.println("client public key -> "+ messageAsString );
                client.setPk(EncryptionKeys.getPublicKeyFromByteArray(readMsg));
            }catch(NoSuchAlgorithmException | InvalidKeySpecException | IOException e)
            {
                client.setConnexion(false);
                e.printStackTrace();
            }
        }
    } 
    
    public void sendMyPublicKey(Serveur_manage server_manage){
        
        if(client.getConnexion()){
            server_manage.sendMessageUnencrypted(server, client, new String(server.getKeys().getPublic().getEncoded()));
        }
    }
}
