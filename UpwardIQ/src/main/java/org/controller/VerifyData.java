package org.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.model.ProductDetails;
@WebServlet("/verify")
@MultipartConfig
public class VerifyData extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Part file = request.getPart("xl");
	        if (file == null || file.getSize() == 0) {
	            response.getWriter().println("No file uploaded");
	            return;
	        }
	        InputStream inputStream=file.getInputStream();
	        Workbook workbook= WorkbookFactory.create(inputStream);
	        Sheet sheet=workbook.getSheetAt(0);
	        PrintWriter writer=response.getWriter();
	        response.setContentType("text/html");
	        HttpSession session=request.getSession();
	        session.setAttribute("file", file);
	        RequestDispatcher dispatcher=request.getRequestDispatcher("UploadProduct.jsp");
	        dispatcher.include(request, response);
        	writer.println("<center style='position: relative; top: -20vw;'> <table style='border: 1px solid black; border-collapse: collapse;'>"
        			+ "        <tr>"
        			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Id</th>"
        			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Name</th>"
        			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Type</th>"
        			+ "            <th style='border: 1px solid black; border-collapse: collapse;'>Product_Price</th>"
        			+ "        </tr>");
        		for(Row r:sheet) {
        			
        			if(r.getRowNum()==0)continue;
        		writer.println(	 "      <tr>"
        			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+r.getCell(0).getNumericCellValue()+"</td>"
        			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+r.getCell(1).getStringCellValue()+"</td>"
        			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+r.getCell(2).getStringCellValue()+"</td>"
        			+ "            <td style='border: 1px solid black; border-collapse: collapse;'>"+r.getCell(3).getStringCellValue()+"</td>"
        			+ "        </tr>"
        			);
        		}
        		writer.println("</table>"
        				+ "<form action='upload' method='post'>"
        				+ "<input type='submit' value='save' ></form>"
        				+ "<form action='UploadProduct.jsp'><input type='submit' value='cancel'></form>"
        				+ "</center>");
	}
}
