/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Sravanthi
 */
public class AddEmployeeDao {
    public boolean check(int Proj_ID,String Emp_ID,int Dept_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select Emp_Name,Role,Skillset,Rating_avg,Num_Reviews from employee_not_alloted where Emp_ID = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Emp_ID);
            //System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            if( rs.next())
            {
                String Emp_Name=rs.getString("Emp_Name");
                String Role=rs.getString("Role");
                String Skillset=rs.getString("Skillset");
                float Rating_avg=rs.getFloat("Rating_avg");
                int Num_Reviews=rs.getInt("Num_Reviews");
                query="INSERT INTO employee_alloted (`Emp_ID`,`Dept_ID`,`Proj_ID`,`Emp_Name`, `Role`, `Skillset`,`Num_Reviews`,`Rating_avg`) VALUES (?,?,?,?,?,?,?,?)";
                ps=con.prepareStatement(query);
                ps.setString(1,Emp_ID);
                ps.setInt(2,Dept_ID);
            ps.setInt(3,Proj_ID);
             ps.setString(4, Emp_Name);
            ps.setString(5,Role);
            ps.setString(6,Skillset);
             ps.setInt(7,Num_Reviews);
            ps.setFloat(8,Rating_avg);
           System.out.println(ps);
            int rss=ps.executeUpdate();
            
            
            if(rss>0)
                {
                    query="DELETE FROM employee_not_alloted WHERE Emp_ID = ?";
                    ps=con.prepareStatement(query);
                    ps.setString(1,Emp_ID);
                    rss=ps.executeUpdate();
                    if(rss>0)
                    {
                        return(true);
                    }
                    else
                    {
                        return(false);
                    }
                }
                else
                {out.print("else1");
                    return(false);
                    
                }
            }
           else{out.print("else");}}
            else
            {out.print("in con failed");
                return(false);
            }
            
        }
        catch(SQLException e)
        {
            out.print("in frecking exception @ addDept"+e);
           return (false);
        } 
        
    return true;
    }  
}
