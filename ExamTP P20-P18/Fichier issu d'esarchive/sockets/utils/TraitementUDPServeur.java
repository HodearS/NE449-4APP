package fr.esisar.NE442.sockets.utils;

import java.net.InetSocketAddress;

public class TraitementUDPServeur extends Thread{

	private ServeurUDP serveurUDP;
	private InetSocketAddress client;
	private String message;
	private TraitementUDPServeurInterface traitement;

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public TraitementUDPServeur(ServeurUDP serveurUDP, InetSocketAddress client, String message, TraitementUDPServeurInterface traitement){
		this.serveurUDP = serveurUDP;
		this.client = client;
		this.message = message;
		this.traitement = traitement;
	}
	
	/*************/
	/** GET/SET **/
	/*************/
	
	public InetSocketAddress getClient() {
		return client;
	}
	public void setClient(InetSocketAddress client) {
		this.client = client;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {
			traitement.run(serveurUDP, client, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
