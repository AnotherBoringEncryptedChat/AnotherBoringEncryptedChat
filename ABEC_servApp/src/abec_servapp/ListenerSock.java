/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec_servapp;
import java.net.*;
import java.io.*;
import java.util.UUID;

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
            client = this.getNameInInputClient(client);
            this.serveur.getHashMap().put(client.getNumClient(),client);
            System.out.println(" HASHMAP.put(client)");        
            TransfertSock ThTransfert = new TransfertSock(this.serveur, client);
            ThTransfert.start();
        }
    }
    
    public Client_info getNameInInputClient(Client_info client) {
        System.out.println("---------------------- getNameInputClient()");
        try {
            InputStream in = transfer_socket.getInputStream();
            DataInputStream sortie = new DataInputStream(in);
//            System.out.println(sortie.readUTF());
            UUID uuid = UUID.fromString(sortie.readUTF());
            System.out.println("uuid :"+ uuid.toString());
            String pseudo = sortie.readUTF();
            System.out.println("nom :"+ pseudo);
            client.setPseudo(pseudo);
            client.setNumClient(uuid);
            client.setSocket(transfer_socket);
        } catch (Exception e) {
           e.printStackTrace(System.out);
        }
        return client;
    }
}
        