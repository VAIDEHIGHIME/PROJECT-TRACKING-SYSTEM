package DAO;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegisterDao
{
    
   
    
    public boolean addUser(String name,String password, String cpassword, String Role, String email, String yoe, String skill) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            String query="INSERT INTO `Registrationdetails` (`Name`, `Password`,`cPassword`, `Role`, `Email`, `YearsOfExp`, `Skills`) VALUES (?,?,?,?,?,?,?)";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, password);
                ps.setString(3, cpassword);
                ps.setString(4, Role);
                ps.setString(5, email);
                ps.setString(6, yoe);
                ps.setString(7, skill);
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                    out.println("shd return true");
                    return(true);
                }
                else
                {
                    out.print("in else");
                    return(false);
                }
            }
            else
            {
                //out.print("not connected to db");
                return(false);
            }
        }
        catch(SQLException e2)
        {
           //out.print(e2);
           return (false);
        } 
    }
}
