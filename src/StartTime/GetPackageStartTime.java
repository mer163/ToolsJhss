package StartTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jfree.chart.renderer.xy.DeviationRenderer;


public class GetPackageStartTime {
	
	public static void main(String args[]) throws IOException, InterruptedException{
		System.out.println(Gettime("com.jhss.youguu", ".SplashActivity","192.168.56.101:5555"));
		killpackage("com.jhss.youguu", "192.168.56.101:5555");
		
	}
	
	
	/**
	 * 获取启动耗时，并在3s后关闭应用。
	 * @param packageName
	 * @param activityname
	 * @param devicename
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String GetstartTime(String packageName,String activityname, String devicename)throws IOException, InterruptedException
	{
		
		String TalTime=GetPackageStartTime.Gettime(packageName,activityname,devicename);
		Thread.sleep(3000);
		killpackage(packageName,devicename);
//		System.out.print(TalTime);
        return TalTime;
	}


	/**
	 * 获取启动耗时信息
	 * @param packageName
	 * @param activityname
	 * @param devicename
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	 public static String Gettime(String packageName,String activityname, String devicename) throws IOException, InterruptedException {
		  String time=null;
		    Runtime runtime = Runtime.getRuntime();
		    String command = "adb -s "+devicename+" shell am start -W -n "+packageName+"/"+activityname;
		    Process proc = runtime.exec(command);
		    Thread.sleep(5000);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" "); 
		        }

		    String str1=stringBuffer.toString();
		    String[] temp,temp1;
		    temp = str1.split("TotalTime:");
		    if(temp[1].contains("WaitTime:")){
		    	temp1=temp[1].split("WaitTime:");
		    }else{
		    	temp1 = temp[1].split("Complete");
		    }
		   
//		    System.out.println(temp1[0]);
		    time = temp1[0].trim();

		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }

			return time;
	}
	 
	 /**
	  * 启动应用
	  * @param packageName 包名
	  * @param activityname activity名
	  * @param devicename  连接多设备时选择某一设备
	  * @throws IOException
	  * @throws InterruptedException
	  */
	  public static void launchActivity(String packageName,String activityname, String devicename) throws IOException, InterruptedException{
		  
		  Runtime runtime = Runtime.getRuntime();
		  Process proc = runtime.exec("adb -s "+devicename+" shell am start -W -n "+packageName+"/"+activityname);
		 
	  }
	  
	  

	  /**
	   * 强制关闭应用
	   * @param packageName 包名
	   * @param devicename 指定设备
	   * @throws IOException
	   * @throws InterruptedException
	   */
	  public static void killpackage(String packageName, String devicename) throws IOException, InterruptedException {
		 
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb -s "+ devicename +" shell  am force-stop "+packageName);
		    Thread.sleep(5000);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) {
		            stringBuffer.append(line+" ");
		            
		            
		        }
		     //   System.out.println(stringBuffer);
	        
	
			//System.out.println(str3);
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
		  
	  }
	  
	
  }
  

