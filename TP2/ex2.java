
/** Lancement du programme:
 * - 
 * 
 * 
 * 
 * 
 * 
 */
import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;



public class ex2 {

	
	public static void main (String[] args) throws IOException, InterruptedException {
		
		int DEFAULT_PORT = 3000;
		int WINDOWS = Integer.parseInt(args[0]);
		int WINDOWSIZE = 300;
		
		int port = DEFAULT_PORT + WINDOWS;
		int nextPort = WINDOWS == 4 ? DEFAULT_PORT + 1 : port + 1;
		
		JFrame frame = new JFrame("Chenillard "+WINDOWS);
		frame.setSize(300,300);
		frame.setLocation(WINDOWS*WINDOWSIZE,WINDOWS);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
		Thread.sleep(1000);
		
		DatagramSocket socket = new DatagramSocket(null);
		
		while(true) {
			
			// Reception 
			
			socket.bind(new InetSocketAddress(port));
			
			byte[] bufR = new byte[2048];
			DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
			socket.receive(dpR);
			String message = new String(bufR, dpR.getOffset(), dpR.getLength());
			System.out.println("message " + message);
			
			socket.close();
			frame.getContentPane().setBackground(Color.RED);
			frame.setVisible(true);
			Thread.sleep(1000);
			
			
			// Emission au prochain
			socket = new DatagramSocket();
			InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1",nextPort);
			
			byte[] bufE = new String("hello").getBytes();
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length,adrDest);
			socket.send(dpE);
			System.out.println("message envoyé");
			
			socket.close();
			frame.getContentPane().setBackground(Color.GREEN);
			frame.setVisible(true);
			Thread.sleep(1000);
			
		}
		
	Runtime.getRuntime().addShutdownHook(new Thread() {
		@Override
		public void run() {
			socket.close();
			System.out.println("Socket fermé");
		}
	});
		
	}
}
