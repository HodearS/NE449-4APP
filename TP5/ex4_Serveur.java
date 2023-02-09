
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ex4_Serveur {

	public static void main (String[] args) throws IOException {
		
		System.out.println("Demmarrage serveur ...");
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(3000));

		Socket socketConnexion = socketEcoute.accept();
		System.out.println("client est connecté");
		
		// reception
		byte[] bufR = new byte[2048];
		InputStream is = socketConnexion.getInputStream();
		int lenBufR = is.read(bufR);
		
		if (lenBufR != -1) {
			String fileName = new String(bufR, 0 ,lenBufR);
			System.out.println("file recu = "+fileName);
			
			FileInputStream file = new FileInputStream(fileName);
			System.out.println("fichier receptionné");
			OutputStream os = socketConnexion.getOutputStream();
			System.out.println("Message envoyé");
		}
		
		
		
		
		
	}
		
}
