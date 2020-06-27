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
public class DeleteDepartmentDao {
    public boolean check(String Mang_ID,String Dept_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            
            
            
                String query="DELETE FROM manager WHERE Dept_ID = ? and Mang_ID = ?";
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1,Dept_ID);
                ps.setString(2,Mang_ID);
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
