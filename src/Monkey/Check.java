package Monkey;

import java.io.IOException;

import Utils.ShellUtils;

class Check extends Thread{
	
  Check()
  {
  }

  public void run()
  {
    try
    {
    	String currentActivity = Monkey_Menu.getFocusedPackageAndActivity();
        if ((currentActivity.contains("WebViewUI")) || (currentActivity.contains("ShareableWebViewActivity")) || (currentActivity.contains("RealTradeWebView"))) {
          ShellUtils.cmd("adb shell input keyevent 4");
          System.out.println("执行返回");
        }
    }
    catch (IndexOutOfBoundsException e)
    {
      e.printStackTrace();
    }
  }
}
