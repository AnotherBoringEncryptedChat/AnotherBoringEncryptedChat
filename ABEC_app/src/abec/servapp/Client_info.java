/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec.servapp;
import java.net.*;
import java.security.PublicKey;
import java.util.UUID;

/**
 *
 * @author Max
 */
public class Client_info {
    
    private static int num = 0;
    
    //Cette variable est marquée comme inutilisée,
    // ON LA GARDE ?
    private final int numClient;
    // 
    private Socket socket;
    private String pseudo;
    private boolean connexionOK;
    private PublicKey pk;
    private UUID uuid;
    
    public Client_info(){
        numClient = ++num;
        connexionOK = false;
    }
    
    public void setConnexion(boolean statut){
        this.connexionOK = statut;
    }
    
    public boolean getConnexion(){
        return this.connexionOK;
    }
    
    public UUID getNumClient(){
        return uuid;
    }
    
    public String getPseudo(){
        return this.pseudo;
    }
    
    public void setPseudo(String p){
        this.pseudo = p;
    }
    
    public Socket getSocket(){
        return this.socket;
    }
    
    public void setSocket(Socket s){
        this.socket = s;
    }

    public PublicKey getPk() {
        return pk;
    }

    public void setPk(PublicKey pk) {
        this.pk = pk;
    }

    public void setNumClient(UUID uuid2) {
            this.uuid = uuid2;

    }
}
