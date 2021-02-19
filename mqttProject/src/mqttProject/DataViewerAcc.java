package mqttProject;
import java.io.File;
import java.util.Date;
import java.util.List;

import org.jfree.data.time.Millisecond;

import mqttProject.code.PlotFrame;
import mqttProject.code.utils.FileUtils;


public class DataViewerAcc implements DataObservable {
	private PlotFrame ipassAccX;
	private PlotFrame ipassAccY;
	private PlotFrame ipassAccZ;
	
	private PlotFrame ipassAccX1;
	private PlotFrame ipassAccY1;
	private PlotFrame ipassAccZ1;
	private PlotFrame X;
	private PlotFrame Y;
	private PlotFrame Z;
	private long currentMilleSeconds;
	public DataViewerAcc() {
		currentMilleSeconds=System.currentTimeMillis();
		//start(3)
		  ipassAccX=new PlotFrame("ipassAccX", "TS", "ipassX", -1, 1);
		  ipassAccY=new PlotFrame("ipassAccY", "TS", "ipassY", -1, 1);
		  ipassAccZ=new PlotFrame("ipassAccZ", "TS", "ipassZ", -1, 1);
		  //finish(3)
		  ipassAccX1=new PlotFrame("ipassAccXCSVFile", "TS", "ipassX", -1, 1);
		  ipassAccY1=new PlotFrame("ipassAccYCSVFile", "TS", "ipassY", -1, 1);
		  ipassAccZ1=new PlotFrame("ipassAccZCSVFile", "TS", "ipassZ", -1, 1); 
		  X=new PlotFrame("XCSVFile", "TS", "X", -10, 10);
		  Y=new PlotFrame("YCSVFile", "TS", "Y", -10, 10);
		  Z=new PlotFrame("ZCSVFile", "TS", "Z", -10, 10);
			
		  
	}

	@Override
	public void onDataReceived(List<EnvData> list) {
		for(EnvData d:list) {
			if(d instanceof walkClass) {
				walkClass m=(walkClass)d;
				//(3)
				double x=m.getIpassAccX();
				double y=m.getIpassAccY();
				double z=m.getIpassAccZ();
				
				  ipassAccX.addDataPoint(x); 
				  ipassAccY.addDataPoint(y);
				  ipassAccZ.addDataPoint(z);
				 
			}
		}
		
	}
	
	
	public void plotFromFile(File file) {
		byte[] data= FileUtils.readCopy(file);
		String content=new String(data);
		String lines[]=content.split("\n");
		for(String line:lines) {
			String fields[]=line.split(",");
			String mac=fields[0];
			String x=fields[2];
			String y=fields[3];
			String z=fields[4];
			String ipassx=fields[5];
			String ipassy=fields[6];
			String ipassz=fields[7];
			
			
			  double x1=Double.parseDouble(x); 
			  double y1=Double.parseDouble(y); 
			  double z1=Double.parseDouble(z);
			  double ipassx1=Double.parseDouble(ipassx); 
			  double ipassy1=Double.parseDouble(ipassy);
			  double ipassz1=Double.parseDouble(ipassz);
			  currentMilleSeconds+=1000;
			  Date d=new Date(currentMilleSeconds);
			  Millisecond now=new Millisecond(d);
			  ipassAccX1.addDataPoint(ipassx1); 
			  ipassAccY1.addDataPoint(ipassy1);
			  ipassAccZ1.addDataPoint(ipassz1);
			  X.addDataPoint(x1);
			  Y.addDataPoint(y1);
			  Z.addDataPoint(z1);
			 
			
				}
	}

}
