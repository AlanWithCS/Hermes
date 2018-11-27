package onlineShop.dao;

import java.util.List;

import onlineShop.model.Product;

public interface ProductDao {
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(int productId);
	
	public Product getProductById(int productId);
	
	List<Product> getAllProducts();
}
