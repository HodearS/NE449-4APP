package fr.esisar.NE442.ExamP20.Ex2;

import java.net.Socket;

import fr.esisar.NE442.sockets.ClientTCPSimple;
import fr.esisar.NE442.sockets.ServeurTCPMultiThreads;
import fr.esisar.NE442.sockets.utils.ClientTCP;
import fr.esisar.NE442.sockets.utils.ServeurTCP;
import fr.esisar.NE442.sockets.utils.TraitementTCPClientInterface;
import fr.esisar.NE442.sockets.utils.TraitementTCPServeurInterface;

public class ServeurMultiThreads 
{
	private static final String ip_serveur = "127.0.0.1";
	private static final int port_serveur = 2000;
	private static char lettre = 'A'-1;
	
	public static void main(String[] args) {	
		
		TraitementTCPClientInterface traitementClient = new TraitementTCPClientInterface() {
			public void run(ClientTCP client, Socket serveur) throws Exception {
				client.setLogStatus(false);
				while(true)
					System.out.println(client.receptionner(serveur));
			}
		};
		
		try {
			ServeurTCPMultiThreads serveur = new ServeurTCPMultiThreads("Serveur", port_serveur, new TraitementTCPServeurInterface() {
				public void run(ServeurTCP serveur, Socket client) throws Exception {
					serveur.setLogStatus(false);
					char lettreLocale;
					lettreLocale = ++lettre;
					if(lettreLocale <= 'C') {
						for(int i=1;i<=32;i++) {
							serveur.envoyer(String.valueOf(lettreLocale) + i + ";", client);	
							Thread.sleep(500);
						}
					}
					else
						serveur.envoyer("Trop tard", client);	
				}
			});
			
			ClientTCPSimple client1 = new ClientTCPSimple("Client1", ip_serveur, port_serveur, traitementClient);
			ClientTCPSimple client2 = new ClientTCPSimple("Client2", ip_serveur, port_serveur, traitementClient);
			ClientTCPSimple client3 = new ClientTCPSimple("Client3", ip_serveur, port_serveur, traitementClient);
			ClientTCPSimple client4 = new ClientTCPSimple("Client4", ip_serveur, port_serveur, traitementClient);
			serveur.setLogStatus(false);
			client1.setLogStatus(false);
			client2.setLogStatus(false);
			client3.setLogStatus(false);
			client4.setLogStatus(false);
			serveur.start();
			client1.start();
			client2.start();
			client3.start();
			client4.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
