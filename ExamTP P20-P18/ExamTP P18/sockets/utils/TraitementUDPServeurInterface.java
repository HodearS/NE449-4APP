package fr.esisar.NE442.sockets.utils;

import java.net.InetSocketAddress;

public interface TraitementUDPServeurInterface {
	public void run(ServeurUDP serveur, InetSocketAddress client, String message) throws Exception;
}
