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
import javax.mail.MessagingException;

/**
 *
 * @author h p
 */
public class AdminOperations {
   
    
    public String generateEmpID()
    {
        String empID="";
       
        
        try
         {
            DBAO conObj=new DBAO();
            Connection con=conObj.connectToDataBase();
             String query = "select MAX(`EmpID`) from `logindetails`;";
             if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
               
                
              
                ResultSet rs=ps.executeQuery();  
                rs = ps.executeQuery();
                
                
                if(rs.next())
                {
                    
                    
                    int EmpID_prev = Integer.parseInt(rs.getString(1));
                    EmpID_prev +=1;
                    empID=Integer.toString(EmpID_prev);
                    
                    
                }
                else{
                    
                   empID = "03062020";
                    
                }
            }
         }
        catch(Exception ex)
        {
            
        }
             
        return empID;
        
    }
    
   
   
   
 
 
    public void sendEmail(String recipient, String subject, String content) throws MessagingException
    {
        String myAccount = "svsbugtrackingsystem@gmail.com";
        String pass = "SvsBugTrack@123";
       sendemail.sendMail(myAccount, pass, recipient,subject,content);
    }
    String query = null;
    public boolean deleteRequest(String email)
    {
         try
         {
            DBAO conObj=new DBAO();
            query ="DELETE FROM `queuedForApproval` where `Email` = ?" ;
            Connection con=conObj.connectToDataBase();
             if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, email );
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                   // out.println("shd return true");
                    return(true);
                }
                else
                {
                   // out.print("in else");
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
    
    public boolean giveAccess(String email, String empID)
    {
        
        try
         {
            DBAO conObj=new DBAO();
            query="INSERT INTO `empidemailmapping` (`Email`,`EmpID`) VALUES (?,?)";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, email);

                
                ps.setString(2, empID);
        
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                   // out.println("shd return true");
                    return(true);
                }
                else
                {
                   // out.print("in else");
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
    
     public String GetPass(String email)
    {
         try
         {
            DBAO conObj=new DBAO();
            query ="select password FROM `registrationdetails` where `Email` = ?" ;
            Connection con=conObj.connectToDataBase();
             if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, email );System.out.print(ps);
                ResultSet rs=ps.executeQuery();  
                
                if(rs.next())
                {
                   // out.println("shd return true");
                    return(rs.getString(1));
                }
                else
                {
                   // out.print("in else");
                    return(null);
                }
            }
            else
            {
                //out.print("not connected to db");
                return(null);
            }
        }
        catch(SQLException e2)
        {
           //out.print(e2);
           return (null);
        } 
        
    }
    
    
    
    public boolean insertValidUser(String empID,String Email)
    {
        
       try
         {
            DBAO conObj=new DBAO();
            String password=GetPass(Email);
            String query="INSERT INTO `Logindetails` (`EmpID`,`password`) VALUES (?,?)";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, empID);

                ps.setString(2, password);
              
        System.out.print(ps);
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                   System.out.println("shd return true");
                    return(true);
                }
                else
                {
                   // out.print("in else");
                     System.out.println("else1");
                    return(false);
                }
            }
            else
            {
                 System.out.println("else2");
                //out.print("not connected to db");
                return(false);
            }
        }
        catch(SQLException e2)
        {
             System.out.println("else3");
           //out.print(e2);
           return (false);
        } 
    
        
    }
    
    public boolean addRequest(String Name,String password, String Role, String email, String yoe, String skill)
    {
        
          try
         {
            DBAO conObj=new DBAO();
            query="INSERT INTO `queuedForApproval` (`Name`,`Password`,`Role`, `Email`, `YearsOfExp`, `Skills`) VALUES (?,?,?,?,?,?)";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, Name);
                ps.setString(2,password);
                ps.setString(3, Role);
                ps.setString(4, email);
                ps.setString(5, yoe);
                ps.setString(6, skill);
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

    public boolean removePermanently(String email)
    {
        try
         {
            DBAO conObj=new DBAO();
            query ="DELETE FROM `registrationdetails` where `Email` = ?" ;
            Connection con=conObj.connectToDataBase();
             if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, email );
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                   // out.println("shd return true");
                    return(true);
                }
                else
                {
                   // out.print("in else");
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
    
    
    public boolean EmpNotAlloted(String Name,String EmpID, String Role, String yoe, String skill)
    {
        
          try
         {
            DBAO conObj=new DBAO();
            query="INSERT INTO `employee_not_alloted` (`Emp_ID`,`Emp_Name`,`Role`, `Skillset`,`YearsOfExp`,`Rating_avg`,`Num_Reviews`) VALUES (?,?,?,?,?,?,?)";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, EmpID);
                ps.setString(2, Name);
                ps.setString(3, Role);
                 ps.setString(4, skill);
                ps.setString(5, yoe);
               
                ps.setString(6, "0");
                ps.setString(7, "0");
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
    
    
    //Get Email
    public String GetMail(String empID)
    {
         try
         {
            DBAO conObj=new DBAO();
            query ="select Email FROM `empidemailmapping` where `EmpID` = ?" ;
            Connection con=conObj.connectToDataBase();
             if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, empID );System.out.print(ps);
                ResultSet rs=ps.executeQuery();  
                
                if(rs.next())
                {
                   System.out.println(rs.getString("Email"));
                    return(rs.getString("Email"));
                }
                else
                {
                   // out.print("in else");
                    return(null);
                }
            }
            else
            {
                //out.print("not connected to db");
                return(null);
            }
        }
        catch(SQLException e2)
        {
           //out.print(e2);
           return (null);
        } 
        
    }
    //delete manager permanently
    public boolean del_manager(String empID,String email)
    {
        try
         {
            DBAO conObj=new DBAO();
            query ="DELETE FROM `logindetails` where `EmpID` = ?" ;
            Connection con=conObj.connectToDataBase();
             if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1, empID );
                int rs=ps.executeUpdate();  
                
                if(rs>0)
                {
                     query ="DELETE FROM `empidemailmapping` where `EmpID` = ?" ;
                      ps=con.prepareStatement(query);
                        ps.setString(1, empID );
                       rs=ps.executeUpdate(); 
                         if(rs>0)
                         {
                            query ="DELETE FROM `del_manager` where `Mang_ID` = ?" ;
                             ps=con.prepareStatement(query);
                               ps.setString(1, empID );
                              rs=ps.executeUpdate(); 
                               if(rs>0)
                         {
                            query ="DELETE FROM `registrationdetails` where `Email` = ?" ;
                             ps=con.prepareStatement(query);
                               ps.setString(1, email );
                              rs=ps.executeUpdate(); if(rs>0){
                   // out.println("shd return true");
                              return(true);}else{return false;}
                         
                         }else{return false;}
                         }
                         else{
                             return false;
                         }
                }
                else
                {
                   // out.print("in else");
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
