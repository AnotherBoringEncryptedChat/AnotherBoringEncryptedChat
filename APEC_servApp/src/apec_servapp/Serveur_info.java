/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apec_servapp;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;

/**
 *
 * @author Max
 */
public class Serveur_info {
    
    //private int nbClient;
    private ServerSocket listenerSocket;
    private ConcurrentHashMap<Integer, Client_info> tabClients;
    private boolean connexionOK;
    
    public Serveur_info(int p){
        
        try{
        System.out.println("**** Lancement du serveur sur le port " + p + " ****");
        //nbClient = 0;
        listenerSocket = new ServerSocket(p);
        tabClients = new ConcurrentHashMap<>();
        connexionOK = true;
        }catch (BindException e) {
            connexionOK = false;
            System.out.println("Error : Un serveur est déjà lancé !");
            //System.exit(-1);
        }catch(IOException e){
            connexionOK = false;
            e.printStackTrace(System.out);
        }
    }
    
    public ConcurrentHashMap<Integer,Client_info> getHashMap(){
        return this.tabClients;
    }
    
    public ServerSocket getServerSocket(){
        return this.listenerSocket;
    }

}
