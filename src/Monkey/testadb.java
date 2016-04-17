package Monkey;

import java.io.BufferedReader; 
import java.io.File;
import java.io.IOException;
import java.io.InputStream; 
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List; 


public class testadb {

	public static void main(String[] args) throws IOException {
////		String command = "-s 4df09e3913881133 shell monkey -p com.jhss.youguu"
//				+ " --ignore-crashes --ignore-timeouts --monitor-native-crashes "
//				+ "-s 500 --throttle 100  --pct-anyevent 15  --pct-touch 15 "
//				+ " --pct-motion 15  --pct-trackball 15  --pct-syskeys 15  "
//				+ "--pct-appswitch 15 -v-v-v 10";
		
//		String command = "shell monkey -p com.jhss.youguu 10";
		String command = "devices";
		
		ProcessBuilder pb = new ProcessBuilder("/Users/mac/Downloads/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb", command);
//		ProcessBuilder pb = new ProcessBuilder(new String[] {"sh","/c",command});
//		pb.directory(new File("/Users/David/Applications/android-sdk-macosx/platform-tools/adb", "version"));
		
		Process p = pb.start();
		
		InputStream is = p.getInputStream();
		InputStreamReader ir = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(ir);
		String line;
		System.out.printf("Output of running %s is:", Arrays.toString(args)); 
		System.out.println("start:");
		
		while((line = br.readLine())!=null){
			System.out.println(line+"\n");
		}

	}

}
