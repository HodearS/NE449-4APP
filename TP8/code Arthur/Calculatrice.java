package TD8;

public class Calculatrice extends Thread{
	
	private Somme somme;
	
	public Calculatrice(Somme somme){
		this.somme = somme;
	}
	
	@Override
	public void run(){
		int res = 0;
		for (int i = 0; i < 1000; i++){
			res= somme.somme(res, i);
		}
		
		System.out.println("1+2+3+4+...+999 = "+res);
	}
	
	
	static class Somme {
		int c;
		
		public int somme(int a, int b){ 
			c=a+b;
			System.out.println("c="+c);
			return c;
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException{
		Somme somme = new Somme();
		Calculatrice c1 = new Calculatrice(somme);
		Calculatrice c2 = new Calculatrice(somme);
		
		c1.start();
		c2.start();
		
	}
	
	
	
}
