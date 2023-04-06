package org.fileupload.Dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileUploadDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/parkingportal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Intern@1234";

    private static final String INSERT_SQL = "INSERT INTO adamscitylights (name, content) VALUES (?, ?)";

    public int uploadFile(String name, Blob content) {
        int rowsAffected = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, name);
            stmt.setBlob(2, content);
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}