<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.io.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Handler</title>
</head>
<body>
    <h1>Upload Handler</h1>
    <% 
    String username = (String)session.getAttribute("username");

    if (username != null) { 
        String filename = "";
        InputStream inputStream = null;
        try {
            // Get the uploaded file
            Part filePart = request.getPart("file");
            filename = filePart.getSubmittedFileName();
            inputStream = filePart.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Save the file in the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingportal", "root", "Intern@1234");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO test (username, filename, content) VALUES (?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, filename);
            pstmt.setBinaryStream(3, inputStream);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("<p>File uploaded successfully</p>");
    } else { %>
        <p>You need to log in first</p>
    <% } %>
</body>
</html>
