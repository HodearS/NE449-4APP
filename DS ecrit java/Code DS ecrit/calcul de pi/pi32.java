package test;

public class pi32 extends Thread{
long start;
long end;
double res =0;
double tampon;
public pi32(long start, long end)
{
	this.start = start;
	this.end = end;
}

public void run()
{
	for(double i = start; i<=end;i++) { //boucle qui calcul la formule de leibnitz
		tampon = 1/((4*i+1)*(4*i+3));
		res = res+tampon;
	}
}
	
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
long start = 0;
double start_time =System.currentTimeMillis();
long end = 25000000;
double pi=0;

pi32[]arrayla = new pi32[4];

for(int i =0;i<4;i++) {
	arrayla[i]= new pi32(start,end);
	start=end+1;
	end=end+25000000; //création des 4 threads chaque thread s'occupe de 1/4 de k
}
for(int i=0;i<4;i++) {
	arrayla[i].start();
	arrayla[i].join();	//attend la fin d'un thread
}

for(int i=0;i<4;i++) {
	pi+=arrayla[i].res;
}

System.out.println("pi/8= "+pi);
	long now = System.currentTimeMillis();
	System.out.println("Le temps d'éxécution est de "+((now-start_time)/1000)+ "s");
	}
}
