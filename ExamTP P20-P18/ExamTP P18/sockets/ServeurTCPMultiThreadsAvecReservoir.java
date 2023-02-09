package fr.esisar.NE442.sockets;

import java.net.Socket;
import java.util.ArrayList;

import fr.esisar.NE442.sockets.utils.ServeurTCP;
import fr.esisar.NE442.sockets.utils.TraitementTCPServeur;
import fr.esisar.NE442.sockets.utils.TraitementTCPServeurInterface;

public class ServeurTCPMultiThreadsAvecReservoir extends ServeurTCP{
			
	private ArrayList<TraitementTCPServeur> traitements;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un serveur TCP multi threads. Les clients sont traités en paralèlle.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param maxThreads Nombre de clients maximum
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementTCPServeurInterface".
	 * @throws Exception
	 */
	public ServeurTCPMultiThreadsAvecReservoir(String nom, int port, int maxThreads, TraitementTCPServeurInterface traitement) throws Exception {
		super(nom, port);
		this.traitements = new ArrayList<TraitementTCPServeur>();
		
		for(int i=0;i<maxThreads;i++)
			traitements.add(new TraitementTCPServeur(this, null, traitement));
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {			
			TraitementTCPServeur traitementDisponible;
			ouvrir();
			while(true){
				while((traitementDisponible = traitements.stream().filter(t -> !t.isAlive()).findAny().orElse(null)) == null);
				Socket client = attendreConnexion();
				traitementDisponible.setClient(client);
				traitements.add(traitementDisponible);
				traitementDisponible.start();
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
