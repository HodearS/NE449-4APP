package fr.esisar.NE442.ExamP20.Ex1;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import fr.esisar.NE442.sockets.ClientUDPSimple;
import fr.esisar.NE442.sockets.ServeurUDPSimple;
import fr.esisar.NE442.sockets.utils.ClientUDP;
import fr.esisar.NE442.sockets.utils.ServeurUDP;
import fr.esisar.NE442.sockets.utils.TraitementUDPClientInterface;
import fr.esisar.NE442.sockets.utils.TraitementUDPServeurInterface;

public class JeuDePisteUDP 
{	
	private static final String ip_serveur = "127.0.0.1";
	private static final int port_serveur = 9200;
	
	private static int[] ports = {9200, 35180,6848,5555,2888,7525,6754,14000,31555,20001};
	private static ArrayList<ServeurUDPSimple> serveurs = new ArrayList<ServeurUDPSimple>();
	
	public static void main(String[] args){		
		try {
			serveurs.add(new ServeurUDPSimple("Serveur0", ports[0], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[1], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur1", ports[1], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[2], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur2", ports[2], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[3], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur3", ports[3], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[4], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur4", ports[4], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[5], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur5", ports[5], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[6], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur6", ports[6], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[7], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur7", ports[7], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[8], client);
					}
				}
			}));
			serveurs.add(new ServeurUDPSimple("Serveur8", ports[8], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("Allez voir au port " + ports[9], client);
					}
				}
			}));			
			serveurs.add(new ServeurUDPSimple("Serveur9", ports[9], new TraitementUDPServeurInterface() {
				public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception {
					if(message.contains("JOUER")) {
						serveur.envoyer("La clef est 94555357800", client);
					}
				}
			}));
			
			ClientUDPSimple client = new ClientUDPSimple("Client", new TraitementUDPClientInterface() {
				public void run(ClientUDP client) throws Exception {
					String message;
					int port = port_serveur;
					do {
						client.envoyer("JOUER", ip_serveur, port);
						message = client.attendreMessage();
						if(message.contains("clef"))
							break;
						port = Integer.valueOf(message.substring(19));
					}while(true);
				System.out.println(message);
				}
			});
			serveurs.stream().forEach(s -> s.start());
			client.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
