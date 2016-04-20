package Monkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


import Monitor.Menu;
import Outlog.log;


public class Monkey {

	
	public static void main(String []args) throws IOException, InterruptedException
	{
		
		//Monkey(" ", "com.taobao.taobao", "500", "", "", "", "", "", "", "500","D:/log8.txt");
//		System.out.println(getMonkeyPid("192.168.56.101:5555"));
//		killMonkey("192.168.56.101:5555", getMonkeyPid("192.168.56.101:5555"));
		System.out.println(getDevices());
	}

	public static void killMonkey(String DevicesName,String pid) throws IOException{
		
		Runtime runtime1 = Runtime.getRuntime();
		Process proc = runtime1.exec("adb -s "+DevicesName+" shell kill "+pid);
//	    Thread.sleep(5000);
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
		if(str1.length()==0){
			
			System.out.println("结束monkey成功");
		}
		else{
			System.out.println("Monkey 没有在运行中，结束monkey失败");
			
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
	
	
	
	public static String getMonkeyPid(String DevicesName) throws IOException{
		
		Runtime runtime1 = Runtime.getRuntime();
		String command = "adb -s "+DevicesName+" shell ps |grep monkey";
		Process proc = runtime1.exec(command);
//	    Thread.sleep(5000);
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
	    System.out.println("str1:"+str1);
		
		if(str1.length()==0){
			
			System.out.println("monkey is not running...");
		}
		else{
			String str2 = str1.substring(str1.indexOf("root")+4,str1.indexOf("root")+16).trim();

			return str2;
		}

	    } catch (InterruptedException e) {
	        System.err.println(e);
	    }finally{
	        try {
	            proc.destroy();
	        } catch (Exception e2) {
	        }
	    }
	    return null;
	}
	
	public static Boolean isMonkeyRunning( String DevicesName) throws IOException, InterruptedException{
		Boolean ismonkeyrunning=false;
		Runtime runtime1 = Runtime.getRuntime();
		String command = "adb -s "+DevicesName+" shell ps |grep monkey";
		Process proc = runtime1.exec(command);
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
		
		if(str1.length()==0){
			ismonkeyrunning=false;
//			System.out.println("monkey is not running...");
		}
		else{
			ismonkeyrunning =true;
//			System.out.println("monkey is running...");
		}
		
	    } catch (InterruptedException e) {
	        System.err.println(e);
	    }finally{
	        try {
	            proc.destroy();
	        } catch (Exception e2) {
	        }
	    }
	    return ismonkeyrunning;
	}
	
