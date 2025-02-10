<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Balance</title>
</head>
<body>
<nav>
		<a href="dashboard.html">Dashboard</a> <a href="login.html">logout</a>

	</nav>

    <h1>Your Account Balance</h1>
    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            PreparedStatement statement = connection.prepareStatement("SELECT bal FROM bankingapp.profile WHERE usid = ?");
            
            HttpSession session1 = request.getSession();
            String userId = (String) session1.getAttribute("usid");
            
            if (userId != null) {
                statement.setString(1, userId);
                ResultSet result = statement.executeQuery();
                
                if (result.next()) {
                    int balance = result.getInt("bal");
    %>
                    <p>Your balance is: <strong><%= balance %></strong></p>
    <%
                } else {
    %>
                    <p>No balance information found for your account.</p>
    <%
                }
                result.close();
            } else {
    %>
                <p>User session not found. Please log in.</p>
    <%
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            out.println("<p>Error retrieving balance: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    %>
</body>
</html>
