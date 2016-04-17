package GetRoScoer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
;


public class GetTop {


	public static void main(String []args) throws IOException
	{
		for(int i = 0;i<2;i++)
		{
		//System.out.println(GetRoson("Cpu"));
		System.out.println(heap("com.jhss.youguu"));
	
		}
		}

	
	  public static double cpu(String PackageName) throws IOException {
		  double Cpu = 0;
		  try{
		   
			Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell top -n 1| grep "+PackageName);
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
		        //更换获取方式。
		        String result = stringBuffer.toString();
		        String[] temp = result.split(" com.jhss.youguu ");
		        String cpu = temp[0].substring(temp[0].indexOf("%")-4, temp[0].indexOf("%"));  //z   1%
		        cpu = cpu.trim();
		        Cpu=Double.parseDouble(cpu); //string转为double
		        
//			    String str1=stringBuffer.toString();
//			    String  str3=str1.substring(str1.indexOf(PackageName)-43,str1.indexOf(PackageName));
//				String cpu= str3.substring(0,4);
//				cpu=cpu.trim(); 
//				Cpu=Double.parseDouble(cpu); //string转为double
				
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
					
					System.out.print("请检查设备是否连接或应用是否为开启状态"+"\n");
					
					return -0.1;
				}
				
		            return Cpu;

	  }
	  
	  
	  public static double heap(String PackageName) throws IOException {
			 
			
		  double Heap = 0;
	
		   try{
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys meminfo "+PackageName);
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
		        
		        String result=stringBuffer.toString();
		        String temp=result.substring(result.indexOf("TOTAL"),result.indexOf("Objects"));
		        String  pss=temp.substring(5,16);
		        pss=pss.trim();
                Heap=Double.parseDouble(pss);
             
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    } catch (Exception StringIndexOutOfBoundsException){
		    	System.out.print("请检查应用是否启动"+"\n");
	       		return 0.0;
		    }
		    finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
	  }
	         
		   catch (Exception e)
       	{
			   e.printStackTrace();
	       		return -0.1;
       		
       	}
		    return Heap;
	  }
	  
	  
	  //根据adb shell dumpsys meminfo +package 返回结果中的内容得到pid
	  public static int getPid(String PackageName ){
		  int Pid = 0;
			
		   try{
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb shell dumpsys meminfo "+PackageName);
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
		        String str2=str1.substring(str1.indexOf("pid"),str1.indexOf("[com.jhss.youguu]"));
//		        System.out.println(str2);
		        String  str3=str2.substring(4,str2.length());
		        str3=str3.trim();
                Pid=Integer.parseInt(str3);

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
	        		System.out.print("请检查设备是否连接或应用是否为开启状态"+"\n");
	        		return -1;
	        		
	        	}
		    return Pid;
		  
	  }
	  
	  
 }
	