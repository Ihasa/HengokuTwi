import java.time.LocalDateTime;
import java.time.Duration;

public class JobCycle{
	private LocalDateTime start;
	private Duration duration;

	public JobCycle(LocalDateTime start, Duration duration){
		this.start = start;
		this.duration = duration;
	}
	public JobCycle(Duration d){
		this(LocalDateTime.now(),d);
	}

	public LocalDateTime getStartDateTime(){
		return start;
	}
	public Duration getDuration(){
		return duration;
	}
	public boolean isActive(){
		return start.compareTo(LocalDateTime.now()) <= 0;
	}
}
