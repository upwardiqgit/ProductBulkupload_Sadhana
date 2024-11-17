package org.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.model.ProductDetails;

import com.mysql.cj.result.Row;
import com.mysql.cj.xdevapi.PreparableStatement;

public class UpwardDAOimpl implements UpwardDAO {

	Connection connection;
	private final String url="jdbc:mysql://localhost:3306/techa62?user=root&password=root";
	@Override
	public List<ProductDetails> getAllDetails() {
		String select="select * from product_details";
		List<ProductDetails> listOfProds=new ArrayList<ProductDetails>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(select);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ProductDetails details=new  ProductDetails();
					details.setProductid(rs.getInt("Product_ID"));
					details.setProductname(rs.getString("Product_Name"));
					details.setProductype(rs.getString("Product_Type"));
					details.setProductprice(rs.getString("Product_Price"));
					listOfProds.add(details);
				}
				return listOfProds;
			}else return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<ProductDetails> getDetailsBasedOnTypeAndName(String name, String type) {
		String select="select * from product_details where Product_Name=? and Product_Type=?";
		List<ProductDetails> listOfProds=new ArrayList<ProductDetails>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(select);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ProductDetails details=new  ProductDetails();
					details.setProductid(rs.getInt("Product_ID"));
					details.setProductname(rs.getString("Product_Name"));
					details.setProductype(rs.getString("Product_Type"));
					details.setProductprice(rs.getString("Product_Price"));
					listOfProds.add(details);
				}
				return listOfProds;
			}else return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	
	
}
