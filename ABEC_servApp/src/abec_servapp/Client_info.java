/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec_servapp;
import java.net.*;

/**
 *
 * @author Max
 */
public class Client_info {
    
    private static int num = 0;
    private final int numClient;
    private Socket socket;
    private String pseudo;
    private boolean connexionOK;
    
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
    
    public int getNumClient(){
        return numClient;
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
}
