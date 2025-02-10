package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.packages.services.CreateProfileService;

public class CreateProfileServiceImpl implements CreateProfileService {

	@Override
	public void createProfile(HttpServletRequest req, HttpServletResponse res) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingApp", "root",
					"root");
			PreparedStatement statement = connection.prepareStatement(
					"insert into BankingApp.profile (name , bal, pan,address,usid) values (?,0,?,?,?);");

			statement.setString(1, req.getParameter("name"));
			statement.setString(2, req.getParameter("pan"));
			statement.setString(3, req.getParameter("address"));
			HttpSession session = req.getSession();
			if (session.getAttribute("usid") == null) {
				res.sendRedirect("login.html");
			} else {

				statement.setString(4, (String) session.getAttribute("usid"));
				int response = statement.executeUpdate();

				System.out.println(response);
				if (response > 0) {
					res.sendRedirect("dashboard.html");
				} else {
					System.err.println("something went wrong");
				}

				connection.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
