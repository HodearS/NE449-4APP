package fr.esisar.NE442.ExamP18.Ex2;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

import fr.esisar.NE442.sockets.ClientTCPSimple;
import fr.esisar.NE442.sockets.utils.ClientTCP;
import fr.esisar.NE442.sockets.utils.TraitementTCPClientInterface;

public class PontTCP 
{
	private static final String ip_serveur1 = "127.0.0.1", ip_serveur2 = "127.0.0.1";
	private static final int port_serveur1 = 8000, port_serveur2 = 8200;
	
	public static void main(String[] args){	
		try {
			PipedOutputStream out = new PipedOutputStream();
			PipedInputStream in = new PipedInputStream(out);
		
			ClientTCPSimple client1 = new ClientTCPSimple("Client1", ip_serveur1, port_serveur1, new TraitementTCPClientInterface() {
				public void run(ClientTCP client, Socket serveur) throws Exception {
					client.transferer(serveur.getInputStream(), out);
					out.close();
				}				
			});
			ClientTCPSimple client2 = new ClientTCPSimple("Client2", ip_serveur2, port_serveur2, new TraitementTCPClientInterface() {
				public void run(ClientTCP client, Socket serveur) throws Exception {
					client.transferer(in, serveur.getOutputStream());
					in.close();
				}				
			});
			client1.start();
			client2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
