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
//    public void clearEmployees() {
//        employees.clear();
//    }
        
    public void save()
    {
        BinaryFileWriter writer = new BinaryFileWriter();
        writer.connectToFile(OUTPUT_FILE_NAME);
        
        for(Employee employee:employees)
        {
            writer.writeString(employee.getNickname());
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
        employees.clear();
        
        Employee e;
                
        BinaryFileReader reader = new BinaryFileReader();
        reader.connectToFile(OUTPUT_FILE_NAME);
        
        while(reader.notEOF())
        {
            e=new Employee();
            e.setNickname(reader.readString());
            e.setFname(reader.readString());
            e.setMname(reader.readString());
            e.setLname(reader.readString());
            e.setMonthlySalary(reader.readDouble());
            e.setCola(reader.readDouble());
            employees.add(e);
        }
        reader.close();        
    }

    //add employees that don't already exist in the employees array
    void generateFromStringArray(EmployeeNameList employeenamelist) {
        ArrayList<Employee> temp=new ArrayList<Employee>();
        for(String name:employeenamelist)
        {
            if(!employees.contains(name))
            temp.add(new Employee(name,"","","",0d,0d));
        }
        save();
    }
}
