/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apec_app;

/**
 *
 * @author Max
 */
public class Client_Com extends Thread{
    
    private final Client_info clientInfo;
    private final Client_manage clientManager;
    private final Tchat tchat;
    
    public Client_Com(Client_info clinfo,Tchat tchat){
        super();
        System.out.println("---------------------- Lancement du Thread de gestion du tchat");
        this.clientInfo = clinfo;
        this.clientManager = new Client_manage();
        this.tchat = tchat;
    }
    
    @Override
    public void run(){
        
        System.out.println("---------------------- Procédure principale du thread client");
        while(clientInfo.isConnected == true && tchat.getEtatConnexion() == true){
            String msg;
            msg = this.clientManager.ReceiveMessage(clientInfo);
            if (msg.contains("--Send file :")){
                   this.clientManager.receiveFile(null, msg, msg);
            }
            else if(msg.contains("--popup:Nobody")){
                msg = "Personne n'est connecté au serveur.";
            }
            tchat.getJTextArea().append(msg + "\n");
        }
        System.out.println("---------------------- Fin de la procédure principale du thread client");
        
    }
}
