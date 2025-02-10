<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Balance - ICICI Banking</title>
    <style>
        /* Resetting margins and padding */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body and general layout */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f8fb; /* Light blue background */
            color: #333;
            padding: 0 20px;
        }

        /* Navigation Bar Styling */
        nav {
            background-color: #007BFF; /* Blue background */
            padding: 15px 20px;
            text-align: center;
        }

        nav a {
            color: #fff; /* White text */
            text-decoration: none;
            margin: 0 20px;
            font-size: 16px;
            text-transform: uppercase;
            font-weight: bold;
        }

        nav a:hover {
            color: #FFD700; /* Gold color on hover */
        }

        /* Heading */
        h1 {
            color: #007BFF;
            text-align: center;
            margin-top: 40px;
            font-size: 36px;
        }

        /* Balance display */
        .balance {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            margin-top: 30px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .balance h1 {
            font-size: 48px;
            color: #28a745; /* Green color for balance */
        }

        .balance h4 {
            font-size: 20px;
            color: #555;
            margin-top: 10px;
        }

        /* Footer Section */
        footer {
            background-color: #007BFF; /* Blue background */
            color: #fff;
            text-align: center;
            padding: 20px;
            position: absolute;
            bottom: 0;
            width: 100%;
            font-size: 14px;
        }

    </style>
</head>
<body>

    <!-- Navigation Bar -->
    <nav>
        <a href="dashboard.html">Dashboard</a>
        <a href="login.html">Logout</a>
    </nav>

    <%
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            PreparedStatement statement = connection.prepareStatement("SELECT bal FROM bankingapp.profile WHERE usid = ?");

            // Get user session
            HttpSession session1 = request.getSession();
            statement.setString(1, (String) session.getAttribute("usid"));

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                // Retrieve balance
                int balance = Integer.parseInt(result.getString("bal"));
    %>

    <!-- Balance Display -->
    <div class="balance">
        <h1>Your balance is:</h1>
        <h1><%= balance %></h1>
        <h4>Thank you for banking with us!</h4>
    </div>

    <%
                System.out.println(balance); // Log balance (optional)
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 ICICI Bank. All rights reserved.</p>
    </footer>

</body>
</html>
