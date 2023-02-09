package TD7;

public class ex3 extends Thread{

	int numThread;
	
	public ex3(int numThread) {
		super();
		this.numThread = numThread;
	}

	public void run() {
		
		System.out.println("debut du thread "+numThread);
		
		try {
			sleep(1000*numThread);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("fin du thread "+numThread);
		
	}
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		// conteneur de tout les threads
		ex3[] threads = new ex3[6];
		
		//creation des différents thread
		for (int i = 0; i < 6; i++) {
			
			ex3 newThread = new ex3(i+1);	
			threads[i] = newThread;
		}
		
		
		threads[0].start();
		threads[0].join();
		// rien n'est fait tant que le threads[1] n'est pas die
		// cela est du a la fonction join()
		
		
		threads[1].start();
		threads[2].start();
		threads[3].start();
		
		threads[1].join();
		threads[2].join();
		
		threads[4].start();
		
		threads[3].join();
		threads[4].join();
		
		threads[5].start();
			
		
		
	}
	
	
}

