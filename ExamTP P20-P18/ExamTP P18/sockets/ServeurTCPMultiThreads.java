package fr.esisar.NE442.sockets;

import java.net.Socket;
import java.util.ArrayList;

import fr.esisar.NE442.sockets.utils.ServeurTCP;
import fr.esisar.NE442.sockets.utils.TraitementTCPServeur;
import fr.esisar.NE442.sockets.utils.TraitementTCPServeurInterface;

public class ServeurTCPMultiThreads extends ServeurTCP{

	private TraitementTCPServeurInterface traitement;
	private ArrayList<TraitementTCPServeur> traitements;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un serveur TCP multi threads. Les clients sont traités en paralèlle.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementTCPServeurInterface".
	 * @throws Exception
	 */
	public ServeurTCPMultiThreads(String nom, int port, TraitementTCPServeurInterface traitement) throws Exception {
		super(nom, port);
		this.traitement = traitement;
		this.traitements = new ArrayList<TraitementTCPServeur>();
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {			
			ouvrir();
			while(true){
				Socket client = attendreConnexion();
				traitements.stream().filter(t -> !t.isAlive()).forEach(t -> traitements.remove(t));
				TraitementTCPServeur traitementTCP = new TraitementTCPServeur(this, client, traitement);
				traitements.add(traitementTCP);
				traitementTCP.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fermer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
