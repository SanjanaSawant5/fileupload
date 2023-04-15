package com.parkingportalmain.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileUploadDao {

    private static final String URL = "jdbc:mysql://localhost:3306/parkingportal";
    private static final String USER = "root";
    private static final String PASSWORD = "Intern@1234";

    private static final String sql = "INSERT INTO 'test' (username, registrationv1doc, insurancev1doc, registrationv2doc, insurancev2doc) values (?, ?, ?, ?);";

    public int uploadFile(String username, InputStream registrationv1doc, InputStream insurancev1doc,InputStream registrationv2doc, InputStream insurancev2doc) {
        int row = 0;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
        }
        		
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
        	 
            if (registrationv1doc != null) {
            preparedStatement.setBlob(1, registrationv1doc);
            }if(insurancev1doc != null) {
            preparedStatement.setBlob(2, insurancev1doc);
            }if(registrationv2doc != null) {
            preparedStatement.setBlob(3, registrationv2doc);
            }if(insurancev2doc != null) {
            preparedStatement.setBlob(4, insurancev2doc);
            }

            row = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return row;
    }

    private void printSQLException(SQLException ex) {
        ex.printStackTrace(System.err);
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("Message: " + ex.getMessage());
        Throwable t = ex.getCause();

        while (t != null) {
            System.out.println("Cause: " + t);
            t = t.getCause();
        }
    }
}