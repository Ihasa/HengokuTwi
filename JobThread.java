import java.time.LocalDateTime;
import java.time.Duration;

public class JobThread extends Thread{
	int sleepSec;
	Job job;
	JobCycle cycle;
	public JobThread(Job j, int cycle){
		job = j;
		sleepSec = cycle;
		this.cycle = new JobCycle(Duration.ofSeconds(4));
	}
	public void run(){
		while(true){
			try{
				if(cycle.isActive()){
					Thread.sleep(cycle.getDuration().toMillis());
					job.execute();
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
