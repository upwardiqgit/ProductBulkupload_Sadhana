package org.DAO;

import java.util.List;

import org.model.ProductDetails;

public interface UpwardDAO {

	List<ProductDetails> getAllDetails();
	List<ProductDetails> getDetailsBasedOnTypeAndName(String name,String type);
	
}
