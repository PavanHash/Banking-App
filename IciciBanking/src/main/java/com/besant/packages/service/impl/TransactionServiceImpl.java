package com.besant.packages.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.besant.packages.services.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public void addMoney(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			PreparedStatement statement = connection
					.prepareStatement("select bal from bankingapp.profile where usid= ?");

			HttpSession session = req.getSession();
			if (session.getAttribute("usid") == null) {
				res.sendRedirect("login.html");
			} else {

				statement.setString(1, (String) session.getAttribute("usid"));

				ResultSet result = statement.executeQuery();

				if (result.next()) {
					PreparedStatement statement2 = connection
							.prepareStatement("update bankingapp.profile set bal = ? where usid =?");

					int newBalance = Integer.parseInt(result.getString("bal"))
							+ Integer.parseInt(req.getParameter("depositMoney"));
					statement2.setInt(1, newBalance);
					statement2.setString(2, (String) session.getAttribute("usid"));

					int result2 = statement2.executeUpdate();
					if (result2 > 0) {
						res.sendRedirect("viewBal.jsp");
					} else {
						System.err.println("Something went wrong.... try again later");
					}
					System.out.println();
				}

				connection.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void withdrawMoney(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			PreparedStatement statement = connection
					.prepareStatement("select bal from bankingapp.profile where usid= ?");

			HttpSession session = req.getSession();
			if (session.getAttribute("usid") == null) {
				res.sendRedirect("login.html");
			} else {

				statement.setString(1, (String) session.getAttribute("usid"));

				ResultSet result = statement.executeQuery();

				if (result.next()) {
					PreparedStatement statement2 = connection
							.prepareStatement("update bankingapp.profile set bal = ? where usid =?");

					int newBalance = Integer.parseInt(result.getString("bal"))
							- Integer.parseInt(req.getParameter("depositMoney"));
					if (newBalance < 0) {
						System.err.println("insufficient balance");
					} else {
						statement2.setInt(1, newBalance);
						statement2.setString(2, (String) session.getAttribute("usid"));

						int result2 = statement2.executeUpdate();
						if (result2 > 0) {
							res.sendRedirect("viewBal.jsp");
						} else {
							System.err.println("Something went wrong.... try again later");
						}
					}
				}

				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewBalance(HttpServletRequest req, HttpServletResponse res) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			PreparedStatement statement = connection
					.prepareStatement("select bal from bankingapp.profile where usid= ?");

			HttpSession session = req.getSession();
			statement.setString(1, (String) session.getAttribute("usid"));

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				int balance = Integer.parseInt(result.getString("bal"));
				System.out.println(balance);

			}

			connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
