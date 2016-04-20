package Utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReUtils
{
  public static ArrayList<String> matchString(Pattern pattern, String str)
  {
    ArrayList result = new ArrayList();
    Matcher mc = pattern.matcher(str);

    while (mc.find()) {
      result.add(mc.group());
    }

    return result;
  }

  public static ArrayList<Integer> matchInteger(Pattern pattern, String str) {
    ArrayList result = new ArrayList();
    Matcher mc = pattern.matcher(str);

    while (mc.find()) {
      result.add(new Integer(mc.group().trim()));
    }

    return result;
  }
}