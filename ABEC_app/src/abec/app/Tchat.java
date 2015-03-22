/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Tchat.java
 *
 * Created on 20 févr. 2014, 15:09:36
 */
package abec.app;

import abec.encryption.EncryptionKeys;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main Chat Window
 */
public class Tchat extends javax.swing.JFrame {

	private final Client_info client;
	private final Client_manage clientManager;
	private boolean connexionOK;
	private javax.swing.JTextArea _TA_Main = new JTextArea();
	ArrayList<JTextArea> TA_List = new ArrayList<>();

	public Tchat(Client_info client) throws IOException {
		System.out.println("-------Public Tchat()");
		this.client = client;
		this.clientManager = new Client_manage();
		this.client.setConnexion(true);
		this.connexionOK = true;
		// client.writeClient(client);
		// client.setTchat(this);
		String newConnection = "\t -- " + this.client.getPseudo() + " join the tchat --";
		System.out.print(client.getUUID().toString());

		this.clientManager.sendUnencryptedMessage(this.client, client.getUUID().toString());
		this.clientManager.sendUnencryptedMessage(this.client, client.getPseudo());
		this.clientManager.sendUnencryptedMessage(this.client, retrieveKey(client.getKeys().getPublic().getEncoded()));
                
                String receivedKey = this.clientManager.ReceiveMessage(client);
                byte[] receivedKeyByteArray = EncyptionKeys.hexStringToByteArray(receivedKey);
                this.clientManager.setServerPublicKey(EncryptionKeys.getPublicKeyFromByteArray(receivedKeyByteArray));
                
		this.clientManager.sendMessage(this.client, newConnection);

		initComponents();
		_TA_Main.setPreferredSize(new Dimension(360, 500));
		_TA_Main.setSize(new Dimension(99, 100));

		// add general chat

		_tabPanel.addTab("Main", _TA_Main);
		TA_List.add(_TA_Main);

		// set pseudo textbox

		pseudo.setText(client.getPseudo());
		Client_Com ThClient = new Client_Com(this.client, this);
		ThClient.start();

	}

