/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abec_servapp;
/**
 *
 * @author Max
 */
public class ABEC_servApp {

    
    static Serveur_info server;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int portConnexion = 30000;
        
        server = new Serveur_info(portConnexion);

        ListenerSock listener;
        listener = new ListenerSock(server);
        listener.start();
        
    }
    
}
