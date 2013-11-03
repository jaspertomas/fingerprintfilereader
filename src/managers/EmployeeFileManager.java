/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.util.ArrayList;
import models.Employee;
import models.EmployeeNameList;
import utils.fileaccess.BinaryFileReader;
import utils.fileaccess.BinaryFileWriter;

/**
 *
 * @author jaspertomas
 */
public class EmployeeFileManager {
    //---------------SINGLETON-------------------
    static EmployeeFileManager instance;
    private EmployeeFileManager(){};
    public static EmployeeFileManager getInstance(){
        if(instance==null)instance= new EmployeeFileManager();
        return instance;
    }
    //------------------CONSTANTS----------------
    private static final String OUTPUT_FILE_NAME = "fingerprint.dat";
    //------------------VARIABLES----------------

    ArrayList<Employee> employees = new ArrayList<Employee>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void clearEmployees() {
        employees.clear();
    }
        
    public void save()
    {
        BinaryFileWriter writer = new BinaryFileWriter();
        writer.connectToFile(OUTPUT_FILE_NAME);
        
        for(Employee employee:employees)
        {
            writer.writeString(employee.getFname());
            writer.writeString(employee.getMname());
            writer.writeString(employee.getLname());
            writer.writeDouble(employee.getMonthlySalary());
            writer.writeDouble(employee.getCola());
        }
        writer.close(); 
    }
    public void load()
    {
        BinaryFileReader reader = new BinaryFileReader();
        reader.connectToFile(OUTPUT_FILE_NAME);
        
        while(reader.notEOF())
        {
//            reader.readString(employee.getFname());
//            reader.readString(employee.getMname());
//            reader.readString(employee.getLname());
//            reader.readDouble(employee.getMonthlySalary());
//            reader.readDouble(employee.getCola());
            
        }
        reader.close();        
    }

    void generateFromStringArray(EmployeeNameList employeenamelist) {
        for(String name:employeenamelist)
        {
            employees.add(new Employee(name,"","",0d,0d));
        }
        save();
    }
}
