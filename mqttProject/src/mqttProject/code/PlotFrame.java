package mqttProject.code;



import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class PlotFrame extends JFrame {
	
	/** The time series data. */
    private TimeSeries series;    

    public PlotFrame(String title, String timeAxisLabel, String valueAxisLabel,  
    								double ymin, double ymax) {

        super(title);
        
        this.series = new TimeSeries(timeAxisLabel);
        
        TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        JFreeChart chart  = ChartFactory.createTimeSeriesChart( title,  timeAxisLabel, 
        										valueAxisLabel, dataset);

        XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(ymin, ymax); 

        ChartPanel chartPanel = new ChartPanel(chart);

        JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);
       pack();
       setVisible(true);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    
    
    public void addDataPoint(double datapoint) {
    	try {
    	 Millisecond now = new Millisecond();
    	 // System.out.println("Now = " + now.toString());
    	 this.series.addOrUpdate(now, datapoint);
    	}catch (Exception e) {			
    		e.printStackTrace();
		}
    }

    
    
   
    
}