
package SystemControllers;

import DAO.ModifyETicketDao;
import DAO.ModifyETicketDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyETicketController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       response.setContentType("text/html;charset=UTF-8");
       System.out.print("in this goddamn file");
        try (PrintWriter out = response.getWriter())
        {
        
            String E_ID = request.getParameter("E_ID");
            String Explanation = request.getParameter("Explanation");
            
            ModifyETicketDao dao=new ModifyETicketDao();
            
            try
            {
                
                if(dao.check(E_ID,Explanation))
                {
                    System.out.print("returned true");
                    response.sendRedirect("e_ticket_dev_view.jsp");
                }
                else
                {
                    System.out.print("in else");
                    response.sendRedirect("error.jsp");
                    
                }  
            }
            catch(IOException ex)
            {
                out.println("Exception:"+ex);
                
            }
            
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "AddEmployeeController Servlet";
    }
    
}