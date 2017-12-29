package com.ejb.service.impl;

import java.sql.Connection;

import javax.ejb.Local;

import com.ejb.controller.UserController;


@Local
public interface UserService {
	  public boolean dbData(String uName,String pswd,Connection con);
	  public String add(UserController user,Connection con);
}
