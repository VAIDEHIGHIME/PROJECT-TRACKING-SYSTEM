
package SystemControllers;

import DAO.DepartmentDao;
import DAO.DepartmentDao;
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

public class DepartmentController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       response.setContentType("text/html;charset=UTF-8");
       //System.out.print("in this departent file");
        try (PrintWriter out = response.getWriter())
        {
        
        
       
            
            String Dept_Name = request.getParameter("Dept_Name");
            out.print(Dept_Name);
            DepartmentDao dao=new DepartmentDao();
            String Dept_ID=dao.check(Dept_Name);
            try
            {
                
                if("false".equals(Dept_ID))
                {
                    System.out.print("in else");
                    response.sendRedirect("error.jsp");
                    
                }
                else
                {
                    System.out.print("returned true");
                    HttpSession session=request.getSession();
                    session.setAttribute("Dept_ID",Dept_ID);
                    response.sendRedirect("department.jsp");
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
            Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    { 
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "departmentController Servlet";
    }
    
}