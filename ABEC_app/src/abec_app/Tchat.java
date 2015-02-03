/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Tchat.java
 *
 * Created on 20 févr. 2014, 15:09:36
 */
package abec_app;

import java.io.File;
import javax.swing.*;

/**
 * Fenêtre de tchat principale
 */
public class Tchat extends javax.swing.JFrame {

    private final Client_info client;
    private final Client_manage clientManager;
    private boolean connexionOK;

    public Tchat(Client_info client) {
        System.out.println("-------Public Tchat()");
        this.client = client;
        this.clientManager = new Client_manage();
        this.client.setConnexion(true);
        this.connexionOK = true;
        //client.writeClient(client);
        //client.setTchat(this);
        String newConnection = "\t -- " + this.client.getPseudo() + " join the tchat --" ;
        System.out.println("uuuuuuuuuuuuid : " + client.getUUID().toString());
        this.clientManager.sendMessage(this.client, client.getUUID().toString());
        this.clientManager.sendMessage(this.client, client.getPseudo());
        this.clientManager.sendMessage(this.client, newConnection);

        initComponents();

        //jTextField2.setText(SelectionServ.hostname);
        //jTextField3.setText(String.valueOf(SelectionServ.port));
        jTextField5.setText(client.getPseudo());
        
        Client_Com ThClient = new Client_Com(this.client,this);
        ThClient.start();


    }

    public boolean getEtatConnexion() {
        return this.connexionOK;
    }

    public void setEtatConnexion(boolean b) {
        this.connexionOK = b;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(231, 233, 241));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(7, 8, 35));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMargin(new java.awt.Insets(5, 8, 5, 8));
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField1.setMargin(new java.awt.Insets(8, 10, 8, 10));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Serveur :");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Port : ");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton1.setText("Envoyer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton2.setText("Vider la conversation");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton4.setText("Se déconnecter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton3.setText("Envoyer un bip");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton5.setText("Envoyer un fichier");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jMenu1.setBackground(new java.awt.Color(204, 204, 255));
        jMenu1.setText("Menu");
        jMenu1.setBorderPainted(true);
        jMenu1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setMargin(new java.awt.Insets(0, 25, 0, 5));
        jMenu1.setMaximumSize(new java.awt.Dimension(90, 32767));
        jMenu1.setMinimumSize(new java.awt.Dimension(70, 0));

        jMenuItem3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jMenuItem3.setText("Mode d'emploi");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jMenuItem2.setText("Se déconnecter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jMenuItem1.setText("Quitter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Options");

        jMenuItem5.setText("Change name");
        jMenu2.add(jMenuItem5);

        jMenuItem4.setText("Start a private tchat");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jTextField5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

}//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("---------------------- ButtonSendMessage()");
        String msg;
        msg = jTextField1.getText();
        if (!msg.isEmpty() && !msg.contains("--popup:")) {msg = client.getPseudo() + " : " + msg;}
        else if (msg.contains("--popup:")) { 
            msg = msg.replaceAll("--popup:", "");
            //JOptionPane.showMessageDialog(null, "Opération bloquée par mesure de sécurité", "Information", JOptionPane.ERROR_MESSAGE);
        }
        clientManager.sendMessage(this.client,msg);
        jTextField1.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            jButton1.doClick(0);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        int answer = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(this, "Are you sure to quit the tchat ? ", "Warning", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION){
            this.connexionOK = false;
            this.dispose();            
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // MENU > FICHIER > SE DECONNECTER
        System.out.println("Déconnexion (Menu/Fichier/SeDeconnecter");
        this.connexionOK = false;
        this.dispose();

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //ModeDemploi mode = new ModeDemploi();
        //mode.setLocationRelativeTo(null);
        //mode.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jMenuItem2.doClick();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextArea1.setText(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //client.envoiMsg("/bip");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        System.out.println("---------------------- JOptionPane()");
        JFileChooser fileSelecter = new JFileChooser();
            fileSelecter.setMultiSelectionEnabled(true) ;
            int value = fileSelecter.showOpenDialog(this);
            if(value == JFileChooser.APPROVE_OPTION){
                File[] fs = fileSelecter.getSelectedFiles();
                for(File f : fs){
                    clientManager.sendFile(this.client,new File(f.getAbsolutePath()));
                }
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        char key = evt.getKeyChar();
        if(key=='\n'){
            this.client.setPseudo(jTextField5.getText());
            System.out.println("NEW PSEUDO : " + jTextField5.getText());
            this.clientManager.sendMessage(this.client, "--ChangeName: " + this.client.getPseudo());
        }
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed


    public JTextArea getJTextArea(){
        return this.jTextArea1;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
