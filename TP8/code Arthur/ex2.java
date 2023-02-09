package TD8;

public class ex2 extends Thread {
	
	
	int numPhilo;
	Arbitre arbitre;


	public ex2(int numPhilo, Arbitre arbitre) {
		super();
		this.numPhilo = numPhilo;
		this.arbitre = arbitre;
		
	}

	public void discuter() throws InterruptedException {
		
			int nomPhilo = numPhilo +1;
			System.out.println("debut d'une discussion "+nomPhilo);
			sleep((int) (Math.random()*10));		
	}
	
	public void manger() throws InterruptedException {
			
			int nomPhilo = numPhilo +1;
			System.out.println("le philosophe " +nomPhilo+" mange");
			sleep((int) (Math.random()*10));
	
	}
	
	@Override
	public void run() {
		
		try {
			this.discuter();
			
			for (int j = 0; j<=2; j++) {
				
				if (this.arbitre.autorisation(this.numPhilo) == true) {
					this.manger();
					this.arbitre.liberation(this.numPhilo);
				}
				else {
					int nomPhilo = numPhilo +1;
					System.out.println("Ah chiant, bon bah j'attend " +nomPhilo);
					sleep(1000);
				}
			}
			
			this.discuter();
				
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}
		
	

	static class Arbitre {
		
		boolean listeBaguette[] = {true,true,true,true,true};
		
		public boolean autorisation(int numPhilo) {
			boolean auto = false;
			int baguette = numPhilo;
			int nxtBaguette = numPhilo == 4 ? 0 : baguette + 1;
			
			if (listeBaguette[baguette] == true && listeBaguette[nxtBaguette] == true) {
				auto = true;
				int nomPhilo = numPhilo +1;
				listeBaguette[baguette] = false;
				listeBaguette[nxtBaguette] = false;
				System.out.println("l'arbitre repond OK pour " + nomPhilo);
			}			
		 	return auto;
		}
		
		public void liberation (int numPhilo) {
			int baguette = numPhilo;
			int nxtBaguette = numPhilo == 4 ? 0 : baguette + 1;
			
			listeBaguette[baguette] = true;
			listeBaguette[nxtBaguette] = true;
			
		}
	}
	
// ******************** MAIN *************************************	
	public static void main(String[] args) throws InterruptedException {
		
		
		int nbPilo = 5;
		Arbitre arbitre = new Arbitre();
		
		// conteneur de tout les threads
		ex2[] threads = new ex2[nbPilo];
		
		for (int i = 0; i < nbPilo; i++) {
			
			ex2 newThread = new ex2(i,arbitre);	
			threads[i] = newThread;
		}
		
		
		// activation des threads
		for( int i = 0; i<nbPilo; i++) {
			
			threads[i].start();
		}
		
	}
	
	
	
	
}

