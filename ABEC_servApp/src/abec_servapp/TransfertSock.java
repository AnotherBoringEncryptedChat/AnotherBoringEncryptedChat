/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec_servapp;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;
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
        System.out.println("---------------------- Procédure principale du Thread de gestion de transfert socket");

        Serveur_manage serverManage = new Serveur_manage();
        InputStream in = null;
        DataInputStream entree = null;
        while(client.getConnexion()){
            try {
                in = client.getSocket().getInputStream();
                entree = new DataInputStream(in);
                String msg;
                msg = entree.readUTF();
                // FIRST CONNEXION OF THE CLIEN
                if (!msg.contains("--Send file :")) {
                    serverManage.sendMessage(this.server, this.client, msg);
                    System.out.println("Send :   " + msg);
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
}