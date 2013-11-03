/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author jaspertomas
 */
public class EmployeeList extends ArrayList<Employee>{
    public Employee getByNickname(String name)
    {
        for(Employee e:this)
        {
            if(e.getNickname().contentEquals(name))
            {
                return e;
            }
        }
        return null;
    }
}
