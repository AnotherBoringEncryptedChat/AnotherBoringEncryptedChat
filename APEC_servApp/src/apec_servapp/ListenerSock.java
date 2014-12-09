/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apec_servapp;
import java.net.*;
import java.io.*;

/**
 *
 * @author Max
 */
public class ListenerSock extends Thread{
    
    private Socket transfer_socket;
    private final Serveur_info serveur;
    
    public ListenerSock(Serveur_info serveur){
        super();
        System.out.println("---------------------- ListenerSock()");
        this.serveur = serveur;
    }
    
    @Override
    public void run(){
        System.out.println("---------------------- Procédure principale de ListenerSock()");
        //Serveur_manage manager = new Serveur_manage();
        while (true){
            try{
                transfer_socket = this.serveur.getServerSocket().accept();
                System.out.println("CONNEXION CLIENT");
            }catch(IOException e){e.printStackTrace(System.out);}
            Client_info client = new Client_info();
            client.setSocket(transfer_socket);
            this.getNameInInputClient();
            this.serveur.getHashMap().put(client.getNumClient(),client);
            System.out.println(" HASHMAP.put(client)");        
            TransfertSock ThTransfert = new TransfertSock(this.serveur, client);
            ThTransfert.start();
        }
    }
    public Client_info getNameInInputClient() {
        System.out.println("---------------------- getNameInputClient()");
        Client_info client = new Client_info();
        try {
            InputStream in = transfer_socket.getInputStream();
            DataInputStream sortie = new DataInputStream(in);
            String pseudo = sortie.readUTF();
            client.setPseudo(pseudo);
            client.setSocket(transfer_socket);
        } catch (Exception e) {
           e.printStackTrace(System.out);
        }
        return client;
    }
}
        