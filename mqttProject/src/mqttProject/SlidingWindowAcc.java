package mqttProject;
import java.util.ArrayList;

public class SlidingWindowAcc {
	private ArrayList<Double> newList;
	private int maxSize;

	public SlidingWindowAcc(int maxSize) {
		this.maxSize=maxSize;
		newList=new ArrayList<Double>(maxSize);
	}

	public ArrayList<Double> getNewList() {
		return newList;
	}

	//bm7a mn l [0] wbzed l3er5 ljded 3l [0]
	public void add(double e) {
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


	//e(ii)
	public double styaa() {
		int count=0;
		double sum=0;
		double sum2=0;
		double avg=0;
		for(double e:newList) {
			sum+=e;
			count++;
		}
		 avg=sum/count;
		
		for(double l:newList) {
			sum2+=Math.pow((l-avg), 2); 
		
		}	
		//return Standard deviation
		return Math.sqrt((sum2/count));
		
	}





	}



