/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;


public class Emp {
    protected String empID;
    protected String name;
    protected String email;
    protected String role;
    protected String yoe;
    protected String skills;
    protected String password;
    protected String cpassword;

    public Emp() {}

    public Emp(String empID, String name, String email, String role, String yoe, String skills, String password ,String cpassword) {
        this.empID = empID;
        this.name = name;
        this.email = email;
        this.role = role;
        this.yoe = yoe;
        this.skills = skills;
        this.password = password;
        this.cpassword=cpassword;
    }

    Emp(String name, String pass, String role, String email, String YOE, String skills) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getYoe() {
        return yoe;
    }

    public void setYoe(String yoe) {
        this.yoe = yoe;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  

    
}