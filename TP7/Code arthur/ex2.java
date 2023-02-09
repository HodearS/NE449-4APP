package TD7;


	
public class ex2 extends Thread {
	
	long start,end;
	double nbrPi = 0;
	


	public ex2(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}


	public void run() {
		
		for(long i = start; i <= end; i++) {
			
			nbrPi += Math.pow(-1, i)/(2*i+1);
		}
	}




public static void main(String[] args) throws InterruptedException {
	
	long time = System.currentTimeMillis();
	
	int nbThread = 2;
	long N = 500000000;
	long end = N/nbThread;
	long start = 0;
	double PI = 0;
	
	
	// conteneur de tout les threads
	ex2[] threads = new ex2[nbThread];
	
	//creation des différents thread
	for (int i = 0; i< nbThread; i++) {
		
		ex2 newThread = new ex2(start,end);
		start = end+1;
		end = start + N/nbThread;
		
		threads[i] = newThread;
	}
	
	
	// activation des threads
	for( int i = 0; i<nbThread; i++) {
		
		threads[i].start();
	}
	
	// synchronisation
	for (int i = 0;i <nbThread; i++) {
		
		threads[i].join();
	}
	
	
	//recuperation des données
	for (int i = 0; i<nbThread;i++) {
		
		PI += threads[i].nbrPi;
	}
	
	System.out.println("Pi= "+PI);
	System.out.println("time en ms= " + (System.currentTimeMillis()-time));
	
	
	}
}
	
	

	
	
	