	/**
	 * 
	 * @param command
	 * @return 返回一个String数组， 第一位代表完整内容，第二位为crash内容。
	 */
	public static void getCrash(String command,String pkgname,JTextArea text)
    {
        BufferedReader br = null;
        StringBuffer totalLog = new StringBuffer();
        StringBuffer crashLog = new StringBuffer();
        
        String path = Monkey_Menu.path; //获取页面上的日志路径。
        Boolean flag = false;
        Boolean success = false;
        
        String[] ret = new String[2];
        String str2 = null;
        String time = Outlog.log.time();
        //判断目录是否存在，不存在则手动创建
        if(!(new File(path).isDirectory())){
        	new File(path ).mkdirs();
        }
        if(!(new File(path +"Monkey_log/").isDirectory())){
        	new File(path+"Monkey_log/").mkdir();
        }
        if(!(new File(path+"Crach_log/").isDirectory())){
        	new File(path+"Crash_log/").mkdir();
        }
        
        File file = new File(path + "Monkey_log" + File.separator + log.time() + ".txt");
        File crashFile = new File(path +"Crash_log" + File.separator +log.time() + ".txt");
        
        try {

        	Process p = Runtime.getRuntime().exec(command);    
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            Writer writer = new OutputStreamWriter(
            		new FileOutputStream(file,true),"UTF-8");
            Writer writer1 = new OutputStreamWriter(new FileOutputStream(crashFile,true),"UTF-8");
            
            String line = null;
            while ((line = br.readLine()) != null) {
            	if("".equals(line.trim())) continue;
            	
                writer.write(line+"\r\n");
                totalLog.append(line+"\n");
                if(line.startsWith("CRASH: " + pkgname)){
                	text.append("应用：" + pkgname + "crash发生，开始捕捉崩溃日志。\n" );
                	text.append("----------------------------\n");
                	text.append(line + "\n");
                	writer1.write("应用：" + pkgname + " crash发生，开始捕捉崩溃日志。 \n" + "-----------------------------");
                	writer1.write(line+"\r\n");
                	flag = true;
	
                }
                else if (line.equalsIgnoreCase("// Monkey finished")) {
                    success = Boolean.valueOf(true);
                  }

                else if (line.startsWith("## Network stats:")) {
                    int fisrt = line.indexOf("=");
                    int end = line.indexOf("ms");
                    double d = Double.valueOf(line.substring(fisrt + 1, end)).doubleValue() / 1000.0D / 60.0D;
                  }
                
                if ((flag.booleanValue()) && 
                    (line.startsWith("// ")) && (!(line.equalsIgnoreCase("// Monkey finished")))) {
                    text.append(line + "\n");
                    crashLog.append(line + "\n");
                    writer1.write(line + "\r\n");
                	}
            }
            goBackIfWebViewOccored();
            if (success)
                if (flag.booleanValue()) {
                  text.append("本次monkey结束，检测到应用崩溃，请查看日志。\n");
                  writer.write("本次monkey结束，检测到应用崩溃，请查看日志。\n");
                } else {
                  text.append("本次monkey结束，未发生崩溃，请查看日志。\n");
                  writer.write("本次monkey结束，未发生崩溃，请查看日志。\n");
                  writer1.write("本次monkey结束，未发生崩溃，请查看日志。\n");
                }
              else {
            	  text.append("monkey发生未知错误，执行失败。\n");
              }

              writer.close();
              writer1.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                try {
                    br.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
       
    }
	
	public static void goBackIfWebViewOccored() throws IOException, InterruptedException{
		while (true)
	    {
	      Check check = new Check();
	      Thread ck = new Thread(check);
	      ck.start();
	      Thread.sleep(3000L);
	    }
	}
	
	
	
	public static String Monkey( String DevicesName,String PackageName ,String zscount,String throttle,String anyevent,String touch,String motion,String trackball,String syskeys,String appswitch,String count,String path) throws InterruptedException{
		Runtime runtime1 = Runtime.getRuntime();
		String errorMSG = "";
		 BufferedReader br = null;
         StringBuffer stringBuffer = new StringBuffer();
         String str2 = null;
	    String cmd="adb -s "+DevicesName+" shell monkey -p "+PackageName+  " --ignore-crashes --ignore-timeouts" +" --monitor-native-crashes" +" -s "+zscount+" "+throttle+" "+anyevent+" "
	    		+ touch+" "+motion +" "+trackball +" "+syskeys+" " +appswitch+ " -v-v-v "+count+" > "+path+log.time()+".txt";
	    
//	    System.out.println(cmd);
		try {
			String[] args = new String[]{"cmd","/c",cmd};
			//String[] args = new String[]{"sh","-?/c",command};
			
			Process pro1 = runtime1.exec(args);
			//Process pro = runtime.exec("c://///////.exe");
//			
			br = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
	             if("".equals(line.trim())) continue;

//	                 stringBuffer.append(line+"\n");
	                 System.out.println(line);
	                 if (line.contains("// ")){
//	                	 while((line).contains("// ")){
	                		 stringBuffer.append(line+"\n");
	                		 
//	                	 }
	                	 
	                	 
	                 }
	                 String str1 = stringBuffer.toString();
	                 if(str1.contains("// CRASH: com.jhss.youguu")){
	                	 str2 = str1.substring(str1.indexOf("// CRASH: com.jhss.youguu"));
	                     
	                 }
	             }
			
			
			
			if (pro1.waitFor() != 0) {
	            System.err.println("exit value = " + pro1.exitValue());
	        }
	       
			//检查命令是否失败
			try {
				if(pro1.waitFor()!=0){
					System.err.println("exit value:" + pro1.exitValue());
					JOptionPane.showMessageDialog(new JFrame(), "哥们抱歉，好像出问题了！关掉重试吧！");
				}
			} catch (InterruptedException e) {
				System.err.println();
				e.printStackTrace();
			
				
			}
			
		} catch (IOException e) {
			System.out.println("error Message:"+e.getMessage());
			e.printStackTrace();
		} finally{
			if (str2!=null){
				return str2;
			}
			else return null;
		}
		
		
	  }

	
	public static String getDeviceName() throws IOException{
		String name=null;
	    Runtime runtime = Runtime.getRuntime();
	    Process proc = runtime.exec("adb get-serialno");
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
//	    System.out.println(str1);
	    name=str1.trim();
	    } catch (InterruptedException e) {
	        System.err.println(e);
	    }finally{
	        try {
	            proc.destroy();
	        } catch (Exception e2) {
	        }
	    }

		return name;
		
	  }
	
	public static ArrayList<String> getDevices() throws IOException{
		ArrayList<String> devices = new ArrayList<String>();
		try{  
		    Runtime runtime = Runtime.getRuntime();
		    Process proc = runtime.exec("adb devices");
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
		    //判断是否有设备连接。
		    if(str1.contains("device ")){
//		    	System.out.println("有设备链接");
		    	String[] a = str1.split("attached ");
		    	 String[] b =a[1].split("device");
//				    String[] b = a[1].split("device");
				    //用循环来切割
				    for(int i=0;i<b.length-1;++i){
				    	b[i]=b[i].trim();
				    	devices.add(b[i]);
				    }
		    }
		    else{
		    	return null;
		    }
		
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }
			}finally{
				System.out.println("OK");
			}
		return devices;
		
	}


}

  

