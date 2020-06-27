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
public class AddDepartmentDao {
    public boolean check(String manager_id,String Department_name) throws SQLException
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
            Department_name=rs.getString("Dept_ID");
            System.out.print("first querry done1"+Department_name);
            query="select Name from registrationdetails,empidemailmapping where registrationdetails.Email=empidemailmapping.Email and empidemailmapping.EmpID = ?";
            ps=con.prepareStatement(query);
            ps.setString(1,manager_id);
            rs = ps.executeQuery();
            rs.next();
            String manager_name=rs.getString("Name");
            System.out.print("second querry done"+manager_name);
            query="INSERT INTO manager (`Mang_ID`,`Mang_Name`,`Dept_ID`) VALUES (?,?,?)";
            ps=con.prepareStatement(query);
            ps.setString(1,manager_id);
            ps.setString(2,manager_name);
            ps.setString(3,Department_name);
            int rss=ps.executeUpdate();
            
            
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
