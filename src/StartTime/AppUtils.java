package StartTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;


public class AppUtils {


	public static void main(String []args) throws IOException, InterruptedException
	{
//		unistallPackage("com.jhss.youguu", "192.168.56.101:5555");
//		installPackage("mncg");
//		System.out.println(CheckPackage("com.jhss.youguu"));
//		 Runtime runtime = Runtime.getRuntime();
//		 Process proc = runtime.exec("adb -s "+"192.168.56.101:5555 "+" shell am start -W -n "+"com.jhss.youguu"+"/"+".SplashActivity");
//		 GetPackageStartTime.launchActivity("com.jhss.youguu", ".SplashActivity", "192.168.56.101:5555 ");
//		 isApkRunning("com.jhss.youguu");
		 isAppInstalled("emulator-5554", "com.jhss.youguu");

		}
	
	
	public static Boolean isAppInstalled(String device, String apkName) throws IOException, InterruptedException{
		
		String command = "adb -s " + device + " shell pm list packages";
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec(command);
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while((line = br.readLine())!=null){
			sb.append(line + "\r\n");
		}
		String result = sb.toString();
		return result != null && result.contains("package:" + apkName);

	}

	/**
	 * 卸载应用。
	 * @param packageName
	 * @param devicename
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void unistallPackage(String packageName, String devicename) throws IOException, InterruptedException  {
		  
	        String command="adb -s "+devicename + " " + "uninstall "+packageName;
		    
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec(command);
		    try {
                  
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());    
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));  
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) 
		        {
		            stringBuffer.append(line+" "); 
		        }
	        
		        String str1=stringBuffer.toString();
		        
		        
		        if(str1.contains("Success  "))
		        {
		       	 System.out.print("卸载成功！");
		        }
		        else
		        {
		       	 System.out.print("卸载失败！请检查");
		        }



		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }

	  }
	
	
	
	/**
	 * 安装应用。(指定apk路径在d盘log文件夹下。)
	 * @param apk
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void installPackage(String apk) throws IOException, InterruptedException  {
		  
	     
		    String installapk="adb install \"d:/log/"+apk+".apk\"";
	       
		    
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec(installapk);
	 
		
		   // Thread.sleep(10000);
		    try {
                  
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());    
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));  
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) 
		        {
		            stringBuffer.append(line+" "); 
		        }
	        
		        String str1=stringBuffer.toString();
		        
		        
		        if(str1.contains("Success  "))
		        {
		       	 System.out.print("安装成功！");
		        }
		        else
		        {
		       	 System.out.print("安装失败！请检查设备是否连接或者其他异常");
		        }

		
		 
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }

	}
	
	
	/**
	 * 检查应用是否已经启动。
	 * @param packageName
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Boolean isApkRunning(String packageName) throws IOException, InterruptedException  {
		  
		    String str1=null;
		    Boolean isLaunching =false;
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys meminfo "+packageName);
	 
		
		   // Thread.sleep(10000);
		    try {
                  
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());    
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                proc.getInputStream()));  
		        StringBuffer stringBuffer = new StringBuffer();
		        String line = null;
		        while ((line = in.readLine()) != null) 
		        {
		            stringBuffer.append(line+" "); 
		  	   
		        }
		        //System.out.print(stringBuffer.toString());
		        str1=stringBuffer.toString();
		        if(str1.contains("No process found"))
		        {
		       	 System.out.print("应用未启动");
		       	isLaunching =false;
		       	 
		        }
		        else
		        {
		       	 System.out.print("应用已启动");
		       	isLaunching =true;
		        }
		    	} 
		   
		    
		    catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
        
		    return isLaunching;
		    
	}
	

}
  

