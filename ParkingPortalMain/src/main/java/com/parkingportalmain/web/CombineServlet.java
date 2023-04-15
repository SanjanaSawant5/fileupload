package com.parkingportalmain.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.parkingportalmain.bean.User;
import com.parkingportalmain.conprov.ConProv;
import com.parkingportalmain.dao.UserDao;

@WebServlet(name = "CombineServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class CombineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if ("login".equals(action)) {
          
            String password = request.getParameter("password");
            try {
                Connection connection = ConProv.getConn();
                UserDao dao = new UserDao(connection);
                User user = dao.callStoredProcedure(username, password);
                if(user != null) {
                    session.setAttribute("user", user);
                    session.setAttribute("username", username);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("NewFile.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("Error.jsp");
                }
            } catch(Exception e) {
                e.printStackTrace();
                response.sendRedirect("Error.jsp");
            }
        } else if ("upload".equals(action)) {
        	 if (username != null) { 
        		 String reg1File = "";
                 String ins1File = "";
                 String reg2File = "";
                 String ins2File = "";
                 InputStream reg1InputStream = null;
                 InputStream ins1InputStream = null;
                 InputStream reg2InputStream = null;
                 InputStream ins2InputStream = null;
                 try {
                     // Get the uploaded file
                     Part reg1FilePart = request.getPart("reg1File");
                     Part ins1FilePart = request.getPart("ins1File");
                     Part reg2FilePart = request.getPart("reg2File");
                     Part ins2FilePart = request.getPart("ins2File");
                      
                     if (reg1FilePart != null && reg1FilePart.getSize() > 0) {
                         reg1File = reg1FilePart.getSubmittedFileName();
                         reg1InputStream = reg1FilePart.getInputStream();
                     } else {
                     	if (ins1FilePart != null && ins1FilePart.getSize() > 0) {
                             ins1File = ins1FilePart.getSubmittedFileName();
                             ins1InputStream = ins1FilePart.getInputStream();
                         } else {
                         	if (reg2FilePart != null && reg2FilePart.getSize() > 0) {
                                 reg2File = reg2FilePart.getSubmittedFileName();
                                 reg2InputStream = reg2FilePart.getInputStream();
                             } else {
                                 if (ins2FilePart != null && ins2FilePart.getSize() > 0) {
                                	 ins2File = ins2FilePart.getSubmittedFileName();
                                	 ins2InputStream = ins2FilePart.getInputStream();
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
                     pstmt.setString(2, reg1File);
                     pstmt.setBinaryStream(3, reg1InputStream);
                     pstmt.setString(4, ins1File);
                     pstmt.setBinaryStream(5, ins1InputStream);
                     pstmt.setString(6, reg2File);
                     pstmt.setBinaryStream(7, reg2InputStream);
                     pstmt.setString(8, ins2File);
                     pstmt.setBinaryStream(9, ins2InputStream);
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
}