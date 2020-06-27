package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ManagerRegistry
{
   
    
    public boolean addManager(String EmpID,String password) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            String query="INSERT INTO manager (`EmpID`, `password`) VALUES (?,?)";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, EmpID);
                ps.setString(2, password);
                int rs=ps.executeUpdate();
                if(rs>0)
                {
                    return(true);
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
        catch(SQLException e)
        {
           return (false);
        } 
    }
}
