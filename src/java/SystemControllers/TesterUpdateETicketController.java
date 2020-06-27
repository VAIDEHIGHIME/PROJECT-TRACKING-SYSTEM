
package SystemControllers;

import DAO.UpdateETicketDao;
import java.io.IOException;
//import java.io.PrintWrieter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TesterUpdateETicketController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       response.setContentType("text/html;charset=UTF-8");
       System.out.print("in this goddamn file");
        try (PrintWriter out = response.getWriter())
        {
        
        
       
            
            String Is_Solved = request.getParameter("Is_Solved");
            String recheck = "TRUE";
            if("TRUE".equals(Is_Solved))
            {
                recheck = "FALSE";
            }
            int E_ID = Integer.parseInt(request.getParameter("E_ID"));
             String EM = request.getParameter("Error_Message");
              String file= request.getParameter("file");
            String comments= request.getParameter("Comments");
            
            UpdateETicketDao dao=new UpdateETicketDao();
            
            try
            {
                
                if(dao.checkTester(E_ID,Is_Solved,recheck,EM,comments,file ))
                {
                    System.out.print("returned true");
                    response.sendRedirect("ViewticketsTester.jsp");
                    
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
        return "TesterUpdateETicketController Servlet";
    }
    
}