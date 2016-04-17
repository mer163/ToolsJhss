package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Getbattery {
	
	
	public static void main(String []args) throws IOException
	{
		
		System.out.print(battery());
	}



	  public static double battery() throws IOException {
		    double batt=0;
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys battery");
		    String str3;
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
			String str2=str1.substring(str1.indexOf("level"),str1.indexOf("level")+10);
			str3=str2.substring(6,10);
			str3.trim();
			batt=Double.parseDouble(str3);
			
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }

			return batt;
			
	  }
	  
	  
	
	  }
  

