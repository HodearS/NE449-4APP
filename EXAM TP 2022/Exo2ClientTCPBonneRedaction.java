package Exam2022;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Exo2ClientTCPBonneRedaction extends Thread{
	
	int port;
	
	public Exo2ClientTCPBonneRedaction (int port){
		this.port=port;
	}

	public void run(){
		
		Exo2ClientTCP clientTCP = new Exo2ClientTCP(port);
		try {
			clientTCP.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void execute() throws IOException
	{
		
		//
		//System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		Socket socket = new Socket();
		
		// Connexion au serveur 
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", port);
		socket.connect(adrDest);		
		
		// Envoi de la requete
		byte[] bufE = new String("?").getBytes();
		OutputStream os = socket.getOutputStream(); //permet d'envoyer dans 
		os.write(bufE);
		//System.out.println("Message envoye");
		
		// Attente de la reponse 
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		String reponse ="";
		
		
		while (!reponse.contains("GAGNE") && (!reponse.contains("PERDU"))) {
			int lenBufR = is.read(bufR);
			if (lenBufR!=-1)
			{
				reponse = new String(bufR, 0 , lenBufR );
				//System.out.println("Reponse recue = "+reponse);
			}
		}
		
		if (reponse.contains("GAGNE")) {
			System.out.println("Début de la recherche ...");
			System.out.println("Le port d'écoute qui répond VOUS AVEZ GAGNE! est "+port);
			System.out.println("Fin du programme");

		}

		// Fermeture de la socket
		socket.close();
		//System.out.println("Arret du client .");
	}
	public static void main(String[] args) throws Exception {
		

		Exo2ClientTCP[] tab = new Exo2ClientTCP[2001];
		for (int i=0;i<=2000;i++) {//initialisation des thread
			Exo2ClientTCP newtcp = new Exo2ClientTCP(i+30000);
			tab [i] = newtcp;
		}
		
		for(int i= 0;i<2000;i++)//lancement des thread
		{
			tab[i].start();
		}

	}
}
