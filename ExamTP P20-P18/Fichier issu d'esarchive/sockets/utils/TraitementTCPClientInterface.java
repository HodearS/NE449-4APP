package fr.esisar.NE442.sockets.utils;

import java.net.Socket;

public interface TraitementTCPClientInterface {
	public void run(ClientTCP client, Socket serveur) throws Exception;
}
