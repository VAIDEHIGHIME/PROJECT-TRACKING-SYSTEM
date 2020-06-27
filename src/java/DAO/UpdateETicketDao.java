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
public class UpdateETicketDao {
    public boolean check(String zipLink,String E_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="UPDATE e_ticket SET Zip_File=?,Recheck=? WHERE E_ID=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,zipLink);
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
    
    
     public boolean checkTester(int E_ID,String Is_Solved, String recheck,String comments, String EM,String file) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="UPDATE e_ticket SET Error_Message=?,Recheck=?,Is_Solved=?,Comments=?,File_Name=? WHERE E_ID=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,EM);
            ps.setString(2,recheck);
              ps.setString(3,Is_Solved);
            ps.setString(4,comments);
            ps.setString(5,file);
            ps.setInt(6,E_ID);
            System.out.println(ps);
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
