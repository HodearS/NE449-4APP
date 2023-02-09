package ExamP20;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class jeudepiste {
	public static void main(String[] args) throws Exception
	{
		jeudepiste serveurUDP = new jeudepiste();
		serveurUDP.execute();
	}

	private void execute() throws IOException
	{
	
		System.out.println("Demarrage du client ...");
		
		DatagramSocket socket = new DatagramSocket();

		int a =9200;
		int n =2;
		
		for(int i =1;i<n;i++) {
			InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", a);
			System.out.println("==========================================");
			System.out.println("Début de la partie "+i);

			byte[] bufE = new String("JOUER").getBytes();//stockage lessage dans buffer
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest); //envoie du message a l'adresse
			socket.send(dpE);
			System.out.println("Envoi d'un paquet UDP avec JOUER");
			
			byte[] bufR = new byte[2048];
			DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
			socket.receive(dpR);//attente 1er du message du serveur
			String message1 = new String(bufR, dpR.getOffset(), dpR.getLength());
			
			
			System.out.println("IP = "+dpR.getAddress().getHostAddress());
			System.out.println("Port = "+dpR.getPort());
			
			System.out.println("Message recu = "+message1);

			a = Integer.parseInt(message1, 19, dpR.getLength(), 10);
			System.out.println(a);
			
			if(message1.contains("c"))
			{
				
			}
			else
			{
				n=n+1;
			}
			
		}
		socket.close();
		System.out.println("");
		System.out.println("Arret du client .");
		
		
	}
}