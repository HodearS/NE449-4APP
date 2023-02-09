package TD4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class ClientTCP
{

	public static void main(String[] args) throws Exception
	{
		ClientTCP clientTCP = new ClientTCP();
		clientTCP.execute();				
	}
								
	/**
	 * Le client cree une socket, envoie un message au serveur
	 * et attend la reponse 
	 * 
	 */
	private void execute() throws IOException
	{
		String message = "",temp ="", data[], equa[],rep = "";
		
		
		System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		Socket socket = new Socket();
		
		// Connexion au serveur 
		InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
		socket.connect(adrDest);		
		
		// Attente de la reponse 
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		
		
		while (!message.startsWith("close")) {
			
			int lenBufR = is.read(bufR);
			if (lenBufR!=-1)
			{
				message = new String(bufR, 0 , lenBufR );
				
				if (!message.endsWith("?")) {
					temp= new String(bufR, 0 , lenBufR );
					message = message + temp;
					System.out.println("message recue = "+temp);
				} else {
					
					System.out.println("message recue = "+message);
					data = message.split("\\?");
					data[0] = data[0].substring(0,data[0].length()-1);
					equa = data[0].split("\\+");
					rep = String.valueOf(Integer.parseInt(equa[0]) + Integer.parseInt(equa[1])) + ";";
					System.out.println("reponse = "+rep);
					
					
					byte[] bufE = new String(rep).getBytes();
					OutputStream os = socket.getOutputStream();	
					os.write(bufE);
					System.out.println("Message envoye");
					
				}

			}
		}

		
		
		
	}
}

