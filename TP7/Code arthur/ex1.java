package TD7;

public class ex1 {

	public static void main(String[] args) {
		
		double a = 0;
		
		for (long i = 0; i < 500000000; i++) {
			
			a += Math.pow(-1, i)/(2*i+1);
		}
		
		System.out.println("PI : "+a);
		
		
	}
	
	
}
