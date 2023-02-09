package fr.esisar.NE442.sockets;

import java.util.ArrayList;

import fr.esisar.NE442.sockets.utils.ConnexionUDP;
import fr.esisar.NE442.sockets.utils.ServeurUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPServeur;
import fr.esisar.NE442.sockets.utils.TraitementUDPServeurInterface;

public class ServeurUDPMultiThreadsAvecReservoir extends ServeurUDP{

	private ArrayList<TraitementUDPServeur> traitements;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un serveur UDP multi threads. Les clients sont traités en paralèlle.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param maxThreads Nombre de clients maximum
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementUDPServeurInterface".
	 * @throws Exception
	 */
	public ServeurUDPMultiThreadsAvecReservoir(String nom, int port, int maxThreads, TraitementUDPServeurInterface traitement) throws Exception {
		super(nom, port);
		this.traitements = new ArrayList<TraitementUDPServeur>();

		for(int i=0;i<maxThreads;i++)
			traitements.add(new TraitementUDPServeur(this, null, null, traitement));
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {
			TraitementUDPServeur traitementDisponible;
			ouvrir();
			while(true){
				while((traitementDisponible = traitements.stream().filter(t -> !t.isAlive()).findAny().orElse(null)) == null);
				ConnexionUDP client = receptionner();
				traitementDisponible.setClient(client.getInetSocketAddress());
				traitementDisponible.setMessage(client.getMessage());
				traitements.add(traitementDisponible);
				traitementDisponible.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
