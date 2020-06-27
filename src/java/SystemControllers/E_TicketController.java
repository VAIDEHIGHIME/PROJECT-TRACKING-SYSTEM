
package SystemControllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class E_TicketController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       response.setContentType("text/html;charset=UTF-8");
       //System.out.print("in this goddamn file");
        try (PrintWriter out = response.getWriter())
        {
        
        
       
            
             String E_ID = request.getParameter("E_ID");
            Integer diffDays = Integer.parseInt(request.getParameter("diffDays"));
            //Integer diffDays=3;
            System.out.print(E_ID);
            
            
            HttpSession session=request.getSession();
            session.setAttribute("E_ID",E_ID);
            if(diffDays>0)
            {
                String Proj_ID = request.getParameter("Proj_ID");
                String Dept_ID = request.getParameter("Dept_ID");
                out.print(Dept_ID);
                session.setAttribute("Proj_ID",Proj_ID);
                session.setAttribute("Dept_ID",Dept_ID);               
            }
            response.sendRedirect("e_ticket_display.jsp");
            
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(E_TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(E_TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "E_TicketController Servlet";
    }
    
}