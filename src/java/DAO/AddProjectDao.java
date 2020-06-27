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
public class AddProjectDao {
    public boolean check(String Proj_Name,String Dept_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="SELECT MAX(Proj_ID) FROM project";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.print("accesing problem");
            Integer Proj_ID = Integer.valueOf(rs.getString("MAX(Proj_ID)"));
            System.out.print("project id last"+Proj_ID);
            Proj_ID += 1;
            query="INSERT INTO project (`Proj_ID`,`Proj_Name`, `Dept_ID`) VALUES (?,?,?)";
            ps=con.prepareStatement(query);
            ps.setString(1,Integer.toString(Proj_ID));
            ps.setString(2,Proj_Name);
            ps.setString(3,Dept_ID);
            System.out.print(ps);
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