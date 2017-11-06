package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqliteDbHelper;
import utils.JsonHelper;

public class Deduction {
    //------------FIELDS-----------
    public static final String tablename="deduction";
    //field names
    public static String[] fields={
            "id"
            ,"week_id"
            ,"employee_id"
            ,"vale"
            ,"sss"
            ,"ph"
            ,"pi"
            ,"loan"
            ,"other"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"int(11)"
            ,"int(11)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            ,"decimal(10,2)"
            };
    //-----------------------

    public Integer id;
    public Integer week_id;
    public Integer employee_id;
    public Double vale;
    public Double sss;
    public Double ph;
    public Double pi;
    public Double loan;
    public Double other;

    public Deduction() {
    }
    public Deduction(ResultSet rs) {
        try {
            id=rs.getInt("id");
            week_id=rs.getInt("week_id");
            employee_id=rs.getInt("employee_id");
            vale=rs.getDouble("vale");
            sss=rs.getDouble("sss");
            ph=rs.getDouble("ph");
            pi=rs.getDouble("pi");
            loan=rs.getDouble("loan");
            other=rs.getDouble("other");
        } catch (SQLException ex) {
            Logger.getLogger(Deduction.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//	public String getUuid()
//	{
//		return id.toString()+"-";
//	}

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public Integer getWeekId() {
            return week_id;
    }

    public void setWeekId(Integer week_id) {
            this.week_id = week_id;
    }

    public Integer getEmployeeId() {
            return employee_id;
    }

    public void setEmployeeId(Integer employee_id) {
            this.employee_id = employee_id;
    }

    public Double getVale() {
            return vale;
    }

    public void setVale(Double vale) {
            this.vale = vale;
    }

    public Double getSss() {
            return sss;
    }

    public void setSss(Double sss) {
            this.sss = sss;
    }

    public Double getPh() {
            return ph;
    }

    public void setPh(Double ph) {
            this.ph = ph;
    }

    public Double getPi() {
            return pi;
    }

    public void setPi(Double pi) {
            this.pi = pi;
    }

    public Double getLoan() {
            return loan;
    }

    public void setLoan(Double loan) {
            this.loan = loan;
    }

    public Double getOther() {
            return other;
    }

    public void setOther(Double other) {
            this.other = other;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(week_id!=null?week_id.toString():null);
            values.add(employee_id!=null?employee_id.toString():null);
            values.add(vale!=null?vale.toString():null);
            values.add(sss!=null?sss.toString():null);
            values.add(ph!=null?ph.toString():null);
            values.add(pi!=null?pi.toString():null);
            values.add(loan!=null?loan.toString():null);
            values.add(other!=null?other.toString():null);

            return values;
    }
    public void delete()
    {
            Deductions.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    id=Deductions.insert(this);
            else
                    Deductions.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
