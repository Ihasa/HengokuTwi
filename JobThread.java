import java.time.LocalDateTime;
import java.time.Duration;

public class JobThread extends Thread{
	Job job;
	JobCycle cycle;
	public JobThread(Job j, Duration duration){
		job = j;
		this.cycle = new JobCycle(duration);
	}
	
	public void run(){
		while(!cycle.isActive()){}
		while(true){
			try{
				Thread.sleep(cycle.getDuration().toMillis());
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
