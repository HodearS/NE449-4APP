package fr.esisar.NE442.sockets.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public abstract class ServiceTCP extends Service{
	
	private String message = "";
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public ServiceTCP(String nom) {
		super(nom);
	}
	
	/***************/
	/** TRANSFERT **/
	/***************/
	
	/** Permet de transferer les donn�es d'un stream � un autre
	 * @param in Stream d'entr�e
	 * @param out Stream de sortie
	 * @param total	Taille connue des donn�es � envoyer (-1 si inconnue).
	 * @throws Exception
	 */
	private void transferer(InputStream in, OutputStream out, int total) throws Exception {
		byte[] buf = new byte[1024];
		int len, telecharge = 0;
		while((len = in.read(buf)) != -1) {
			if(total != -1)
				log((telecharge += len)*100/total + "%");
			out.write(buf, 0, len);
		}
	}
	
	/** Permet de transferer les donn�es d'un stream � un autre
	 * @param in Stream d'entr�e
	 * @param out Stream de sortie
	 * @throws Exception
	 */
	public void transferer(InputStream in, OutputStream out) throws Exception {
		transferer(in, out, -1);
	}
	
	/**************/
	/** MESSAGES **/
	/**************/
	
	/** Permet d'envoyer un message
	 * @param message Message � envoyer
	 * @param connexion Serveur aupres duquel on envoi le message
	 * @throws Exception
	 */
	public void envoyer(String message, Socket connexion) throws Exception {
		log("Envoi : " + message);
		InputStream in = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
		transferer(in,connexion.getOutputStream());
		log("Envoi termin�");
	}
	
	/** Permet d'attendre la reception d'un message
	 * @param connexion Serveur aupres duquel on attend un message
	 * @return Message re�u
	 * @throws Exception
	 */
	public String receptionner(Socket connexion) throws Exception {
		log("Attente d'un message");	
		byte[] buf = new byte[1024];
		int len = connexion.getInputStream().read(buf);
		String message = new String(buf, 0, len);
		log("Message recu : " + message);
		return  message;
	}
	
	/** Permet d'attendre la reception d'un message finissant par un motif particulier
	 * @param substring Motif particulier
	 * @param substring_meta Motif particulier avec meta caractere
	 * @param connexion Serveur aupres duquel on attend un message
	 * @return Message re�u
	 * @throws Exception
	 */
	public String receptionner(String substring, String substring_meta, Socket connexion) throws Exception {
		log("Attente d'un message");

		byte[] buf = new byte[2048];
		int len;
		do {
			len = connexion.getInputStream().read(buf);
			if(len != -1)
				message += new String(buf, 0 , len);
		}while(!message.contains(substring));
		
		String messages[] = splitLastSubstring(message, substring, substring_meta);
		message = messages[1];
		messages[0] = messages[0].substring(0, messages[0].length()-1);
		
		log("Message recu : " + messages[0]);
		return  messages[0];
	}
	
	/*************/
	/** FICHIER **/
	/*************/
	
	/** Permet d'envoyer un fichier
	 * @param fichier Nom du fichier � envoyer
	 * @param connexion Serveur aupres duquel on envoi le ficher
	 * @param protocole Envoyer la taille du fichier en premier puis ";"
	 * @throws Exception
	 */
	public void envoyerFichier(String fichier, Socket connexion, boolean protocole) throws Exception {
		FileInputStream fis = new FileInputStream(fichier);
		int total = fis.available();
		log("Envoi de fichier : " + fichier + " (" + total + " octets)");
		
		if(protocole) {
			envoyer(String.valueOf(fis.available()) + ";", connexion);
			transferer(fis, connexion.getOutputStream(), total);
		}
		else
			transferer(fis, connexion.getOutputStream());

		log("Fichier envoy� : " + fichier);
	}
	
	/** Permet d'attendre un fichier
	 * @param fichier Nom sous lequel le fichier sera sauvegard�
	 * @param connexion Serveur aupres duquel on attend le ficher
	 * @param protocole Attend la taille du fichier en premier puis ";"
	 * @throws Exception
	 */
	public void attendreFichier(String fichier, Socket connexion, boolean protocole) throws Exception {
		log("Attente d'un fichier");
		FileOutputStream fos = new FileOutputStream(fichier);
		
		if(protocole) {
			int total = Integer.parseInt(receptionner(";",";", connexion));
			transferer(connexion.getInputStream(), fos, total);
			log("Reception du fichier : " + fichier + " (" + total + " octets)");
		}
		else {
			transferer(connexion.getInputStream(), fos);
			log("Reception du fichier : " + fichier);
		}
		fos.close();
	}

	/** Permet de transferer un fichier entre deux serveurs.
	 * @param source Serveur source
	 * @param destination Serveur de destination
	 * @throws Exception
	 */
	public void transfererFichier(Socket source, Socket destination) throws Exception {
		log("Transfert d'un fichier de " + source.getInetAddress() + ":" + source.getPort() + " � " + destination.getInetAddress() + ":" + destination.getPort());
		transferer(source.getInputStream(), destination.getOutputStream());
		log("Transfert termin�");
	}
	
	/** Permet d'attendre une demande de fichier et d'envoyer le fichier correspondant
	 * @param connexion Serveur aupres duquel on attend le fichier
	 * @throws Exception
	 */
	public void attendreDemandeFichier(Socket connexion) throws Exception {
		envoyerFichier(receptionner(";",";",connexion), connexion, true);
	}
	
	/** Permet de demander un fichier � une machine distante
	 * @param fichier_distant Nom du fichier a demander
	 * @param fichier_distant Nom du fichier t�l�charg�
	 * @param connexion Serveur aupres duquel on demande le ficher
	 * @throws Exception
	 */
	public void demanderFichier(String fichier_distant, String fichier_local, Socket connexion) throws Exception {
		envoyer(fichier_distant + ";", connexion);
		attendreFichier(fichier_local, connexion, true);
	}
}
