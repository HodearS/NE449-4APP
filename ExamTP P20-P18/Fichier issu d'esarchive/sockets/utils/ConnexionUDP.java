package fr.esisar.NE442.sockets.utils;

import java.net.InetSocketAddress;

public class ConnexionUDP {

	private InetSocketAddress inetSocketAddress;
	private String message;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ConnexionUDP(InetSocketAddress inetSocketAddress, String message) {
		this.inetSocketAddress = inetSocketAddress;
		this.message = message;
	}
	
	/*************/
	/** GET/SET **/
	/*************/
	
	public InetSocketAddress getInetSocketAddress() {
		return inetSocketAddress;
	}
	public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
		this.inetSocketAddress = inetSocketAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
