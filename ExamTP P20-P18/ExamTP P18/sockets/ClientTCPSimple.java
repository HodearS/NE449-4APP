package fr.esisar.NE442.sockets;

import java.net.Socket;

import fr.esisar.NE442.sockets.utils.ClientTCP;
import fr.esisar.NE442.sockets.utils.TraitementTCPClientInterface;

public class ClientTCPSimple extends ClientTCP{

	private String ip_serveur;
	private int port_serveur;
	private TraitementTCPClientInterface traitement;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un client TCP simple. Il se connecte au serveur, réalise un traitement et se deconnecte.
	 * @param nom Nom du client
	 * @param ip_serveur Ip du serveur
	 * @param port_serveur Port du serveur
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementTCPClientInterface".
	 * @throws Exception
	 */
	public ClientTCPSimple(String nom, String ip_serveur, int port_serveur, TraitementTCPClientInterface traitement) throws Exception {
		super(nom);
		this.ip_serveur = ip_serveur;
		this.port_serveur = port_serveur;
		this.traitement = traitement;
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {
			Socket serveur = connexion(ip_serveur, port_serveur);
			traitement.run(this, serveur);
			deconnexion(serveur);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
