package test3;
import java.util.Random;

public class JobManager extends Thread{
	Integer id, rands, state;
	JobManager prev1;
	JobManager prev2;
	job job2;

	public JobManager(job job2, int id, JobManager prev1, JobManager prev2)
	{
		this.id = id;
		this.prev1 = prev1;
		this.prev2 = prev2;
		this.state = 2;
		this.job2 = job2;
		Random rand = new Random();
		rands = rand.nextInt(6000);
	}
	public JobManager(job job2, int id, JobManager prev1)
	{
		this.id = id;
		this.prev1 = prev1;
		this.state = 1;
		this.job2 = job2;
		Random rand = new Random();
		rands = rand.nextInt(6000);
	}
	public JobManager(job job2, int id)
	{
		this.id = id;
		this.state = 0;
		this.job2 = job2;
		Random rand = new Random();
		rands = rand.nextInt(6000);	 
	}
	@Override
	public void run() {
		try {
			switch(state)
			{
			case 0:
				break;
			case 1:
				prev1.join();
				break;
			case 2:
				prev1.join();
				prev2.join();
				break;
			}
			switch(id) {
			case 1:
				job2.t1();
				break;
			case 2:
				job2.t2();
				break;
			case 3:
				job2.t3();
				break;
			case 4:
				job2.t4();
				break;
			case 5:
				job2.t5();
				break;
			case 6:
				job2.t6();
				break;
			case 7:
				job2.t7();
				break;
			}
			System.out.println("Thread en cours: "+this.id.toString());
			sleep(rands);

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

