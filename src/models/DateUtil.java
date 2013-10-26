/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jaspertomas
 */
public class DateUtil {
    
    /*
     * USAGE:
        String a,b;
        a="2013/09/09";
        b="2013/09/16";
        //if a.compareTo(b) == -1, then a is earlier than b
        System.out.println(DateUtil.isLaterThan(b, b));
    */
    public static boolean isEqualTo(String a, String b)
    {
        return a.compareTo(b)==0;
    }
    public static boolean isLaterThan(String a, String b)
    {
        return a.compareTo(b)>0;
    }
    public static boolean isEarlierThan(String a, String b)
    {
        return a.compareTo(b)<0;
    }
    public static boolean isLaterThanOrEqualTo(String a, String b)
    {
        return a.compareTo(b)>=0;
    }
    public static boolean isEarlierThanOrEqualTo(String a, String b)
    {
        return a.compareTo(b)<=0;
    }
    
}
