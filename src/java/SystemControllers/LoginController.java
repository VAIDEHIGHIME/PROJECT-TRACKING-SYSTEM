
package SystemControllers;




import DAO.DBAO;
import DAO.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet
{   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            
            String EmpID=request.getParameter("EmpID");
            String password=request.getParameter("password");
            HttpSession session= request.getSession();
            session.setAttribute("empID", EmpID);
            
            //Check for Admin
            if("Admin".equals(EmpID) && "Admin".equals(password))
            {
                response.sendRedirect("AdminDashboard.jsp");
            }
            else
            {
                
                 LoginDao login = new LoginDao();
                 String role = login.check(EmpID, password);

                  out.print(role);

                if(role != null)
                {
                    
                  
                    
                     if("Manager".equals(role))
                     {
                                              
                         response.sendRedirect("ManagerDashboard.jsp");

                     }
                     if("Tester".equals(role))
                     {
                         
                        response.sendRedirect("TesterDashboard.jsp");

                     }
                     if("Developer".equals(role))
                     {
                         
                        response.sendRedirect("DeveloperDashboard.jsp");

                     }

                } 
                else
                {
                    response.sendRedirect("index.html");
                    
                }
                                                                        
            }
                     
        }
                         
               
            
            
            
            
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "Login Servlet";
    }
}