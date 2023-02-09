package fr.esisar.NE442.sockets.utils;

import java.net.DatagramSocket;

public class ClientUDP extends ServiceUDP {

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ClientUDP(String nom) throws Exception {
		super(nom);
		this.socket = new DatagramSocket();
	}
}
