package fr.esisar.NE442.sockets.utils;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTCP extends ServiceTCP{
		
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ClientTCP(String nom) {
		super(nom);
	}
	
	/*************/
	/** SERVEUR **/
	/*************/
	
	/** Permet de se connecter a un serveur
	 * @param ip_serveur Ip du serveur auquel se connecter
	 * @param port_serveur Port du serveur auquel se connecter
	 * @return Retourne la connexion avec le serveur
	 * @throws Exception
	 */
	public Socket connexion(String ip_serveur, int port_serveur) throws Exception {
		return connexion(new InetSocketAddress(ip_serveur, port_serveur));
	}
	
	/** Permet de se connecter a un serveur
	 * @param serveur InetSocketAddress du serveur auquel se connecter
	 * @return Retourne la connexion avec le serveur
	 * @throws Exception
	 */
	public Socket connexion(InetSocketAddress serveur) throws Exception {
		log("Connexion au serveur " + serveur);
		Socket socket = new Socket();
		socket.connect(serveur);
		return socket;
	}
	
	/** Permet de se deconnecter du serveur
	 * @param connexion Serveur duquel se deconnecter
	 * @throws Exception
	 */
	public void deconnexion(Socket socket) throws Exception {
		log("Deconnexion du serveur " + socket.getInetAddress() + ":" + socket.getPort());
		socket.close();	
	}
}
