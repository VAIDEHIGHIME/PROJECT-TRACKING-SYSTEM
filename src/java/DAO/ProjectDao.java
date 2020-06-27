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
public class ProjectDao {
    public String check(String project_name) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select Proj_ID from project where Proj_Name = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,project_name);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String project_ID=rs.getString("Proj_ID");
            return(project_ID);
            }
            else
            {System.out.print("in con failed");
                return("false");
            }
            
        }
        catch(SQLException e)
        {System.out.print("in frecking exception @ proj"+e);
           return ("false");
        }
    }
}
        
    
    
