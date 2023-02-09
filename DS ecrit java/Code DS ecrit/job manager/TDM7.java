package test3;


public class TDM7
{
	public static void main(String[] args) throws InterruptedException
	{

		//public JobManager(job job2, int id, JobManager prev1, JobManager prev2, JobManager state)
		//public JobManager(job job2, int id, JobManager prev1)
		//public JobManager(job job2, int id, job job2)

		job job1 = new job();

		JobManager t1 = new JobManager(job1,1);
		JobManager t2 = new JobManager(job1,2,t1);
		JobManager t3 = new JobManager(job1,3,t1);
		JobManager t4 = new JobManager(job1,4,t1);
		JobManager t5 = new JobManager(job1,5,t2,t3);
		JobManager t6 = new JobManager(job1,6,t4);
		JobManager t7 = new JobManager(job1,7,t5, t6);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t7.join();


		System.out.println("fini");
	}
}

