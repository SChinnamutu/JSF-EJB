package com.ejb.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ejb.service.impl.UserService;

@ManagedBean(name = "user")
@SessionScoped
public class UserController {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dbPassword;
    private String dbName;
  
    private Connection connect = null;

    @EJB
    private UserService userService;
    
    public UserController()   {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");
			System.out.println("Connection created successfully");
		} catch (ClassNotFoundException ex) {
			System.out.println("Exception occured while creating connection and exception is " + ex.getMessage());
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("Exception occured while creating connection and exception is " + ex.getMessage());
			System.out.println(ex.getMessage());
		}
    }
   
    public String add(){
    	UserController user = new UserController();
    	user.setEmail(email);
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	user.setPassword(dbPassword);
    	return userService.add(user,connect);
    }
    
    public String login() {
        if(userService.dbData(firstName,password,connect)){
        	return "output";
        } else
        	return "invalid";
      }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
    }
    

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

