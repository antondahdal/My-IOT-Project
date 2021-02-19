package mqttProject;

public class TimeHolder {
	private int hour;
	private int minute;
	private int sec;
	private static TimeHolder instance;
	private TimeHolder() {
		super();
	}
	
	
	public static TimeHolder getInstance() {
		if(instance==null) {
			instance=new TimeHolder();
		}
		return instance;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public int getMinute() {
		return minute;
	}


	public void setMinute(int minute) {
		this.minute = minute;
	}


	public int getSec() {
		return sec;
	}


	public void setSec(int sec) {
		this.sec = sec;
	}
}
