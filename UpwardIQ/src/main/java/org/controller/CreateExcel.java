package org.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.model.ProductDetails;

import com.mysql.cj.Session;

@WebServlet("/createExcel")
public class CreateExcel extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		List<ProductDetails> list=(List<ProductDetails>) session.getAttribute("filteredList");
		if(list!=null) {
			Workbook workbook=new XSSFWorkbook();
			Sheet sheet=workbook.createSheet("ProductDetails");
			Row headerRow=sheet.createRow(0);
			String [] headers= {"Product_ID", "Product_Name", "Product_Type", "Product_Price"};
			for(int i=0;i<headers.length;i++) {
				Cell cell=headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(createHeaderStyle(workbook));
			}
			
			int rowNum=1;
			for(ProductDetails p:list) {
				
				Row row=sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(p.getProductid());
				row.createCell(1).setCellValue(p.getProductname());
				row.createCell(2).setCellValue(p.getProductype());
				row.createCell(3).setCellValue(p.getProductprice());
			}
			for(int i=0;i<headers.length;i++) {
				sheet.autoSizeColumn(i);
			}
			
			try(FileOutputStream fileOutputStream=new FileOutputStream("C:\\advanced_java\\FinalProject\\UpwardIQ\\xlFiles\\Products.xlsx")){
				workbook.write(fileOutputStream);
				response.setContentType("text/html");
				RequestDispatcher dispatcher=request.getRequestDispatcher("SearchProduct.jsp");
				dispatcher.include(request, response);
				response.getWriter().println("<center style='position:relative; top:-10vw;' ><h1 style='color:green;'>Excel Created</h1></center>");
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
				

		}else {
			response.getWriter().println("<center><h1>Unable to process Excel</h1></center>");
		}
		
	}
	
	 private static CellStyle createHeaderStyle(Workbook workbook) {
	        CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setBold(true);
	        style.setFont(font);
	        return style;
	    }
	
}
