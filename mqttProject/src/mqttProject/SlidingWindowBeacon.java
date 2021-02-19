package mqttProject;

import java.util.ArrayList;

public class SlidingWindowBeacon {
	private ArrayList<Ibeacon> newList;
	private int maxSize;

	public SlidingWindowBeacon(int maxSize) {
		this.maxSize=maxSize;
		newList=new ArrayList<Ibeacon>(maxSize);
	}

	public ArrayList<Ibeacon> getNewList() {
		return newList;
	}

	//bm7a mn l [0] wbzed l3er5 ljded 3l [0]
	public void add(Ibeacon e) {
		newList.add(e);
		if(newList.size()>=maxSize) {
			newList.remove(0);
		}
		
	}
	public int getSize() {
		return maxSize;
	}

	public void clear() {
		if(newList!=null) {
			newList.clear();
		}
}

}
