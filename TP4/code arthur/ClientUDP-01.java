package TD1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 */
public class ClientUDP
{

	public static void main(String[] args) throws Exception
	{
		ClientUDP clientUDP = new ClientUDP();
		clientUDP.execute();
				
	}
				
				
	/**
	 * Le client cree une socket, envoie un message UDP au serveur
	 */
	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		DatagramSocket socket = new DatagramSocket();
		
		// Creation et envoi du message à l'adresse 127.0.0.1 et le port 2000
		InetSocketAddress adrDest = new InetSocketAddress("192.168.130.24", 2000);
		byte[] bufE = new String("hello connard").getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		System.out.println("Message envoyé.");
		
		
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}

}