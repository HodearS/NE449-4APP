package fr.esisar.NE442.sockets;

import fr.esisar.NE442.sockets.utils.ConnexionUDP;
import fr.esisar.NE442.sockets.utils.ServeurUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPServeurInterface;

public class ServeurUDPSimple extends ServeurUDP{
			
	private TraitementUDPServeurInterface traitement;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	/**	Permet d'impl�menter un serveur UDP multi threads. Un seul client est trait� puis le serveur se ferme.
	 * @param nom Nom du serveur
	 * @param port Port du serveur
	 * @param traitement Traitement � r�aliser impl�mentant l'interface "TraitementUDPServeurInterface".
	 * @throws Exception
	 */
	public ServeurUDPSimple(String nom, int port, TraitementUDPServeurInterface traitement) throws Exception {
		super(nom, port);
		this.traitement = traitement;
	}
	
	/****************/
	/** TRAITEMENT **/
	/****************/
	
	public void run(){
		try {
			ouvrir();
			ConnexionUDP client = this.receptionner();
			traitement.run(this, client.getInetSocketAddress(), client.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
