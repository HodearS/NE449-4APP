package fr.esisar.NE442.sockets.utils;

import java.net.Socket;

public class TraitementTCPServeur extends Thread{

	private ServeurTCP serveurTCP;
	private Socket client;
	private TraitementTCPServeurInterface traitement;

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public TraitementTCPServeur(ServeurTCP serveurTCP, Socket client, TraitementTCPServeurInterface traitement){
		this.serveurTCP = serveurTCP;
		this.client = client;
		this.traitement = traitement;
	}
	
	/*************/
	/** GET/SET **/
	/*************/
	
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {
			traitement.run(serveurTCP, client);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
