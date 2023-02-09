package fr.esisar.NE442.ExamP18.Ex1;

import java.net.InetSocketAddress;

import fr.esisar.NE442.sockets.ClientUDPSimple;
import fr.esisar.NE442.sockets.utils.ClientUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPClientInterface;

public class PortScanningUDP 
{
	private static final String ip_serveur = "127.0.0.1";
	
	public static void main(String[] args){		
		try {
			ClientUDPSimple client = new ClientUDPSimple("Client", new TraitementUDPClientInterface() {
				public void run(ClientUDP client) throws Exception {
					client.log("Début du scanning des ports UDP de 30000 à 32000 sur la machine locale"); 
					client.setLogStatus(false);
					
					for(int port_serveur = 30000; port_serveur<=32000; port_serveur++) {
						client.envoyer("salut", ip_serveur, port_serveur);
					}
					InetSocketAddress serveur = client.attendreIdentification();

					client.setLogStatus(true);
					client.log("Le serveur UDP écoute sur le port X = " + serveur.getPort());
					client.log("Fin du programme");
				}
				
			});
			client.start();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
