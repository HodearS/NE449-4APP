

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.InetAddress;




public class ex4_Client {

	public static void main (String[] args) throws IOException {
		
		if (args.length != 1) {
			System.out.println("usage: <filename>");
			System.exit(1);
		}
		
	
		// Envoi du nom du fichier
		Socket socket = new Socket();
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1",3000);
		socket.connect(adrDest);
		byte[] bufE = args[0].getBytes();
		OutputStream os = socket.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoyé");
		
		// Reception d'une réponse
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		String reponse = new String(bufR, 0 ,is.read(bufR));
		System.out.println(reponse);
		
		
		
		
		
	}
	
	
}
