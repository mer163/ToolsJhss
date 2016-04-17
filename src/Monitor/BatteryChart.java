package Monitor;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.time.Millisecond;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  

import GetRoScoer.GetTop;
import GetRoScoer.Getbattery;
  
public class BatteryChart extends ChartPanel implements Runnable  
{  
    private static TimeSeries timeSeries;  
    private long value=0;  
    public volatile boolean stop = false;  
      
    public BatteryChart(String chartContent,String title,String yaxisName)  
    {  
        super(createChart(chartContent,title,yaxisName));  
    }  

    
    private static JFreeChart createChart(String chartContent,String title,String yaxisName){  
        //创建时序图对象  
        timeSeries = new TimeSeries(chartContent,Millisecond.class);  
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);  
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"时间(秒)",yaxisName,timeseriescollection,true,true,false);  
        XYPlot xyplot = jfreechart.getXYPlot();  
        //纵坐标设定  
        ValueAxis valueaxis = xyplot.getDomainAxis();  
        //自动设置数据轴数据范围  
        valueaxis.setAutoRange(true);  
        //数据轴固定数据范围 30s  
        valueaxis.setFixedAutoRange(60000D);  
  
        valueaxis = xyplot.getRangeAxis();  
        valueaxis.setRange(0.0D,200D);  
  
        return jfreechart;  
      }  
  
    public void run()  
    {  
        while(!stop)  
        {  
        try  
        {  
        	if(GetTop.cpu(Menu.text)==-0.1)
        	{
        		//JOptionPane.showMessageDialog(new JFrame(), "请检查设备连接");
        		break;
        	}
        	else
        	{
            timeSeries.add(new Millisecond(),Getbattery.battery() );  
            Thread.sleep(100);  
            
        } 
        	} 
        catch (InterruptedException e)  {   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        }         
    }  
      
    private double randomNum() throws IOException  
    {     
    	
        System.out.println(Getbattery.battery());        
        return Getbattery.battery();  
    }  
}  