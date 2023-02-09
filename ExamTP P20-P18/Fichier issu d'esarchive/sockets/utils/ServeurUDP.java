package fr.esisar.NE442.sockets.utils;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServeurUDP extends ServiceUDP {

	private int port;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ServeurUDP(String nom, int port) throws Exception {
		super(nom);
		this.port = port;
		this.socket = new DatagramSocket(null);
	}

	/*************/
	/** SERVEUR **/
	/*************/
	
	/** Ouvre le serveur
	 * @throws Exception
	 */
	public void ouvrir() throws Exception {
		log("Ouverture du serveur sur le port " + port);
		this.socket.bind(new InetSocketAddress(port));
	}
}
