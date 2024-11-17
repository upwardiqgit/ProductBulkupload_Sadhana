package org.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.DAO.UpwardDAO;
import org.DAO.UpwardDAOimpl;
import org.apache.poi.ss.usermodel.*;
import org.model.ProductDetails;

import com.mysql.cj.Session;

@WebServlet("/upload")
@MultipartConfig
public class UploadFile extends HttpServlet {

    Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/techa62?user=root&password=root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session=request.getSession();
        Part file = (Part) session.getAttribute("file");
        if (file == null || file.getSize() == 0) {
            response.getWriter().println("No file uploaded");
            return;
        }
        

        String insert = "INSERT INTO product_details(Product_ID, Product_Name, Product_Type, Product_Price) VALUES (?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);

            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            PreparedStatement preparedStatement = connection.prepareStatement(insert);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String type = row.getCell(2).getStringCellValue();
                String price = row.getCell(3).getStringCellValue();

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, type);
                preparedStatement.setString(4, price);
                preparedStatement.addBatch();
            }
            PrintWriter writer=response.getWriter();
            response.setContentType("text/html");
            int[] results = preparedStatement.executeBatch();
            connection.commit();
            //response.getWriter().println("File uploaded and " + results.length + " records inserted!");
            if(results.length>0) {
            	UpwardDAO dao=new UpwardDAOimpl();
            	List<ProductDetails> list=dao.getAllDetails();
            	RequestDispatcher dispatcher=request.getRequestDispatcher("UploadProduct.jsp");
            	dispatcher.include(request, response);
            	writer.println("<center style='position: relative; top: -20vw;'> <table style='border: 1px solid black; border-collapse: collapse;'>"
            			+ "        <tr>"
            			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Id</th>"
            			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Name</th>"
            			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Type</th>"
            			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Price</th>"
            			+ "        </tr>");
            		for(ProductDetails p:list) {
            		writer.println(	 "      <tr>"
            			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+p.getProductid()+"</td>"
            			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+p.getProductname()+"</td>"
            			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+p.getProductype()+"</td>"
            			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+p.getProductprice()+"</td>"
            			+ "        </tr>"
            			);
            		}
            		writer.println("</table>"
            				+ "<h1 style='color:green'>Data Uploaded to DB</h1></center>");
            		
            
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
