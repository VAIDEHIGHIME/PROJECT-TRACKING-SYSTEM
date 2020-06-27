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
public class RemoveManagerController extends HttpServlet {

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
            String Mang_ID=(String) request.getParameter("mang_ID");
            out.print(Name);
           // String Dept_ID=(String) request.getParameter("Dept_ID");
           
            
            

            String acceptStat=request.getParameter("remove");
            




            if(acceptStat!=null)
            {
              
               
               String email = admin.GetMail(Mang_ID);System.out.print("blah "+email);
              // admin.removePermanently(email);
               admin.del_manager(Mang_ID,email);
                String subject = "Regarding SVS Registration";
                String content = "Hello "+Name+"! You have been Succcessfully removed from SVS Bug Tracking System. Thank you for your generous service!";
               admin.sendEmail(email, subject, content);
               



                response.sendRedirect("RemoveManager.jsp");
                
                // mail has been sent

   


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
