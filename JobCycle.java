import java.time.LocalDateTime;

public abstract class JobCycle{
	public abstract boolean isTime(LocalDateTime now);
}

class EveryDay extends JobCycle{
	int day, hour, min, second;
	public EveryDay(int day, int hour, int min, int second){
		this.day = day;
		this.hour = hour;
		this.min = min;
		this.second = second;
	}
	public boolean isTime(LocalDateTime now){
		return now.getDayOfMonth() % day == 0 && now.getHour() == hour && now.getMinute() == min && now.getSecond() == second;
	}
}

class EveryHour extends JobCycle{
	int hour, min, second;
	public EveryHour(int hour, int min, int second){
		this.hour = hour;
		this.min = min;
		this.second = second;
	}
	public boolean isTime(LocalDateTime now){
		return (now.getHour() % hour == 0 && now.getMinute() == min && now.getSecond() == second);
	}
}


class EveryMin extends JobCycle{
	int second;
	int min;
	public EveryMin(int min, int second){
		this.min = min;
		this.second = second;
	}
	public boolean isTime(LocalDateTime now){
		return (now.getMinute() % min == 0 && now.getSecond() == second);
	}
}

class EverySecond extends JobCycle{
	int second;
	public EverySecond(){
		this(1);
	}
	public EverySecond(int s){
		second = s;
	}
	public boolean isTime(LocalDateTime now){
		return now.getSecond() % second == 0;
	}
}
