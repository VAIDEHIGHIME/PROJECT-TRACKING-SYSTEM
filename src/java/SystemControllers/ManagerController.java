/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemControllers;

import DAO.DBAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author h p
 */
public class ManagerController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           try
        {
            DBAO conObj=new DBAO();
            HttpSession session = request.getSession();
            String deptBTN = request.getParameter("Dept_Name");
            String query="SELECT Dept_ID from department where Dept_Name= ?";
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                
               
                ps.setString(1, deptBTN);
                ResultSet rs=ps.executeQuery();
                //out.print("blah");
                if(rs!=null){rs.next();
                int Dept_ID = rs.getInt("Dept_ID");
                out.print(Dept_ID);
                query="INSERT INTO `manager`(`Mang_ID`, `Dept_ID`) VALUES (?,?)";
                ps=con.prepareStatement(query);
                ps.setString(1, (String) session.getAttribute("empID"));
                ps.setInt(2, Dept_ID );
               
                int ret= ps.executeUpdate();
                
                if(ret>0)
                {
                    response.sendRedirect("ManagerDashboard.jsp");
                    //out.println("shd return true");
                    //return(true);
                }
                else
                {
                    out.print("in else");
                    //return(false);
                }}
            }
            else
            {
               out.print("not connected to db");
                //return(false);
            }
        }
        catch(SQLException e2)
        {
           out.print(e2);
          // return (false);
        } 
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
        processRequest(request, response);
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
        processRequest(request, response);
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
