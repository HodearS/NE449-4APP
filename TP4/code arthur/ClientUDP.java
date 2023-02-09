package TD4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;



public class ClientUDP {

	public static void main(String[] args) throws Exception
	{
		ClientUDP clientUDP = new ClientUDP();
		clientUDP.execute();
				
	}
	
	
	private void execute() throws IOException
	{
		String data[],equa[],idR,rep;
		int num1,num2;
		
		System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		DatagramSocket socket = new DatagramSocket();
		
		// Creation et envoi du message à l'adresse 127.0.1 et le port 7001
		InetSocketAddress adrDest = new InetSocketAddress("127.0.1", 7001);
		byte[] bufE = new String("JOUER").getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		System.out.println("Message envoyé.");
		
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		
		data = message.split(":");
		idR  = "R" + data[0].substring(1,6);
		
		equa = data[1].split("\\+");
		num1 = Integer.parseInt(equa[0]);
		
		equa[1] = equa[1].substring(0,equa[1].length()-3);
	
		num2 = Integer.parseInt(equa[1]);
		
		rep = "R" + data[0].substring(1,6) + ":" + String.valueOf(num1+num2);
		
		System.out.println(rep); 
		
		bufE = new String(rep).getBytes();
		dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		System.out.println("Message envoyé.");
		
		
		
		
		
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}

	
}
