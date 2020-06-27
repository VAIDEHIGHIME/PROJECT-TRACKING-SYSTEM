package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class InsertDao
{
   
    
    public boolean runInsertQuery(String query,  ArrayList<String> params) throws SQLException
    {
        try
        {
            DBAO conObj=new DBAO();
            int count =1;
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps = con.prepareStatement(query);
                 for (int it = 0; it<params.size(); it++) {
                  ps.setString(count, params.get(it));
                  count++;
                }
              
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                    //out.println("shd return true");
                    return(true);
                }
                else
                {
                    //out.print("in else");
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
