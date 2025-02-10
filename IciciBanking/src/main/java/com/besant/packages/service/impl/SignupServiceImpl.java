package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.packages.services.SignupService;

public class SignupServiceImpl implements SignupService {

	@Override
	public void signUp(HttpServletRequest req, HttpServletResponse res) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			PreparedStatement statement = connection.prepareStatement(
					"insert into BankingApp.authentication (email,password, accountNumber) values(?,?,'123456789')");

			statement.setString(1, req.getParameter("email"));
			statement.setString(2, req.getParameter("password"));

			int response = statement.executeUpdate();

			System.out.println(response);
			if (response > 0) {
				res.sendRedirect("login.html");
			} else {
				System.err.println("something went wrong");
			}

			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
