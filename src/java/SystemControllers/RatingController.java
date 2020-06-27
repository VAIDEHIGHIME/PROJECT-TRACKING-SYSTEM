
package SystemControllers;

import DAO.RatingDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RatingController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       response.setContentType("text/html;charset=UTF-8");
       
        try (PrintWriter out = response.getWriter())
        {
            String star = request.getParameter("star");
           
            String Emp_ID = request.getParameter("Emp_ID");
            RatingDao dao=new RatingDao();
            try
            {
                
                if(dao.check(Emp_ID, star))
                {
                    //System.out.print("returned true");
                    response.sendRedirect("employee.jsp");
                }
                else
                {
                    //System.out.print("in else");
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
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "RatingController Servlet";
    }
    
}