/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.util.ArrayList;
import models.Employee;
import models.EmployeeList;
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

    EmployeeList employees = new EmployeeList();

    public EmployeeList getEmployees() {
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
        boolean result=reader.connectToFile(OUTPUT_FILE_NAME);
        
        if(!result)
        {
            //file not found
            System.out.println("File fingerprint.dat not found");
            return;
        }
        
        while(reader.notEOF())
        {
            e=new Employee();
            e.setNickname(reader.readString());
            if(!reader.notEOF())break;
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
//        EmployeeList temp=new EmployeeList();
        
        //scan employee list for matching nickname; 
        //if it doesnt exist, add it
        for(String nickname:employeenamelist)
        {
            if(employees.getByNickname(nickname)==null)
            employees.add(new Employee(nickname,"","","",0d,0d));
        }
        save();
    }
}
