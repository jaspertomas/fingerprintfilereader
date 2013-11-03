/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

/**
 *
 * @author jaspertomas
 */
public class EmployeeFileManager {
    //---------------SINGLETON-------------------
    static EmployeeFileManager instance;
    private EmployeeFileManager(){};
    public static EmployeeFileManager getInstance(){
        if(instance!=null)instance= new EmployeeFileManager();
        return instance;
    }
    //----------------------------------
}
