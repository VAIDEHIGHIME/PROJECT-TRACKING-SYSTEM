
package SystemControllers;




import DAO.AdminOperations;
import DAO.RegisterDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet
{  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
       
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String cpassword=request.getParameter("cpassword");
            String role=request.getParameter("role");
            String email=request.getParameter("email");
            String yoe=request.getParameter("yoe");
            String skill=request.getParameter("ss");
          
            RegisterDao dao=new RegisterDao();
            AdminOperations ao = new AdminOperations();
            
           
            
            
            
            try
            { 
                if(dao.addUser(name,password,cpassword,role,email,yoe,skill)==true)
                {
                      ao.addRequest(name, password, role, email, yoe, skill);
                      //out.println ("<html><body><script>alert('registered successfully! Now wait for the confirmation from the admin ');</script></body></html>");
                      response.sendRedirect("Login.jsp");
                 
                    
                }
                else
                {  
                   // out.println ("<html><body><script>alert('Registration Failed!');</script></body></html>");
                    response.sendRedirect("index.html");
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
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public String getServletInfo()
    {
        return "Register Servlet";
    }
}