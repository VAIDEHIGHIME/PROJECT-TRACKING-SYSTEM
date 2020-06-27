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
public class DepartmentDao {
    public String check(String Department_name) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select Dept_ID from department where Dept_Name = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Department_name);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Department_ID=rs.getString("Dept_ID");
            return(Department_ID);
            }
            else
            {System.out.print("in con failed");
                return("false");
            }
            
        }
        catch(SQLException e)
        {System.out.print("in frecking exception @ addDept"+e);
           return ("false");
        }
    }
}
        
    
    
