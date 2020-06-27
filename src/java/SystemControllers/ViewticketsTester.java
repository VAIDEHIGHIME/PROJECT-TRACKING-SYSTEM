/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemControllers;

import DAO.ChooseprojectDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author h p
 */
public class ViewticketsTester extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.net.MalformedURLException
     * @throws java.net.URISyntaxException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MalformedURLException, URISyntaxException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int eid = Integer.parseInt(request.getParameter("E_ID")); 
           // int projID= Integer.parseInt(request.getParameter("Proj_ID"));
            String projName=request.getParameter("Proj_Name");
             String Dev_ID=request.getParameter("Dev_ID");

            String update=request.getParameter("update");
             String create=request.getParameter("create");
            String openprj=request.getParameter("open");//out.print(openprj);
            String openfile=request.getParameter("openFile");
            //System.out.print(projID);
            ChooseprojectDao openproj = new ChooseprojectDao();
            
            if(openprj!=null && update==null && create ==null)
            {
                String link = openproj.GetLink(eid);
                //openproj.openProjectLink(link);
                
                
                Runtime rn=Runtime.getRuntime();
                try{
                rn.exec("rundll32 url.dll, FileProtocolHandler "+link);
                }
                catch(IOException e){}
                response.sendRedirect("ViewticketsTester.jsp");
                    
                    
               //&&& response.sendRedirect(update_ticket_tester.jsp);
            }
            
            if(update!= null && openprj == null && create==null)
            {
               // String Eticket = openproj.getEticket(projID);
                HttpSession se= request.getSession();
                se.setAttribute("E_ID", eid);
                
                response.sendRedirect("update_ticket_tester.jsp");
                
            }
            if(create!=null && update == null && openprj == null)
            {
               // String Eticket = openproj.getEticket(projID);
                HttpSession se= request.getSession();
                se.setAttribute("E_ID", eid);//out.print("bll "+eid);
               // se.setAttribute("Dev_ID", Dev_ID);
               response.sendRedirect("FillETicketTester.jsp");
                
            }
           
           if(openfile !=null)
           { String link = openproj.GetFile(eid);
               Runtime rn=Runtime.getRuntime();
                try{
                rn.exec("rundll32 url.dll, FileProtocolHandler "+link);
                }
                catch(IOException e){}
                response.sendRedirect("ViewticketsTester.jsp");
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
            throws ServletException, IOException, MalformedURLException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ViewticketsTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewticketsTester.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException, MalformedURLException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ViewticketsTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewticketsTester.class.getName()).log(Level.SEVERE, null, ex);
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
