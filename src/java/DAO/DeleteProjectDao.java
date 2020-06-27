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
public class DeleteProjectDao {
    public boolean check(String Proj_ID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
            String query="select Emp_ID,Emp_Name,Role,Skillset,Rating_avg,Num_Reviews,YearsOfExp from employee_alloted where Proj_ID = ?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,Proj_ID);
            System.out.print(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
            String Emp_ID=rs.getString("Emp_ID");
            String Emp_Name=rs.getString("Emp_Name");
            String Role=rs.getString("Role");
            String Skillset=rs.getString("Skillset");
            String Rating_avg=rs.getString("Rating_avg");
            String Num_Reviews=rs.getString("Num_Reviews");
            String YearsOfExp=rs.getString("YearsOfExp");
            query="INSERT INTO employee_not_alloted (`Emp_ID`,`Emp_Name`, `Role`,`Skillset`,`Rating_avg`,`Num_Reviews`,`YearsOfExp`) VALUES (?,?,?,?,?,?,?)";
            ps=con.prepareStatement(query);
            ps.setString(1,Emp_ID);
            ps.setString(2,Emp_Name);
            ps.setString(3,Role);
            ps.setString(4,Skillset);
            ps.setString(5,Rating_avg);
            ps.setString(6,Num_Reviews);
            ps.setString(7,YearsOfExp);
            int rss=ps.executeUpdate();
            if(rss>0)
                {
                    query="SET foreign_key_checks = 0;";
                    ps=con.prepareStatement(query);
                    rss=ps.executeUpdate();
                    query="DELETE FROM employee_alloted WHERE Emp_ID = ?";
                    ps=con.prepareStatement(query);
                    ps.setString(1,Emp_ID);
                    int rss1=ps.executeUpdate();
                    if(rss1>0)
                    {
                        query="SET foreign_key_checks = 1;";
                        ps=con.prepareStatement(query);
                        int rss2=ps.executeUpdate();
                    }
                    else
                    {
                        return(false);
                    }
                }
                else
                {
                    return(false);
                    
                }
            }
            System.out.println("completed deleting employees");
            query="SET foreign_key_checks = 0;";
            ps=con.prepareStatement(query);
            int rss=ps.executeUpdate();
            query="DELETE FROM e_ticket WHERE Proj_ID = ?";
            ps=con.prepareStatement(query);
            ps.setString(1,Proj_ID);
            System.out.println(ps);
            int rss1=ps.executeUpdate();
            query="SET foreign_key_checks = 1;";
                ps=con.prepareStatement(query);
                int rss2=ps.executeUpdate();
            System.out.println("completed deleting e_tickets");
            query="SET foreign_key_checks = 0;";
            ps=con.prepareStatement(query);
            rss=ps.executeUpdate();
            query="DELETE FROM project WHERE Proj_ID = ?";
            ps=con.prepareStatement(query);
            ps.setString(1,Proj_ID);
            rss1=ps.executeUpdate();
            if(rss1>0)
            {
                query="SET foreign_key_checks = 1;";
                ps=con.prepareStatement(query);
                rss2=ps.executeUpdate();
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
