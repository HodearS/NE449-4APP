package fr.esisar.NE442.sockets.utils;

import java.net.Socket;

public interface TraitementTCPServeurInterface {
	public void run(ServeurTCP serveur, Socket client) throws Exception;
}
