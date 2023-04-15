<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authentication Page</title>
</head>
<body>
    <h1>Authentication Page</h1>
    <% 
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    boolean authenticated = false;

    // Check if username and password are valid, e.g. by querying a database
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Intern@1234");
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM logincredential WHERE username = ? AND password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            authenticated = true;
        }
        rs.close();
        pstmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    if (authenticated) { 
        session.setAttribute("username", username);
        response.sendRedirect("usertest.jsp");
    } else { %>
        <p>Invalid username or password</p>
    <% } %>
</body>
</html>
