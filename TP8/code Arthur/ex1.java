package TD8;



public class ex1 extends Thread {
	
	
	int numPhilo;
	


	public ex1(int numPhilo) {
		super();
		this.numPhilo = numPhilo;
	}

	public void discuter() throws InterruptedException {
		
			System.out.println("debut d'une discussion "+numPhilo);
			sleep((int) (Math.random()*10));		
	}
	
	public void manger() throws InterruptedException {
		
			System.out.println("il mange N°"+numPhilo);
			sleep((int) (Math.random()*10));
	
	}
	
	
	public void run() {
		
		try {
			this.discuter();
			this.manger();
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}
		
	public static void main(String[] args) throws InterruptedException {
		
		
		int nbPilo = 50;
		
		// conteneur de tout les threads
		ex1[] threads = new ex1[nbPilo];
		
		for (int i = 0; i < nbPilo; i++) {
			
			ex1 newThread = new ex1(i+1);	
			threads[i] = newThread;
		}
		
		
		// activation des threads
		for( int i = 0; i<nbPilo; i++) {
			
			threads[i].start();
		}
		
	}
	
	
	
	
}
