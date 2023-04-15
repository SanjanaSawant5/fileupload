<%@page import="org.uploaddoc.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Files</title>
</head>
<body>
    <h1>Upload Files</h1>   
    <form method="post" action="fileupload" enctype="multipart/form-data">
        <p>
            <label for="reg1File">Registration Document 1:</label>
            <input type="file" name="reg1File" id="reg1File">
        </p>
        <p>
            <label for="ins1File">Insurance Document 1:</label>
            <input type="file" name="ins1File" id="ins1File">
        </p>
        <p>
            <label for="reg2File">Registration Document 2:</label>
            <input type="file" name="reg2File" id="reg2File">
        </p>
        <p>
            <label for="ins2File">Insurance Document 2:</label>
            <input type="file" name="ins2File" id="ins2File">
        </p>
        <p>
            <input type="submit" value="Upload">
        </p> 
    </form>
</body>
</html>

