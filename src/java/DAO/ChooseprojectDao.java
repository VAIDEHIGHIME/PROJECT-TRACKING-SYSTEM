package DAO;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChooseprojectDao
{
   
    
    public boolean insertTestID(String TestID, int EID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            //String query="SELECT registrationdetails.Role from registrationdetails, empidemailmapping, logindetails where empidemailmapping.Email = registrationdetails.`Email` and empidemailmapping.`EmpID`= logindetails.`EmpID` and logindetails.password=? and logindetails.`EmpID`=?";
            
            String query="UPDATE e_ticket SET Test_ID =? where E_ID = ?";
            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                 ps.setString(1, TestID);
                ps.setInt(2, EID);System.out.print(ps);
               
                int rs=ps.executeUpdate();
             
                if(rs != 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
           return false;
        } 
    }
    
    public void NotifDev()
    {
        //AdminOperations().
        
    }
    
    
    
    public void openProjectLink(String link) throws MalformedURLException, URISyntaxException
    {
        openWebpage(new URL(link).toURI());
       
    }

    


   private static boolean openWebpage(URI uri)
   {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
        {
            try 
            {
                desktop.browse(uri);
                return true;
            }
            catch (IOException e)
            {
            }
    }
    return false;
}
    public String GetLink(int prjID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            //String query="SELECT registrationdetails.Role from registrationdetails, empidemailmapping, logindetails where empidemailmapping.Email = registrationdetails.`Email` and empidemailmapping.`EmpID`= logindetails.`EmpID` and logindetails.password=? and logindetails.`EmpID`=?";
            
            String query="select Zip_File from e_ticket where E_ID = ?";
            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                 
                ps.setInt(1, prjID);
               
                ResultSet rs=ps.executeQuery();
             
                if(rs.next())
                {
                    return rs.getString(1);
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
           return null;
        } 
    }
    
    
    public String GetFile(int EID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            //String query="SELECT registrationdetails.Role from registrationdetails, empidemailmapping, logindetails where empidemailmapping.Email = registrationdetails.`Email` and empidemailmapping.`EmpID`= logindetails.`EmpID` and logindetails.password=? and logindetails.`EmpID`=?";
            
            String query="select Explanation from e_ticket where E_ID = ?";
            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                 
                ps.setInt(1, EID);
               
                ResultSet rs=ps.executeQuery();
             
                if(rs.next())
                {
                    return rs.getString(1);
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
           return null;
        } 
    }
    
    public String getEticket(String prjID) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            //String query="SELECT registrationdetails.Role from registrationdetails, empidemailmapping, logindetails where empidemailmapping.Email = registrationdetails.`Email` and empidemailmapping.`EmpID`= logindetails.`EmpID` and logindetails.password=? and logindetails.`EmpID`=?";
            
            String query="select E_ID from e_ticket where Proj_ID = ?";
            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                 
                ps.setString(1, prjID);
               
                ResultSet rs=ps.executeQuery();
             
                if(rs.next())
                {
                    return rs.getString(1);
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
           return null;
        } 
    }

    
}
