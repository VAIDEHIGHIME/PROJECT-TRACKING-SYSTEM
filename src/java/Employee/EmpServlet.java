/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import DAO.AdminOperations;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author h p
 */
public class EmpServlet extends HttpServlet {

   private EmpDao empDao;
   //private static final long serialVersionUID = 1 L;
   @Override
    public void init() {
        empDao = new EmpDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print("AJAHAIENIdj");
           
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
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/accept":
                    
                    acceptRequest(request,response);
                    
                   
                    break;
                case "/deny":
                    //insertUser(request, response);
                    break;
                default:
                    listEmp(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (MessagingException ex) {
           Logger.getLogger(EmpServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    throws ServletException, IOException {
        doGet(request, response);
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
    
    
      private void listEmp(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
          System.out.println("hellllllllllllloooooooooooo");
        List < Emp > listEmp = empDao.selectAllUsers();
        request.setAttribute("listEmp", listEmp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmpList.jsp");
        dispatcher.forward(request, response);
    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        dispatcher.forward(request, response);
//    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        User existingUser = userDAO.selectUser(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);
//
//    }

//    private void insertUser(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//        User newUser = new User(name, email, country);
//        userDAO.insertUser(newUser);
//        response.sendRedirect("list");
//    }

//    private void updateUser(HttpServletRequest request, HttpServletResponse response)
//    throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//
//        User book = new User(id, name, email, country);
//        userDAO.updateUser(book);
//        response.sendRedirect("list");
//    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
       String Email = (request.getParameter("email"));
        empDao.deleteUser(Email);
        response.sendRedirect("list");

    }

    private void acceptRequest(HttpServletRequest request, HttpServletResponse response) throws MessagingException, IOException
    {
        AdminOperations admin=new AdminOperations();
        String empID=admin.generateEmpID();
        admin.sendEmail(request.getParameter("email"), "hi", "blah");
        response.sendRedirect("list");
      //  admin.deleteRequest(request.getParameter("email"));
       // admin.insertValidUser(empID, request.getParameter("password"));
      //  admin.giveAccess(request.getParameter("email"), empID);
    }
    
    

}
