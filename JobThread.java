import java.time.LocalDateTime;
import java.time.Duration;

public class JobThread extends Thread{
	int sleepSec;
	Job job;
	boolean done = false;
	public JobThread(Job j, int cycle){
		job = j;
		sleepSec = cycle;
	}
	public void run(){
		while(true){
			try{
				//Thread.sleep(sleepSec * 1000);
				LocalDateTime now = LocalDateTime.now();
				if(now.getSecond() % 5 == 0){
					if(!done){
						job.execute();
						done = true;
					}
				}else{
					done = false;
				}
				
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	public interface Job{
		public void execute();
	}
}
