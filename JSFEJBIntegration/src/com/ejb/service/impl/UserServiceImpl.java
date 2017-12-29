package com.ejb.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;

import com.ejb.controller.UserController;

@Stateless
public class UserServiceImpl implements UserService {

	public boolean dbData(String uName,String pswd,Connection con) {
    	String dbName = "";
    	String dbPassword = "";
    	if (uName != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            if (con != null) {
                try {
                    if (con != null) {
                        String sql = "select firstname,password from user_login where firstname = '" + uName + "'";
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                        rs.next();
                        dbName = rs.getString("firstname");
                        dbPassword = rs.getString("password");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
                if(dbName.equalsIgnoreCase(uName) && dbPassword.equalsIgnoreCase(pswd)){
                	return true;
                }
            }
        }
    	return false;
    }
    
    public String add(UserController user, Connection con) {
        int i = 0;
        if (user.getFirstName() != null) {
            PreparedStatement ps = null;
            try {
                if (con != null) {
                    if (con != null) {
                        String sql = "INSERT INTO user_login(firstname, password, lastname, email) VALUES(?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, user.getFirstName());
                        ps.setString(2, user.getPassword());
                        ps.setString(3, user.getLastName());
                        ps.setString(4, user.getLastName());
                        i = ps.executeUpdate();
                        System.out.println("Data Added Successfully");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i > 0) {
            return "success";
        } else
            return "unsuccess";
    }
    
}
