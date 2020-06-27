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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author Sravanthi
 */
public class ModifyETicketDao {
    public boolean check(String E_ID,String Explanation) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="UPDATE e_ticket SET Explanation=?,Recheck=? WHERE E_ID=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Explanation);
            ps.setString(2,"TRUE");
            ps.setString(3,E_ID);
            
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
