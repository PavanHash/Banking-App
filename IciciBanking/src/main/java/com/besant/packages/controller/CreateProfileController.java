package com.besant.packages.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.packages.service.impl.CreateProfileServiceImpl;
@WebServlet("/createProfile")
public class CreateProfileController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		CreateProfileServiceImpl service = new CreateProfileServiceImpl();
		service.createProfile(req, resp);
	}

}
