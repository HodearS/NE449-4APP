package fr.esisar.NE442.sockets.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Service extends Thread{
	
	private String nom;
	private boolean logStatus = true;
	
	/******************/
	/** CONSTRUCTEUR **/
	/******************/
	
	public Service(String nom) {
		this.nom = nom;
	}
	
	/*************/
	/** GET/SET **/
	/*************/
	
	public boolean getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
	}
	
	/*********/
	/** LOG **/
	/*********/
	
	/** Permet d'afficher un message log dans la console
	 * @param message Message a afficher
	 */
	public void log(String message) {
		if(logStatus)
			System.out.println(nom + " : " + message);
	}
	
	/************/
	/** SAISIE **/
	/************/
	
	/** Permet de saisir du texte dans la console. La touche "Entré" valide la selection
	 * @param CR Ajoute un retour à la ligne si vrai
	 * @return Chaine de caractère saisie
	 * @throws Exception
	 */
	public static String saisie(boolean CR) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String message = br.readLine();
		if (CR)
			message += "\n";
		return message;
	}
	
	/***********/
	/** AUTRE **/
	/***********/
	
	/** Permet de séparer une chaine de caractère sur la dernière occurence d'un motif particulier
	 * @param text Chaine de caractere à séparer
	 * @param substring Motif particulier
	 * @param substring_meta Motif particulier avec meta caractere
	 * @return
	 */
	public static String[] splitLastSubstring(String text, String substring, String substring_meta) {
		String[] messages = text.split(substring_meta);
		
		String[] out = new String[2];
		out[0] = out[1] = "";
		String temp = text.replace(substring, "");
		int nb_substring = (text.length() - temp.length()) / substring.length();
		
		for(int i=0;i<nb_substring;i++)
			out[0] += messages[i] + substring;
		
		if(nb_substring != messages.length)
			out[1] = messages[messages.length-1];
		
		return out;
	}
}
