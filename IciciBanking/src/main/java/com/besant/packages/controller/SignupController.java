package com.besant.packages.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.packages.service.impl.SignupServiceImpl;


@WebServlet("/signup")
public class SignupController extends HttpServlet {

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
		SignupServiceImpl signupServiceImpl =  new SignupServiceImpl();
		signupServiceImpl.signUp(req, resp);
	}
}
