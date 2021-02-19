package mqttProject;

public class SingeltonTestClass {

	public static void main(String[] args) {
		TimeHolder.getInstance().setHour(25);
		
		System.out.println(TimeHolder.getInstance().getHour());
	}

}
