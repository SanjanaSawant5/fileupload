package org.fileupload.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "UploadHandlerServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class UploadHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        if (username != null) { 
            String filename = "";
            InputStream inputStream = null;
            try {
                // Get the uploaded file
                Part filePart = request.getPart("file");
                if (filePart != null && filePart.getSize() > 0) {
                    filename = filePart.getSubmittedFileName();
                    inputStream = filePart.getInputStream();
                } else {
                    throw new Exception("No file uploaded");
                }
            } catch (Exception e) {
                response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
                return;
            }

            // Save the file in the database
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Intern@1234");
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO doc (username, filename, content) VALUES (?, ?, ?)");
                pstmt.setString(1, username);
                pstmt.setString(2, filename);
                pstmt.setBinaryStream(3, inputStream);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.getWriter().println("<p>File uploaded successfully</p>");
        } else {
            response.getWriter().println("<p>You need to log in first</p>");
        }
    }
}
