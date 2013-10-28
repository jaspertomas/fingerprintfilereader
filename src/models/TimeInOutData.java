/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;

/**
 *
 * @author jaspertomas
 */
public class TimeInOutData {
    String name,date;
    TimeRecord in,out;

    public TimeRecord getIn() {
        return in;
    }

    public void setIn(TimeRecord in) {
        this.in = in;
    }

    public TimeRecord getOut() {
        return out;
    }

    public void setOut(TimeRecord out) {
        this.out = out;
    }
    
    public String getInTimeString()
    {
        Time time=in.getTime();
        return 
                String.format("%02d", time.getHours())
//                +"\t"
                +String.format("%02d", time.getMinutes());
    }
    public String getOutTimeString()
    {
        Time time=out.getTime();
        return 
                String.format("%02d", time.getHours())
//                +"\t"
                +String.format("%02d", time.getMinutes());
    }
    
    
}
