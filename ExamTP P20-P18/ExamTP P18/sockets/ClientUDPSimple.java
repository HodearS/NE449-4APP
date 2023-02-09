package fr.esisar.NE442.sockets;

import fr.esisar.NE442.sockets.utils.ClientUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPClientInterface;

public class ClientUDPSimple extends ClientUDP{
			
	private TraitementUDPClientInterface traitement;

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'impl�menter un client UDP simple. Il r�alise un traitement pass� en parametre.
	 * @param nom Nom du client
	 * @param traitement Traitement � r�aliser impl�mentant l'interface "TraitementUDPClientInterface".
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
