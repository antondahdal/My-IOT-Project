package mqttProject;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;

import mqttProject.code.PlotFrame;
import mqttProject.code.utils.FileUtils;

public class MainGWTestClass {
	public static void main(String[] args) throws MqttException {
		Scanner in=new Scanner(System.in);
		// TODO Auto-generated method stub
		
		Comminicuator.getInstance().start();
		System.out.println("What part of The Project You Want To Run(1-4)");
		int part=in.nextInt();
		System.out.println("here");
		Controller controller=new Controller(part);
		System.out.println(controller.getNumOfChapter());
		Comminicuator.getInstance().addListener(controller);
		if(controller.getNumOfChapter()==3) {
		DataViewer dataViewer=new DataViewer();
		Comminicuator.getInstance().addListener(dataViewer);
		}
		if(controller.getNumOfChapter()==1) {
			DataViewerAcc dataViewer1=new DataViewerAcc();
			Comminicuator.getInstance().addListener(dataViewer1);
			File fileAcc=new File("file1.CSV");
			dataViewer1.plotFromFile(fileAcc);
		}		
		String topic="/gw/ac233fc02255/status";
		int qos=0;
		controller.setMac("GateWayMAc= "+"ac233fc02255");
	try {
		Comminicuator.getInstance().subscribe(topic, qos);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

}
