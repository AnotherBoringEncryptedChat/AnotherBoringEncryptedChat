/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec.servapp;
import abec.encryption.EncryptionKeys;
import java.io.IOException;
import java.net.*;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Max
 */
public class Serveur_info {
    
    //private int nbClient;
    private ServerSocket listenerSocket;
    private ConcurrentHashMap<UUID, Client_info> tabClients;
    private boolean connexionOK;
    private KeyPair keys;
    
    public Serveur_info(int p){
        
        try{
        System.out.println("**** Lancement du serveur sur le port " + p + " ****");
        //nbClient = 0;
        listenerSocket = new ServerSocket(p);
        tabClients = new ConcurrentHashMap<>();
        connexionOK = true;
        keys = EncryptionKeys.keyPairGenerator();
        }catch (BindException e) {
            connexionOK = false;
            System.out.println("Error : Un serveur est déjà lancé !");
            //System.exit(-1);
        }catch(IOException e){
            connexionOK = false;
            e.printStackTrace(System.out);
        }catch(NoSuchAlgorithmException e ){
            connexionOK = false;
            e.printStackTrace();
        }
        catch(Exception e){
            connexionOK = false;
            e.printStackTrace();
        }
    }
    
    public ConcurrentHashMap<UUID,Client_info> getHashMap(){
        return this.tabClients;
    }
    
    public ServerSocket getServerSocket(){
        return this.listenerSocket;
    }

    /**
     * @return the keys
     */
    public KeyPair getKeys() {
        return keys;
    }

    /**
     * @param keys the keys to set
     */
    public void setKeys(KeyPair keys) {
        this.keys = keys;
    }

}
