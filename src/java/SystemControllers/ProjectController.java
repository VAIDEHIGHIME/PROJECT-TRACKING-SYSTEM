
package SystemControllers;

import DAO.ProjectDao;
import DAO.ProjectDao;
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

public class ProjectController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       response.setContentType("text/html;charset=UTF-8");
       //System.out.print("in this goddamn file");
        try (PrintWriter out = response.getWriter())
        {
        
        
       
            
            String Proj_Name = request.getParameter("Proj_Name");
            
            System.out.print(Proj_Name);
            ProjectDao dao=new ProjectDao();
            String Proj_ID=dao.check(Proj_Name);
            
            try
            {
                
                if(Proj_ID!="false")
                {
                    System.out.print("returned true");
                    HttpSession session=request.getSession();
                    session.setAttribute("Proj_ID",Proj_ID);
                    response.sendRedirect("project.jsp");
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
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "ProjectController Servlet";
    }
    
}