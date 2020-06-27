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
public class RatingDao {
    public boolean check(String Emp_ID,String star) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            String query="select Rating_avg,Num_Reviews from employee_alloted where Emp_ID = ?";

            if(con!=null)
            {  
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Emp_ID);
           // System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Float Rating_avg=Float.parseFloat(rs.getString("Rating_avg"));
            Integer Num_Reviews=Integer.parseInt(rs.getString("Num_Reviews"));
            Integer star1=Integer.parseInt(star);
            Float new_rev = (float)(Rating_avg*Num_Reviews)+star1;
            new_rev = (float) new_rev/(Num_Reviews+1);
            query="UPDATE `employee_alloted` SET `Rating_avg` = ? , `Num_Reviews` = ? WHERE `Emp_ID`=?";
            ps=con.prepareStatement(query);
            ps.setString(1,Float.toString(new_rev));
            ps.setString(2,Integer.toString(Num_Reviews+1));
            ps.setString(3,Emp_ID);
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
