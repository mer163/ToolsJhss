package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CopyOfGet_Flow1 {
	
	

	public static void main(String []args) throws IOException, InterruptedException
	{
		for(int i=0;i<1000;i++)
		{
	      Flow("com.jhss.youguu");
		}
	}

	
	public static double Flow(String PackageName) throws IOException, InterruptedException
	{
		    		
		double i1=GetFlow(PackageName);
		Thread.sleep(1000);
		double j =GetFlow(PackageName)-i1;
		//System.out.println(GetFlow()-i1);
		return j ;
				
	}



	  public static double GetFlow(String PackageName) throws IOException {
		    double str3=0;
		    String Pid=PID(PackageName);
		try{
		    Runtime runtime = Runtime.getRuntime();
		 /*   Process proc2 = runtime.exec("");*/
		    Process proc = runtime.exec("adb shell cat /proc/"+Pid+"/net/dev");
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
		    String str1=stringBuffer.toString();
		  //System.out.println(stringBuffer.toString().substring(68,stringBuffer.toString().indexOf("scale",0)));
			String str2=str1.substring(str1.indexOf("wlan0:"),str1.indexOf("wlan0:")+90);
           //接收字节：
			String str4=str2.substring(7,16);
			str4 = str4.trim();
		  //接收数据包
			//String str5=str2.substring(16,23);
			
			//发送字节：
			String str6=str2.substring(67,75);
			str6 = str6.trim();
		  //发送数据包
			//String str7=str2.substring(75,83);
			//int a=Integer.parseInt(str6);
			int b=Integer.parseInt(str4);
			int a=Integer.parseInt(str6);
			System.out.println("发送："+a/1024+"Kb");
			
			System.out.println("接收："+b/1024+"Kb");
	/*		
			System.out.println(str4);
			System.out.println(str5);
			System.out.println(str6);
			System.out.println(str7);*/
//
//			str3=str2.substring(6,10)+"%";
	        str3=b/1024;
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
		    catch (Exception StringIndexOutOfBoundsException)
        	{
		    	//System.out.print("请检查设备是否连接");
        		return -0.1;
        		
        	}
		   
		    //return str3 ;
			return str3;
	  }
	  
	  
	  //获取PID
	  public static String PID(String PackageName) throws IOException {
		  String str3=null;
		  
		    Runtime runtime = Runtime.getRuntime();
		 /*   Process proc2 = runtime.exec("");*/
		    Process proc = runtime.exec("adb shell ps |grep "+PackageName);
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
		    String str1=stringBuffer.toString();
		  //System.out.println(stringBuffer.toString().substring(68,stringBuffer.toString().indexOf("scale",0)));
			String str2=str1.substring(str1.indexOf(" "+PackageName)-46,str1.indexOf(" "+PackageName));
			//String PID =str2;
     		String PID =str2.substring(0,7);
		    PID = PID.trim();
			
		/*	String str4=str2.substring(29,36);
			String str5=str2.substring(44,50);*/
			//System.out.println(str1);
			//System.out.println(str5);
//
//			str3=str2.substring(6,10)+"%";
	        str3=PID;
		    //System.out.println(str3);
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
		    
		    //return str3 ;
			return str3;
	  }
	
	
	  }
  

