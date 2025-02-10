package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.packages.services.LoginService;

public class LoginServiceImpl implements LoginService {

	@Override
	public void login(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingApp", "root",
					"root");
			PreparedStatement statement = connection
					.prepareStatement("select * from BankingApp.authentication where email= ? and  password =?");

			statement.setString(1, req.getParameter("email"));
			statement.setString(2, req.getParameter("password"));

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("usid", result.getString("id"));
				res.sendRedirect("dashboard.html");
				System.out.println("logged  successfully");
			} else {
				System.err.println("password or email incorrect");
			}

			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
