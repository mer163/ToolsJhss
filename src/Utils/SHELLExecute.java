package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SHELLExecute
{
  public static String runShell(String shellString)
  {
    StringBuilder sb = new StringBuilder();
    String line = "";
    try
    {
      Process process = Runtime.getRuntime().exec("adb shell " + shellString);
      InputStream is = process.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(
        is));
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (!line.equals(""))
        {
          sb.append(line);
          sb.append("\r\n");
        }
      }
      try { process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();

        System.out.println("执行CMD wait操作失败");
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("执行CMD失败");
      System.out.println(e.getMessage());
      return null;
    }
    return sb.toString();
  }

  public static void main(String[] args)
  {
    System.out.println(runShell("ps|grep com.jhss.youguu"));
  }
}