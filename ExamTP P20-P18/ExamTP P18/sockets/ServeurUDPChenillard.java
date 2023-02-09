package fr.esisar.NE442.sockets;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import fr.esisar.NE442.sockets.utils.ServeurUDP;

public class ServeurUDPChenillard extends ServeurUDP{

	private String ip_suivante;
	private int port_suivant;
	private JFrame frame;

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un serveur UDP pour chenillard.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param ip_suivante Ip du prochain serveur
	 * @param port_suivant Port du prochain serveur
	 * @throws Exception
	 */
	public ServeurUDPChenillard(String nom, int port, String ip_suivante, int port_suivant) throws Exception {
		super(nom, port);
		this.ip_suivante = ip_suivante;
		this.port_suivant = port_suivant;
		
		this.frame = new JFrame(nom);
		this.frame.addMouseListener( new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
			       try {
			    	   if(e.getButton() == MouseEvent.BUTTON1)
			    		   envoyer("rouge", ip_suivante, port_suivant);
			           else
			        	   envoyer("stop", ip_suivante, port_suivant);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});	
		this.frame.setSize(300,300);
		setColor(Color.GREEN);
	}

	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run() {
		String message;
		try {
			ouvrir();
			do {
				message = this.attendreMessage();	
				if(!message.contains("stop")){
					setColor(Color.RED);
					Thread.sleep(500);
					setColor(Color.GREEN);
					this.envoyer("rouge", ip_suivante, port_suivant);
				}else {
					this.envoyer("stop", ip_suivante, port_suivant);				
				}
			}while(!message.contains("stop"));
			frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**************/
	/** METHODES **/
	/**************/
	
	public void setColor(Color color) {
		frame.getContentPane().setBackground(color);
		frame.setVisible(true);
	}
}
