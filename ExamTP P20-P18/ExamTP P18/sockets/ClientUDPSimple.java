package fr.esisar.NE442.sockets;

import fr.esisar.NE442.sockets.utils.ClientUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPClientInterface;

public class ClientUDPSimple extends ClientUDP{
			
	private TraitementUDPClientInterface traitement;

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'implémenter un client UDP simple. Il réalise un traitement passé en parametre.
	 * @param nom Nom du client
	 * @param traitement Traitement à réaliser implémentant l'interface "TraitementUDPClientInterface".
	 * @throws Exception
	 */
	public ClientUDPSimple(String nom, TraitementUDPClientInterface traitement) throws Exception {
		super(nom);
		this.traitement = traitement;
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {
			traitement.run(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
