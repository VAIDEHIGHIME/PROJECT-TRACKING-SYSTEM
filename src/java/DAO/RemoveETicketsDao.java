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
public class RemoveETicketsDao {
    public boolean check(String Proj_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="DELETE FROM e_ticket WHERE Proj_ID = ? and Is_Solved = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Proj_ID);
            ps.setString(2,"TRUE");
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
        {System.out.print("in exception @ addDept"+e);
           return (false);
        } 
        
    }
    
}
