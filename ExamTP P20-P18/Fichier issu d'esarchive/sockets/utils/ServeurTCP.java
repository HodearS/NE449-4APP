package fr.esisar.NE442.sockets.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurTCP extends ServiceTCP{

	private int port;
	private ServerSocket socketServeur;
	private ArrayList<Socket> clients;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ServeurTCP(String nom, int port) throws Exception {
		super(nom);
		this.port = port;
		this.socketServeur = new ServerSocket();
		this.clients = new ArrayList<Socket>();
	}
	
	/*************/
	/** SERVEUR **/
	/*************/
	
	/** Ouvre le serveur
	 * @throws Exception
	 */
	public void ouvrir() throws Exception {
		log("Ouverture du serveur sur le port " + port);
		socketServeur.bind(new InetSocketAddress(port));
	}
	
	/** Ferme le serveur et déconnecte tous les clients
	 * @throws Exception
	 */
	public void fermer() throws Exception {
		log("Fermeture du serveur");
		clients.stream().forEach(c -> {
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		socketServeur.close();
	}
	
	/*************/
	/** CLIENT **/
	/*************/
	
	/** Permet d'attendre la connexion d'un client
	 * @return Retourne la connexion avec le client
	 * @throws Exception
	 */
	public Socket attendreConnexion() throws Exception {
		log("Attente de connexion");
		Socket socket = socketServeur.accept();
		clients.add(socket);
		log("Connexion de " + socket.getInetAddress() + ":" + socket.getPort());
		return socket;
	}
	
	/** Permet de deconnecter un client
	 * @param connexion Client deconnecté
	 * @throws Exception
	 */
	public void deconnexion(Socket socket) throws Exception {
		log("Deconnexion de " + socket.getInetAddress() + ":" + socket.getPort());
		clients.remove(socket);
		socket.close();
	}
}