	private String retrieveKey(byte[] b) {	
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i]);
			if (s.length() < 2) {
				sb.append("0" + Integer.toHexString((0xFF) & b[i]));
			}
			else {
				sb.append(Integer.toHexString((0xFF) & b[i]));
			}
		}
		return sb.toString();
	}

	public boolean getEtatConnexion() {
		return this.connexionOK;
	}

	public void setEtatConnexion(boolean b) {
		this.connexionOK = b;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		pseudo = new javax.swing.JTextField();
		_tabPanel = new javax.swing.JTabbedPane();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem3 = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JPopupMenu.Separator();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem1 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(204, 255, 255));

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

		jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
		jButton1.setText("Send");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
		jButton2.setText("Clear Chat");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
		jButton4.setText("Log Out");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jButton3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
		jButton3.setText("A bip");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
		jButton5.setText("Send a file");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		pseudo.setText("jTextField4");
		pseudo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				pseudoActionPerformed(evt);
			}
		});
		pseudo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pseudoKeyReleased(evt);
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
		jMenuItem3.setText("Help");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem3);
		jMenu1.add(jSeparator1);

		jMenuItem2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
		jMenuItem2.setText("Log Out");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
		jMenuItem1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
		jMenuItem1.setText("Quit");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuBar1.add(jMenu1);

		setJMenuBar(jMenuBar1);

		JButton btnPrivateChat = new JButton("Private Chat");

		btnPrivateChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked");
			}
		});

		btnPrivateChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientManager.sendMessage(client, " --getUserList:");
				int nbUser = Integer.valueOf(clientManager.ReceiveMessage(client));

			}
		});

		btnPrivateChat.setFont(new Font("Dialog", Font.PLAIN, 12));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(Alignment.LEADING)
										.addGroup(
												layout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(pseudo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
														.addGroup(
																layout.createSequentialGroup()
																		.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 195,
																				GroupLayout.PREFERRED_SIZE).addGap(20)))
										.addGroup(
												layout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnPrivateChat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
														.addComponent(jButton3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton1))
										.addComponent(_tabPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(37)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(23)
						.addGroup(
								layout.createParallelGroup(Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(pseudo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE).addGap(44)
														.addComponent(btnPrivateChat).addGap(46).addComponent(jButton2)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton3)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton5))
										.addComponent(_tabPanel, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, Short.MAX_VALUE)
						.addGroup(
								layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(
												layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
														.addComponent(jButton4))
										.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)).addGap(43)));
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed

	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		System.out.println("---------------------- ButtonSendMessage()");
		String msg;
		msg = jTextField1.getText();
		if (!msg.isEmpty() && !msg.contains("--popup:")) {
			msg = client.getPseudo() + " : " + msg;
		}
		else if (msg.contains("--popup:")) {
			msg = msg.replaceAll("--popup:", "");
			// JOptionPane.showMessageDialog(null,
			// "Opération bloquée par mesure de sécurité", "Information",
			// JOptionPane.ERROR_MESSAGE);
		}
		clientManager.sendMessage(this.client, msg);
		jTextField1.setText(null);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyPressed

		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			jButton1.doClick(0);
		}
	}// GEN-LAST:event_jTextField1KeyPressed

	private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyTyped

	}// GEN-LAST:event_jTextField1KeyTyped

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed

		int answer = JOptionPane.YES_NO_OPTION;
		JOptionPane.showConfirmDialog(this, "Are you sure to quit the tchat ? ", "Warning", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			this.connexionOK = false;
			this.dispose();
		}

	}// GEN-LAST:event_jMenuItem1ActionPerformed

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem2ActionPerformed
		// MENU > FICHIER > SE DECONNECTER
		System.out.println("Déconnexion (Menu/Fichier/SeDeconnecter");
		this.connexionOK = false;
		this.dispose();

	}// GEN-LAST:event_jMenuItem2ActionPerformed

	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem3ActionPerformed
		// ModeDemploi mode = new ModeDemploi();
		// mode.setLocationRelativeTo(null);
		// mode.setVisible(true);
	}// GEN-LAST:event_jMenuItem3ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		jMenuItem2.doClick();
	}// GEN-LAST:event_jButton4ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		_TA_Main.setText(null);
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		// client.envoiMsg("/bip");
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed

		System.out.println("---------------------- JOptionPane()");
		JFileChooser fileSelecter = new JFileChooser();
		fileSelecter.setMultiSelectionEnabled(true);
		int value = fileSelecter.showOpenDialog(this);
		if (value == JFileChooser.APPROVE_OPTION) {
			File[] fs = fileSelecter.getSelectedFiles();
			for (File f : fs) {
				clientManager.sendFile(this.client, new File(f.getAbsolutePath()));
			}
		}
	}// GEN-LAST:event_jButton5ActionPerformed

	private void pseudoKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_pseudoKeyReleased

		if (evt.getKeyChar() == '\n') {
			int dialogResult = JOptionPane.showConfirmDialog(null, "You are going to change your name, are you sure?", "Warning", 0);

			if (dialogResult == 0) {
				if (pseudo.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "login is empty");
					pseudo.setText(client.getPseudo());
				}
				else {
					clientManager.sendMessage(client, client.getPseudo() + " changed to " + pseudo.getText());
					client.setPseudo(pseudo.getText());
					// jLabel4.setText(client.getPseudo());

					// clientManager.sendMessage(this.client,"--changeName:"+client.getUID+":"+client.getPseudo());
				}

			}
			else if (dialogResult == 1) {
				pseudo.setText(client.getPseudo());

			}
		}

	}// GEN-LAST:event_pseudoKeyReleased

	private void pseudoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pseudoActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_pseudoActionPerformed

	public JTextArea getJTextArea(int i) {
		System.out.println("---------------------- getTextArea()");
		return this.TA_List.get(i);
	}

	public JTextArea getJTextArea() {
		System.out.println("---------------------- getTextArea()");
		return this.TA_List.get(0);
	}

	// Variables declaration - do not modify
	private javax.swing.JTabbedPane _tabPanel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JPopupMenu.Separator jSeparator1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField pseudo;
}