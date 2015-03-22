/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec.servapp;

import abec.encryption.ClefDuJour;
import abec.encryption.EncryptionKeys;
import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 *
 * @author Max
 */
public class Serveur_manage {
    
        private String clefDuJour;

    public String getClefDuJour() {
        return clefDuJour;
    }
        public Serveur_manage(){
            
            ClefDuJour cj = new ClefDuJour();
            cj.generateKeys();
            
            clefDuJour = cj.getKeyOfTheDay();
        }
        
        public void sendMessage(Serveur_info server, Client_info client, String msg) {
            System.out.println("---------------------- sendMessage();");
            OutputStream out = null;
            DataOutputStream sortie = null;
            for (UUID i : server.getHashMap().keySet()) {
                Socket socket_transfert = server.getHashMap().get(i).getSocket();
                // Récupécration du flot de sortie
                // Création du flot d'entrée pour données typées 
                try{
                    out = socket_transfert.getOutputStream();
                    sortie = new DataOutputStream(out);
                    if (server.getHashMap().size() <= 1 && !msg.isEmpty()) msg = "--popup:Nobody";
                    try{
                        sortie.writeUTF(EncryptionKeys.encryptString(msg, this.clefDuJour));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }catch(IOException e){ e.printStackTrace(System.out);}
            }
        System.out.println("Broadcast > " + msg);

    }
        
        public void sendMessageUnencrypted(Serveur_info server, Client_info client, String msg) {
            System.out.println("---------------------- sendMessageUnencrypted();");
            OutputStream out = null;
            DataOutputStream sortie = null;
            for (UUID i : server.getHashMap().keySet()) {
                Socket socket_transfert = server.getHashMap().get(i).getSocket();
                // Récupération  du flot de sortie
                // CrÃ©ation du flot d'entrée pour données typées 
                try{
                    out = socket_transfert.getOutputStream();
                    sortie = new DataOutputStream(out);
//                    if (server.getHashMap().size() <= 1 && !msg.isEmpty()) msg = "--popup:Nobody";
                    try{
                        sortie.writeUTF(msg);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }catch(IOException e){ e.printStackTrace(System.out);}
            }
        System.out.println("Broadcast > " + msg);

    }

    public void sendFile(Serveur_info server,Client_info client,byte b[] ) {
        System.out.println("---------------------- sendFile()");
        for (UUID i : server.getHashMap().keySet()) {
            if (i != client.getNumClient()){
                OutputStream out = null;
                Socket socket_transfert = server.getHashMap().get(i).getSocket();
                // Récupération du flot de sortie
                try{
                     out = socket_transfert.getOutputStream();
                } catch (IOException e) {
                    System.out.println("Problème Broadcasting de message (Byte-Type");
                    e.printStackTrace(System.out);
                }
                // Création du flot d'entrée pour données typées 
                DataOutputStream sortie = new DataOutputStream(out);
                try{
                    sortie.write(b);
                    if (out != null) out.close();
                    sortie.close();
                }catch(IOException e){ e.printStackTrace(System.out);}
            }
        }
    }
    
    public void sendFileInfo(String nom, Client_info client, Serveur_info server) {
        System.out.println("---------------------- sendFileInfo()");
        System.out.println("Broadcast file > " + nom + " to :");
        try {
            for (UUID i : server.getHashMap().keySet()) {
                if (i != client.getNumClient()) {
                    System.out.println("\t " + client.getPseudo());
                    // Récupération du flot de sortie
                    OutputStream out = server.getHashMap().get(i).getSocket().getOutputStream();
                    // Création du flot d'entrée pour données typées 
                    DataOutputStream sortie = new DataOutputStream(out);
                    sortie.writeUTF(nom);
                    sortie.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Problème Broadcasting de message");
            e.printStackTrace(System.out);
        }
    }
     
}
