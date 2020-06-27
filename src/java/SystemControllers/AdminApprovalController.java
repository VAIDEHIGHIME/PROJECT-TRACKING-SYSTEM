/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemControllers;

import DAO.AdminOperations;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author h p
 */
public class AdminApprovalController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        
       
        try (PrintWriter out = response.getWriter())
        {
            AdminOperations admin = new AdminOperations();


           // HttpSession se=request.getSession();
            String Name=(String) request.getParameter("name");
           // String password=(String) request.getParameter("password");
            String Role=(String) request.getParameter("role");
            String emailID=(String) request.getParameter("email");
            String yoe=(String) request.getParameter("yoe");
            String skill=(String) request.getParameter("skills");
            
            

            String acceptStat=request.getParameter("accept");
            String denyStat=request.getParameter("deny");

            String afterExamining=null;
            String status=null;
            String subject = "Regarding SVS Registration";
            String content=null;   




            if(acceptStat!=null && denyStat==null)
            {
                //accept
                String EmpID = admin.generateEmpID();
                out.print(EmpID);
                //email
                afterExamining="Please find your Employee ID below:"+ EmpID+" "
                    + " Now you can proceed with Login using this Employee ID.";
                status=" been selected";

                 //email
                     content = " Hello "+Name+", "
                    + " You have "+status+" for the role of "+Role+" ,you applied for in SVS Bug Tracking Systems. "
                    + afterExamining
                    + "Thank You! "
                    + "Regards, "
                    + "Sakshi "
                    + "Vaidehi "
                    + "Sravanthi ";



                try{
                            admin.sendEmail(emailID, subject, content);
                            admin.giveAccess(emailID,EmpID);
                            admin.insertValidUser(EmpID,emailID);
                            admin.deleteRequest(emailID) ;
                            
                            
                            admin.EmpNotAlloted(Name, EmpID, Role, yoe,skill);
                            response.sendRedirect("adminApprovals.jsp");

                            // mail has been sent
                }
                catch(MessagingException e)
                {
                    out.println ("<html><body><script>alert('Some Error Ocurred');</script></body></html>");
                }


                //delete entry from queuedForApproval table
                //mapping between EmpID and Email
                //login details 

   


            }
            else
            {
                if(denyStat!=null && acceptStat==null)
                {
                    status="not been selected";
                    afterExamining="You can try again later!"
                            + "All The Best!";
                    //deny 

                    content = " Hello "+Name+", "
                    + " You have "+status+" for the role of "+Role+" ,you applied for in SVS Bug Tracking Systems."
                    + afterExamining
                    + "Thank You!"
                    + "Regards,"
                    + "Sakshi"
                    + "Vaidehi"
                    + "Sravanthi";


                    try{
                            admin.sendEmail(emailID, subject, content);
                            // mail has been sent
                            //delete from registtration details
                            admin.removePermanently(emailID);
                            admin.deleteRequest(emailID);
                            
                             response.sendRedirect("adminApprovals.jsp");
                    }
                    catch(Exception e)
                    {
                        out.println ("<html><body><script>alert('Some Error Ocurred');</script></body></html>");
                    }

                }
            }
        }
        catch(Exception ex)
        {
            out.println(ex);
            
        }
            
            
            
        

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
