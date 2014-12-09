/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apec_app;


import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Max
 */
public class Client_info {
    private Socket socket;
    private String pseudo;
    // On suppose que la connexion est valide -- Passe Ã  false si l'exception est levÃ©e
    public boolean isConnected;
    //private Tchat tchat = null;

////////////////////////////////////////////////////////////////////////////////
    public Client_info() {
        try {
            socket = new Socket("localhost", 30000);
            pseudo = null;
            isConnected = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connexion au serveur impossible", "Serveur injoignable", JOptionPane.ERROR_MESSAGE);
            isConnected = false;
        }
    }
////////////////////////////////////////////////////////////////////////////////

    public void setPseudo(String p) {
        pseudo = p;
    }

    public void setConnexion(boolean c){
        this.isConnected = c;
    }
    
    public boolean getConnexion(){
        return this.isConnected;
    }
////////////////////////////////////////////////////////////////////////////////

    public String getPseudo() {
        return pseudo;
    }


    public Socket getSocket() {
        return socket;
    }

////////////////////////////////////////////////////////////////////////////////

    public String getHost() {
        InetAddress ip = socket.getInetAddress();
        return (ip.getHostAddress());
    }

////////////////////////////////////////////////////////////////////////////////
//    public void writeClient(Client_info c) {
//        try {
//            OutputStream out = socket.getOutputStream();
//            DataOutputStream sortie = new DataOutputStream(out);
//            sortie.writeUTF(c.getPseudo());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//
//    }
}
