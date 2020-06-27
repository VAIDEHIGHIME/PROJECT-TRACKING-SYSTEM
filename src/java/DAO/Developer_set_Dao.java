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
public class Developer_set_Dao {
    public String check(String Emp_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select * from employee_alloted where Emp_ID=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Emp_ID);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return("yes");
            }
            else
            {
                query="select * from employee_not_alloted where Emp_ID=?";
                ps=con.prepareStatement(query);
                ps.setString(1,Emp_ID);
                System.out.print(ps);
                rs = ps.executeQuery();
                if(rs.next())
                {
                    return("no");
                }
                else
                {
                    return("DNE");
                }
            }
            
            
            }
            else
            {
                System.out.print("in con failed");
                 return("DNE");
            }
            
        }
        catch(SQLException e)
        {
            System.out.print("in frecking exception @ addDept"+e);
            return("DNE");
        } 
        
    }
    
}
