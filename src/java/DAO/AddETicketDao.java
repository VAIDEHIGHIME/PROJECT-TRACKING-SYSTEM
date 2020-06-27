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
public class AddETicketDao {
    public boolean check(String zipLink,String Emp_ID,int Proj_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select MAX(E_ID) from e_ticket";
            PreparedStatement ps=con.prepareStatement(query);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int E_ID = rs.getInt("MAX(E_ID)");
            E_ID += 1;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calobj = Calendar.getInstance();
            String date=df.format(calobj.getTime());
            System.out.println(date);
            
            query="INSERT INTO e_ticket (`E_ID`,`Proj_ID`, `Dev_ID`,`Date_Time`, `Zip_File`,`Is_Solved`,`Recheck`) VALUES (?,?,?,?,?,?,?)";
            ps=con.prepareStatement(query);
            ps.setString(1,Integer.toString(E_ID));
            ps.setInt(2,Proj_ID);
            ps.setString(3,Emp_ID);
            ps.setString(4,date);
            ps.setString(5,zipLink);
            ps.setString(6,"FALSE");
            ps.setString(7,"TRUE");
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
    
    public boolean checkTester(String EM,String Emp_ID,int E_id,String file) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select Dev_ID,Zip_File,Proj_ID from e_ticket where E_ID=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,E_id);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int Proj_ID=rs.getInt("Proj_ID");
            String Zip=rs.getString("Zip_File");
            String Dev_ID=rs.getString("Dev_ID");
            query="select MAX(E_ID) from e_ticket";
            ps=con.prepareStatement(query);
            System.out.print(ps);
            rs = ps.executeQuery();
            if(rs.next()){
            int E_ID = rs.getInt("MAX(E_ID)");
            E_ID += 1;
           
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calobj = Calendar.getInstance();
            String date=df.format(calobj.getTime());
            System.out.println(date);
            
            query="INSERT INTO e_ticket (`E_ID`,`Proj_ID`, `Dev_ID`,`Date_Time`,`Test_ID`, `Zip_File`,`Is_Solved`,`Recheck`,`Error_Message`,`File_Name`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(query);
            ps.setString(1,Integer.toString(E_ID));
            ps.setInt(2,Proj_ID);
            ps.setString(3,Dev_ID);
            ps.setString(4,date);
            ps.setString(5,Emp_ID);
            ps.setString(6, Zip);
            ps.setString(7,"FALSE");
            ps.setString(8,"TRUE");
            ps.setString(9, EM);
            ps.setString(10,file);
            int rss=ps.executeUpdate();
            
            
            if(rss>0)
                {
                    return(true);
                }
                else
                {
                    return(false);
                    
                }
            }}}
            else
            {System.out.print("in con failed");
                return(false);
            }
            
        }
        catch(SQLException e)
        {System.out.print("in frecking exception @ addDept"+e);
           return (false);
        } 
        return false;
        
    } 
    
}
