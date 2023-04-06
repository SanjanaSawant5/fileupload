package org.fileupload.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.fileupload.Dao.FileUploadDao;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = null;
        InputStream inputStream = null;
        Part filePart = request.getPart("file");

        if (filePart != null) {
            fileName = filePart.getSubmittedFileName();
            inputStream = filePart.getInputStream();
        }

        Blob fileContent = null;
        try {
            if (inputStream != null) {
                fileContent = new SerialBlob(inputStream.readAllBytes());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FileUploadDao dao = new FileUploadDao();
        int rowsAffected = dao.uploadFile(fileName, fileContent);

        if (rowsAffected > 0) {
            response.getWriter().write("File uploaded successfully.");
        } else {
            response.getWriter().write("File upload failed.");
        }
    }
}
