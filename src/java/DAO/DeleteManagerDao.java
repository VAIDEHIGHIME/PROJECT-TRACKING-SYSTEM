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
public class DeleteManagerDao {
    public boolean check(String Mang_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select * from manager where Mang_ID = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Mang_ID);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            String Dept_ID,Mang_Name;
            Integer count=0;
            if(rs.next())
            {
                count=count+1;
                Dept_ID=rs.getString("Dept_ID");
                Mang_Name=rs.getString("Mang_Name");
                query="INSERT INTO del_manager (`Mang_ID`,`Mang_Name`, `Dept_ID`) VALUES (?,?,?)";
                ps=con.prepareStatement(query);
                ps.setString(1,Mang_ID);
                ps.setString(2,Mang_Name);
                ps.setString(3,Dept_ID);
                int rss=ps.executeUpdate();
                if(rss==0)
                {
                    return(false);
                }
                
            
           
                System.out.print("first querry done1");
//            
//                query="select * from manager where Mang_ID = ?";
//                ps=con.prepareStatement(query);
//                ps.setString(1,Mang_ID);
//                System.out.print(ps);
//                rs = ps.executeQuery();
//                if(rs.next())
//                {
                    String query1="DELETE FROM manager WHERE Mang_ID = ?";
                    PreparedStatement ps1=con.prepareStatement(query1);
                    ps1.setString(1,Mang_ID);
                    int rss1=ps1.executeUpdate();
                    if(rss1==0)
                    {
                        return(false);
                    }
                
                query="UPDATE registrationdetails, empidemailmapping SET registrationdetails.Valid = ? WHERE empidemailmapping.EmpID=? and registrationdetails.Email = empidemailmapping.Email";
                ps=con.prepareStatement(query);
                ps.setInt(1,0);
                ps.setString(2,Mang_ID);        
                rss1=ps.executeUpdate();
                if(rss1==0)
                {
                    return(false);
                }
                
            
            //query="SET GLOBAL foreign_key_checks = 1;";
            //ps=con.prepareStatement(query);
            //ps.executeUpdate();
            System.out.print("second querry done1");
            return(true);
            
            
            
//            }
//                 else
//            {System.out.print("in on failed");
//                return(false);
//            }
            
            } else
            {System.out.print("in on failed");
                return(false);
            }
            }
            else
            {System.out.print("in on failed");
                return(false);
            }
            
        }
        catch(SQLException e)
        {System.out.print("in frecking exception @ addDept"+e);
           return (false);
        } 
        
    }
    
}
