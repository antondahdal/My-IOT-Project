package mqttProject;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import mqttProject.code.utils.FileUtils;
		
public class Controller implements DataObservable
{
	SlidingWindowAcc slidinigWindowIpass=new SlidingWindowAcc(10);
	SlidingWindowAcc slidinigWindow=new SlidingWindowAcc(25);
	SlidingWindowBeacon racers=new SlidingWindowBeacon(600);
	 int sum=0;
	 RaceCoachModel coach=new RaceCoachModel();
	Scanner scanner=new Scanner(System.in);
	String mac;
	Location loc=new Location();
	int numOfChapter=0;
	
	
	
	
	public int getNumOfChapter() {
		return numOfChapter;
	}
	public void setNumOfChapter(int numOfChapter) {
		this.numOfChapter = numOfChapter;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}


	
	
	public Controller(int chapterNum) {
		setNumOfChapter(chapterNum);
		Comminicuator.getInstance().addListener(this);
		System.out.println("conHere");
		File file123=new File("Copy of all.csv");
		loc.plotFromFile(file123);
	
		makeFile();
		
	}
	@Override
	public void onDataReceived(List<EnvData> list) {
		for(EnvData sd:list) {

		// TODO Auto-generated method stub
		 switch (getNumOfChapter()) {
		 case 1:{
				
						if(sd instanceof walkClass) {
						walkClass walk=(walkClass)sd;
						
						//e(i) begin
						walk.gravityIpassX(walk);
						walk.gravityIpassY(walk);
						walk.gravityIpassZ(walk);
						//e(i) finish	
						slidinigWindowIpass.add(walk.getIpassAccY());
						if(slidinigWindowIpass.getNewList().size()>=9) {
					
					
						}
						System.out.println(walk.getIpassAccX());
						System.out.println("stya"+slidinigWindowIpass.styaa());
						//e(iii)
						  if(slidinigWindowIpass.styaa()>walk.epsilon) {
						  slidinigWindow.add(1.0); } 
						  else if(slidinigWindowIpass.styaa()<walk.epsilon) {
						  slidinigWindow.add(0.0); 
						  }
						 
						  
						 walk.walkingOrStanding(walkinOrStanding1());
						  
						
						 
						  //(6)
							 writeInFile("Mac= "+walk.getMac()+" ,"+" TimeStamp= "+walk.getTimeStamp()+" , "+walk.getX()+
									 " , "+walk.getY()+" , "+walk.getZ()+
									 "  , "+walk.getIpassAccX()+" , "+walk.getGravityY()+" , "+walk.getIpassAccZ()+"\n");
							 
							 
						} 					
						
						
								break;

						
					}

			
		 
		 
		 
	

		 case 2:{
				if(coach.isRaceOn==true) {
					if(sd instanceof Ibeacon) {
						if(coach.isRaceOn==true) {
						Ibeacon participant=(Ibeacon)sd;
						if(!coach.winners.contains(participant.getMac())) {
							
						coach.participates.put(participant.getMac(), participant);
						if(!coach.participates.containsKey(participant.getMac())){
						}
						}
						coach.computerOrder(participant);
						racers.add(participant);
						for(Ibeacon b:racers.getNewList()) {
							if(participant.getMac().equals(b.getMac())&&
									participant.getTimeStamp()!=b.getTimeStamp()&&
									participant.getRssi()==b.getRssi()&&b.getRssi()<(-25)) {
								sum++;
							}
							if(sum>200) {
								if(	!coach.quitters.contains(participant.getMac())&&
										!coach.winners.contains(participant.getMac())) {
								coach.quitters.add(participant.getMac());
								coach.participates.remove(b.getMac());
								sum=0;
								coach.sum++;
								}
								else {
									
								}
							}
						}
						}
						else
							break;
						
						
						
						
					}
					

					
				
				System.out.println("quitters"+coach.quitters);
				System.out.println("Winners"+coach.winners);
				System.out.println(coach.isRaceOn);
				}
				break;
			}

		 
		 
		case 3: {
				if(sd instanceof Ibeacon) {
					Ibeacon beac= (Ibeacon)sd;
					if(loc.PredictedMeters(beac.getRssi())>=8) {
						System.out.println(beac.getMac()+"Above 8 meters");
						
					}
					
				 writeInFile("time "+beac.getTimeStamp()+","+" mac-address "+
				 beac.getMac()+","+"gateway mac-address "+getGateWayMac()+","+"metersFromGateWay "+
						 loc.PredictedMeters(beac.getRssi())+","+", correlation\r\n" + 
						 		"coefficien "+loc.getP()+","+", regression a parameter "+
						 loc.geteSlope()+","+" regression b parameter "+loc.getB()+"\n");
				}			
				
			
			break;
		}
		case 4:{
				if(sd instanceof Ibeacon) {
					Ibeacon beac= (Ibeacon)sd;
					System.out.println("is this the right distance for Mac "+beac.getMac()+" "+loc.PredictedMeters(beac.getRssi()));
					System.out.println("Answer in yes Or no");
					String answer=scanner.next();
					if(answer.equals("no")){
						System.out.println("what is the right Distance ");
						int distance=scanner.nextInt();
						Location newLoc=new Location(beac.getMac(),beac.getRssi(),distance);
						loc.locList.add(newLoc);
						writeInFile(beac.getMac()+","+beac.getRssi()+","+distance+","+"\n");
						loc.getP();
					}
					}
			
		
				break;

		}
		 }
		}
	}
		 

		
	
	public double walkinOrStanding1() {
		double sum=0;
		
		if(slidinigWindow.getNewList().size()>=24) {//   <--- (2)
		for(int i=0;i<slidinigWindow.getNewList().size()-4;i++) {
			sum+=slidinigWindow.getNewList().get(i);
		}
		}
		return sum;
		}
		
	
	
		public String getGateWayMac() {
			return mac;
		}
		
		
		public void writeInFile(String msg) {
			try {
				switch (getNumOfChapter()) {
				case 1:{
					FileWriter fileWrite=new FileWriter("file11.CSV",true);
					fileWrite.append(msg);
					fileWrite.flush();
					fileWrite.close();
					break;
				}
				case 2: {
					FileWriter fileWrite=new FileWriter("file2.CSV",true);
					
					fileWrite.append(msg);
					fileWrite.flush();
					fileWrite.close();
					break;

				}
				case 3: {
					FileWriter fileWrite=new FileWriter("file3.CSV",true);
					
					fileWrite.append(msg);
					fileWrite.flush();
					fileWrite.close();
					break;

				}
				case 4:{
					FileWriter fileWrite=new FileWriter("Copy of all.csv",true);
					fileWrite.append(msg);
					fileWrite.flush();
					fileWrite.close();
					break;

				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + getNumOfChapter());
				}
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public void makeFile() {
			try {
				switch (getNumOfChapter()) {
				case 1: {
					
					File file=new File("file11.CSV");
					if(file.createNewFile()) {
						System.out.print(file.getName());
						
				}
					break;
				}
				case 2:{
					File file=new File("file2.CSV");
					if(file.createNewFile()) {
						System.out.print(file.getName());
					
				}
					break;

				}
				case 3:{
					File file=new File("file3.CSV");
					if(file.createNewFile()) {
						System.out.print(file.getName());
					
				}
					break;

				}
				case 4:{break;}
				default:
					throw new IllegalArgumentException("Unexpected value: " + getNumOfChapter());
				}
		
		}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			}
		
			
		}		
	


