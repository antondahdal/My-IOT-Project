package mqttProject;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
public class RaceCoachModel {
		protected int sum=0;
		public ArrayList<String> winners=new ArrayList<String>();
		public ArrayList<String> quitters=new ArrayList<String>();
		HashMap<String , Ibeacon > participates ;
 		protected boolean isRaceOn=true;
		 
		public RaceCoachModel(){
		 participates = new HashMap<String, Ibeacon>() ;
		
		}
		public void computerOrder(Ibeacon sd){
		if(participates.containsKey(sd.getMac())) {
			
		}
		
		else{
		Ibeacon p =new Ibeacon(sd.getMac(),sd.getTimeStamp(),sd.getRssi());
		participates.put(p.getMac(), p);
		
		}
		checkOrder();
		}

		private void checkOrder() {
		ArrayList<Ibeacon> list = new ArrayList< Ibeacon>(participates.values()) ;
		Collections.sort(list) ;
		whoIsWin(list);
		if(participates.size()==sum) {
			for(int i=0;i<winners.size();i++) {
				System.out.println(winners.get(i)+" is in Place "+(i+1));
			}
			isRaceOn=false;
		}
		
		}
		
		
		public void whoIsWin(ArrayList<Ibeacon> lst) {
			
			for(int i=0;i<lst.size();i++) {
				if(!winners.contains(lst.get(i).getMac())) {
				System.out.println(lst.get(i)+" place "+(i+1));
				}
			
				
				if((lst.get(i).getRssi()>=-22)){
					if(!winners.contains(lst.get(i).getMac())&&!quitters.contains(lst.get(i).getMac())){
					winners.add(lst.get(i).getMac());
					sum++;
					}
					
					lst.remove(i);

				}
			}
		}
		
		



}
