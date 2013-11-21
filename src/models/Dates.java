/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author jaspertomas
 */
public class Dates{

    //---------------SINGLETON-------------------

    static Dates instance;

    public static Dates getInstance() {
        if (instance == null) {
            instance = new Dates();
        }
        return instance;
    }
    //---------------VARIABLES---------------------      
     ArrayList<String> items=new ArrayList<String>();

    public ArrayList<String> getItems() {
        return items;
    }
     
}
