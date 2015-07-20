import java.time.LocalDateTime;
import java.time.Duration;

public class JobThread extends Thread{
	int sleepSec;
	Duration duration;
	Job job;
	public JobThread(Job j, int cycle){
		job = j;
		sleepSec = cycle;
		duration = Duration.ofSeconds(4);
	}
	public void run(){
		while(true){
			try{
				Thread.sleep(duration.toMillis());
				job.execute();
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	public interface Job{
		public void execute();
	}
}
