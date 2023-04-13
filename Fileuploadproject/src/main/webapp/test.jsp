<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Handler</title>
</head>
<body>
    <h1>Upload Handler</h1>
    <%
    String username = (String) session.getAttribute("username");
    if (username != null) {
    %>
        <form method="post" action="upload" enctype="multipart/form-data">
            <p>Choose a file to upload:</p>
            <input type="file" name="file"><br><br>
            <input type="submit" value="Upload">
        </form>
    <%
    } else {
    %>
        <p>You need to log in first</p>
    <%
    }
    %>
</body>
</html>
