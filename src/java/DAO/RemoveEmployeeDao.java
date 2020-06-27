/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Sravanthi
 */
public class RemoveEmployeeDao {
    public boolean check(String Emp_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select Emp_Name,Role,Skillset,Rating_avg,Num_Reviews,YearsOfExp from employee_alloted where Emp_ID = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Emp_ID);
            //System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Emp_Name=rs.getString("Emp_Name");
            String Role=rs.getString("Role");
            String Skillset=rs.getString("Skillset");
            float Rating_avg=rs.getFloat("Rating_avg");
            int Num_Reviews=rs.getInt("Num_Reviews");
            int YearsOfExp=rs.getInt("YearsOfExp");
            query="INSERT INTO employee_not_alloted (`Emp_ID`,`Emp_Name`, `Role`,`Skillset`,`Rating_avg`,`Num_Reviews`, `YearsOfExp`) VALUES (?,?,?,?,?,?,?)";
            ps=con.prepareStatement(query);
            ps.setString(1,Emp_ID);
            ps.setString(2,Emp_Name);
            ps.setString(3,Role);
            ps.setString(4,Skillset);
            ps.setFloat(5,Rating_avg);
            ps.setInt(6,Num_Reviews);
            ps.setInt(7,YearsOfExp);
            int rss=ps.executeUpdate();
            
            
            if(rss>0)
                {
                    query="SET foreign_key_checks = 0;";
                    ps=con.prepareStatement(query);
                    rss=ps.executeUpdate();
                    query="DELETE FROM employee_alloted WHERE Emp_ID = ?";
                    ps=con.prepareStatement(query);
                    ps.setString(1,Emp_ID);
                    rss=ps.executeUpdate();
                    if(rss>0)
                    {
                        query="SET foreign_key_checks = 1;";
                        ps=con.prepareStatement(query);
                        rss=ps.executeUpdate();
                        return(true);
                    }
                    else
                    {
                        return(false);
                    }
                }
                else
                {
                    return(false);
                    
                }
            }
            else
            {System.out.print("in con failed");
                return(false);
            }
            
        }
        catch(SQLException e)
        {System.out.print("in frecking exception @ addDept"+e);
           return (false);
        } 
        
    }
    
}
