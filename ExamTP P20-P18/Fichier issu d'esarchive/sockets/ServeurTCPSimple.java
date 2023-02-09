package fr.esisar.NE442.sockets;

import java.net.Socket;

import fr.esisar.NE442.sockets.utils.ServeurTCP;
import fr.esisar.NE442.sockets.utils.TraitementTCPServeurInterface;

public class ServeurTCPSimple extends ServeurTCP{
			
	private TraitementTCPServeurInterface traitement;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un serveur TCP multi threads. Un seul client est traité puis le serveur se ferme.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementTCPServeurInterface".
	 * @throws Exception
	 */
	public ServeurTCPSimple(String nom, int port, TraitementTCPServeurInterface traitement) throws Exception {
		super(nom, port);
		this.traitement = traitement;
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {			
			ouvrir();
			Socket client = attendreConnexion();
			traitement.run(this, client);
			deconnexion(client);
			fermer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
