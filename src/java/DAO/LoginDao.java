package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao
{
   
    
    public String check(String EmpID,String password) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            //String query="SELECT registrationdetails.Role from registrationdetails, empidemailmapping, logindetails where empidemailmapping.Email = registrationdetails.`Email` and empidemailmapping.`EmpID`= logindetails.`EmpID` and logindetails.password=? and logindetails.`EmpID`=?";
            
            String query="select registrationdetails.`Role` from logindetails, registrationdetails, empidemailmapping where registrationdetails.`Email`= empidemailmapping.`Email` and logindetails.`EmpID`=? and logindetails.password=? and logindetails.`EmpID`=empidemailmapping.`EmpID`";
            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, EmpID);
                ps.setString(2, password);
                ResultSet rs=ps.executeQuery();
             
                if(rs.next())
                {
                    return(rs.getString(1));
                }
                else
                {
                    return(null);
                }
            }
            else
            {
                return(null);
            }
        }
        catch(SQLException e)
        {
           return (null);
        } 
    }
}
