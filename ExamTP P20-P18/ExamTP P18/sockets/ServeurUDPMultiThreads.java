package fr.esisar.NE442.sockets;

import java.util.ArrayList;

import fr.esisar.NE442.sockets.utils.ConnexionUDP;
import fr.esisar.NE442.sockets.utils.ServeurUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPServeur;
import fr.esisar.NE442.sockets.utils.TraitementUDPServeurInterface;

public class ServeurUDPMultiThreads extends ServeurUDP{

	private TraitementUDPServeurInterface traitement;
	private ArrayList<TraitementUDPServeur> traitements;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un serveur UDP multi threads. Les clients sont traités en paralèlle.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementUDPServeurInterface".
	 * @throws Exception
	 */
	public ServeurUDPMultiThreads(String nom, int port, TraitementUDPServeurInterface traitement) throws Exception {
		super(nom, port);
		this.traitement = traitement;
		this.traitements = new ArrayList<TraitementUDPServeur>();
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {	
			ouvrir();		
			while(true){
				ConnexionUDP client = receptionner();
				traitements.stream().filter(t -> !t.isAlive()).forEach(t -> traitements.remove(t));
				TraitementUDPServeur traitementUDP = new TraitementUDPServeur(this, client.getInetSocketAddress(), client.getMessage(), traitement);
				traitements.add(traitementUDP);
				traitementUDP.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
