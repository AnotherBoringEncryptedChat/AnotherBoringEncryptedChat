package abec.app;


import abec.encryption.EncryptionKeys;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.security.KeyPair;
import java.util.UUID;

/**
 *
 * @author Max
 */
public class Client_info {
    private Socket socket;
    private String pseudo;
    private UUID UID;
    public boolean isConnected;
    private KeyPair keys;
    
    //private Tchat tchat = null;

public KeyPair getKeys() {
		return keys;
	}

	public void setKeys(KeyPair keys) {
		this.keys = keys;
	}

	////////////////////////////////////////////////////////////////////////////////
    public Client_info() {
        try {
            socket = new Socket("localhost", 30000);
            pseudo = null;
            isConnected = true;
            UID = UUID.randomUUID();
            keys = EncryptionKeys.keyPairGenerator();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connexion at the server impossible", "Injoignable Serveur ", JOptionPane.ERROR_MESSAGE);
            isConnected = false;
        }
    }
////////////////////////////////////////////////////////////////////////////////

    public UUID getUUID(){
        return UID;
}
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
