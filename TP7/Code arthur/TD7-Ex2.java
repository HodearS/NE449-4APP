package td7;

public class PI2 extends Thread
{
	long start;
	long end;
	double res=0;

	public PI2(long start,long end)
	{
		this.start = start;
		this.end = end;
	}

	public void run()
	{
		for (long i = start; i <= end; i++)
		{
			res = res + Math.pow(-1, i) /(2*i+1);
		}	
	}

	public static void main(String[] args) throws InterruptedException
	{
		long start = System.currentTimeMillis();
		int nbThreads= 12;
		
		long N=  5000000000l;
		long endStep = N/nbThreads;
		
		long startStep =0;
		PI2[] threads =new PI2[nbThreads];
		
		for(int i= 0;i<nbThreads;i++)
		{
			PI2 thread = new PI2(startStep,endStep);
			startStep = endStep+1;
			endStep = startStep+ N/nbThreads;
		
			threads[i] = thread;
		}
		
		for(int i= 0;i<nbThreads;i++)
		{
		
			threads[i].start();
		}
		
		for(int i= 0;i<nbThreads;i++)
		{
		
			threads[i].join();
		}
		
		double pi = 0;
		for(int i= 0;i<nbThreads;i++)
		{
		
			pi += threads[i].res ;
		}
		
		
		System.out.println("PI="+pi*4);
		
		
		System.out
				.println("time in ms = " + (System.currentTimeMillis() - start));
	}
}
