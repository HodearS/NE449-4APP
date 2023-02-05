package Exam2022;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Exo1clientUDP {
	public static void main(String[] args) throws Exception
	{
		Exo1clientUDP serveurUDP = new Exo1clientUDP();
		serveurUDP.execute();
	}
	
	private void execute() throws IOException
	{
	
		System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		DatagramSocket socket = new DatagramSocket();
		
		// Creation et envoi du message à l'adresse 127.0.0.1 et le port 11000
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 11000);
		for(int i =1;i<=10;i++) {
			
			System.out.println("==========================================");
			System.out.println("Partie "+i);

			byte[] bufE = new String("JOUER").getBytes();//stockage lessage dans buffer
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest); //envoie du message a l'adresse
			socket.send(dpE);
			System.out.println("Message envoyé.");
			
			// Le serveur se declare aupres de la couche transport
			// sur le port 11000
			//socket = new DatagramSocket(null);
			//socket.bind(new InetSocketAddress(11000));

			// Attente du premier message
			byte[] bufR = new byte[2048];
			DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
			socket.receive(dpR);//attente 1er du message du serveur
			String message1 = new String(bufR, dpR.getOffset(), dpR.getLength());
			
			System.out.println("IP = "+dpR.getAddress().getHostAddress());
			System.out.println("Port = "+dpR.getPort());
			
			System.out.println("Message recu = "+message1);
			String messplit[] = message1.split(";");
			Integer nb1 = Integer.parseInt(messplit[0]);
			
			
			//byte[] bufR_1 = new byte[2048];
			dpR = new DatagramPacket(bufR, bufR.length);
			socket.receive(dpR);//attente 1er du message du serveur
			String message2 = new String(bufR, dpR.getOffset(), dpR.getLength());
			
			System.out.println("Message recu = "+message2);
			String messplit2[] = message2.split(";");
			Integer nb2 = Integer.parseInt(messplit2[0]);
			
			String res= String.valueOf(nb1*nb2);
			
			bufE = new String(res+";").getBytes();//stockage lessage dans buffer
			dpE = new DatagramPacket(bufE, bufE.length, adrDest); //envoie du message a l'adresse
			socket.send(dpE);
			System.out.println("Message envoyé.");
			
			
			dpR = new DatagramPacket(bufR, bufR.length);
			socket.receive(dpR);//attente 1er du message du serveur
			String message3 = new String(bufR, dpR.getOffset(), dpR.getLength());
			
			System.out.println("Message recu = "+message3);
			
		}
		// Fermeture de la socket
		socket.close();
		System.out.println("");
		System.out.println("Arret du client .");
		
		
	}
}
