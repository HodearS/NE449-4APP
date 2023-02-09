package fr.esisar.NE442.sockets.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public abstract class ServiceUDP extends Service{
	
	public DatagramSocket socket;

	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ServiceUDP(String nom) {
		super(nom);
	}
	
	/***************/
	/** RECEPTION **/
	/***************/
	
	/** Permet d'attendre la réception d'un message
	 * @return ConnexionUDP contenant l'adresse de la machine est le contenu du message
	 * @throws Exception
	 */
	public ConnexionUDP receptionner() throws Exception {
		log("Attente de reception");
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		InetSocketAddress machine = new InetSocketAddress(dpR.getAddress(),dpR.getPort());
		log("Message recu : " + message + machine);
		return new ConnexionUDP(machine, message);
	}
	
	/** Permet d'attendre la réception d'un message
	 * @return Message reçu
	 * @throws Exception
	 */
	public String attendreMessage() throws Exception {
		ConnexionUDP connexion = receptionner();	
		return connexion.getMessage();
	}
	
	/** Permet d'attendre la connexion d'une machine. Il s'agit en fait d'attendre un message quelconque d'une machine.
	 * @return Machine connecté
	 * @throws Exception
	 */
	public InetSocketAddress attendreIdentification() throws Exception {
		ConnexionUDP connexion = receptionner();	
		return connexion.getInetSocketAddress();
	}
	
	/***********/
	/** ENVOI **/
	/***********/

	/** Permet d'envoyer un message à une machine particulière
	 * @param message Message à envoyer
	 * @param ip Machine à laquelle envoyer le message
	 * @throws Exception
	 */
	public void envoyer(String message, String ip, int port) throws Exception {
		envoyer(message,new InetSocketAddress(ip, port));
	}
	
	/** Permet d'envoyer un message à une machine particulière
	 * @param message Message à envoyer
	 * @param ip Ip de la machine
	 * @param port Port de la machine
	 * @throws Exception
	 */
	public void envoyer(String message, InetSocketAddress machine) throws Exception {
		log("Envoi : " + message + machine);
		byte[] bufE = message.getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, machine);
		socket.send(dpE);
	}
}
