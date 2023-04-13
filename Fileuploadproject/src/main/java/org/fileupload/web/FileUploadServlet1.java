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

@WebServlet(name = "FileUploadServlet1", urlPatterns = {"/fileupload"})
@MultipartConfig
public class FileUploadServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        if (username != null) { 
            String filename = "";
            InputStream inputStream = null;
            try {
                // Get the uploaded file
                Part reg1FilePart = request.getPart("reg1File");
                Part ins1FilePart = request.getPart("ins1File");
                Part reg2FilePart = request.getPart("reg2File");
                Part ins2FilePart = request.getPart("ins2File");
                 
                if (reg1FilePart != null && reg1FilePart.getSize() > 0) {
                    filename = reg1FilePart.getSubmittedFileName();
                    inputStream = reg1FilePart.getInputStream();
                } else {
                	if (ins1FilePart != null && ins1FilePart.getSize() > 0) {
                        filename = ins1FilePart.getSubmittedFileName();
                        inputStream = ins1FilePart.getInputStream();
                    } else {
                    	if (reg2FilePart != null && reg2FilePart.getSize() > 0) {
                            filename = reg2FilePart.getSubmittedFileName();
                            inputStream = reg2FilePart.getInputStream();
                        } else {
                            if (ins2FilePart != null && ins2FilePart.getSize() > 0) {
                                filename = ins2FilePart.getSubmittedFileName();
                                inputStream = ins2FilePart.getInputStream();
                            } else {
                                throw new Exception("No file uploaded");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
                return;
            }

            // Save the file in the database
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Intern@1234");
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO doc2 (username, reg1File, contentreg1, ins1File, contentins1, reg2File, contentins2, ins2File, contentins2) VALUES (?, ?, ?, ?, ?, ? ,? , ?, ?)");
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
