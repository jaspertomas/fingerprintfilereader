/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.TreeMap;

/**
 *
 * @author jaspertomas
 * This is a map where String is the date in the format yyyy/mm/dd
 * and DailyEmployeeData contains 2 time records: an in time and an out time
 * it contains all the records from the file
 * example
 * employee 1  2013/01/02  9:00 am  5:00 pm
 * employee 2  2013/01/02  9:00 am  5:00 pm
 */
public class DailyEmployeesData extends TreeMap<String, DailyEmployeeData>{
}
