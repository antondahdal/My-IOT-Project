package mqttProject;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.jfree.data.time.Millisecond;

import mqttProject.code.PlotFrame;
import mqttProject.code.utils.FileUtils;

public class DataViewer implements DataObservable {
	SlidingWindow beacons=new SlidingWindow(6);
	Location loc1=new Location();
	PlotFrame beacon1;
	PlotFrame beacon2;
	PlotFrame beacon3;
	PlotFrame beacon4;
	PlotFrame beacon5;
	PlotFrame beacon6;
	public DataViewer() {
		beacon1=new PlotFrame("beacon1","Time","Meters",1,13);
		 beacon2=new PlotFrame("beacon2","Time","Meters",1,13);
		 beacon3=new PlotFrame("beacon3","Time","Meters",1,13);
		 beacon4=new PlotFrame("beacon4","Time","Meters",1,13);		
		 beacon5=new PlotFrame("beacon5","Time","Meters",1,13);
		 beacon6=new PlotFrame("beacon6","Time","Meters",1,13);
		
			Comminicuator.getInstance().addListener(this);
			File file=new File("Copy of all.csv");
			byte[] aa=FileUtils.readCopy(file);
			loc1.plotFromFile(file);
	}
	
	public double getPrediction(int rssi) {
		
		return loc1.PredictedMeters(rssi);
	}

	@Override
	public void onDataReceived(List<EnvData> list) {
		for(EnvData d:list) {
			if(d instanceof Ibeacon) {
				Ibeacon m=(Ibeacon)d;
				if(!beacons.getNewList().contains(m.getMac())) {
				beacons.add(m.getMac());
				}
				if(beacons.getNewList().size()==2)

				if(beacons.getNewList().get(0).equals(m.getMac())) {
				int rssi1=m.getRssi();
				double pred=getPrediction(rssi1);
				beacon1.addDataPoint(pred);
				}
				if(beacons.getNewList().size()==2&&beacons.getNewList().get(1).equals(m.getMac())) {
					int rssi2=m.getRssi();
					double pred2=getPrediction(rssi2);
					beacon2.addDataPoint(pred2);
					
				}
				if(beacons.getNewList().size()==3&&beacons.getNewList().get(2).equals(m.getMac())) {
					int rssi3=m.getRssi();
					double pred3=getPrediction(rssi3);
					beacon3.addDataPoint(pred3);
					
				}
				if(beacons.getNewList().size()==4&&beacons.getNewList().get(3).equals(m.getMac())) {
					int rssi2=m.getRssi();
					double pred2=getPrediction(rssi2);
					beacon2.addDataPoint(pred2);
					
				}
				if(beacons.getNewList().size()==5&&beacons.getNewList().get(4).equals(m.getMac())) {
					int rssi2=m.getRssi();
					double pred2=getPrediction(rssi2);
					beacon2.addDataPoint(pred2);
					
				}
				if(beacons.getNewList().size()==6&&beacons.getNewList().get(5).equals(m.getMac())) {
					int rssi2=m.getRssi();
					double pred2=getPrediction(rssi2);
					beacon2.addDataPoint(pred2);
					
				}
			}
		}
		
	}
	
	}

