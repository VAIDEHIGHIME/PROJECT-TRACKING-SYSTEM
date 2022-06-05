/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;


import DAO.DBAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class EmpDao {
    
    DBAO dbao= new DBAO();
   

    private static final String INSERT_USERS_SQL = "INSERT INTO `Registrationdetails` (`Name`, `Password`,`cPassword`, `Role`, `Email`, `YearsOfExp`, `Skills`) VALUES (?,?,?,?,?,?,?)";

  //  private static final String SELECT_USER_BY_ID = "select * from Registrationdetails where E =?";
    private static final String SELECT_ALL_USERS = "select * from `queuedforapproval`";
    private static final String DELETE_USERS_SQL = "delete from queuedforapproval where Email = ?;";
  //  private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
    
    

    public EmpDao() {}


    public void insertEmp(Emp emp) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = dbao.connectToDataBase(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getPassword());
            preparedStatement.setString(3, emp.getCpassword());
            preparedStatement.setString(4, emp.getRole());
            preparedStatement.setString(5, emp.getEmail());
            preparedStatement.setString(6, emp.getYoe());
            preparedStatement.setString(7, emp.getSkills());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

//    public User selectUser(int id) {
//        User user = null;
//        // Step 1: Establishing a Connection
//        try (Connection connection = getConnection();
//            // Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
//            preparedStatement.setInt(1, id);
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id, name, email, country);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return user;
//    }

    public List < Emp > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Emp > EmpList = new ArrayList < > ();
        System.out.println("hellllllllllllloooooooooooo");
        // Step 1: Establishing a Connection
        try (Connection connection = dbao.connectToDataBase();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("Name");
                String pass = rs.getString("Password");
                String role = rs.getString("Role");
                String email = rs.getString("Email");
                String YOE = rs.getString("YearsOfExp");
                String skills = rs.getString("Skills");
                EmpList.add(new Emp(name, pass, role, email, YOE, skills));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return EmpList;
    }

    public boolean deleteUser(String emailID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = dbao.connectToDataBase();
        PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setString(1, emailID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

//    public boolean updateUser(Emp emp) throws SQLException {
//        boolean rowUpdated;
//        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
//            statement.setString(1, user.getName());
//            statement.setString(2, user.getEmail());
//            statement.setString(3, user.getCountry());
//            statement.setInt(4, user.getId());
//
//            rowUpdated = statement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

