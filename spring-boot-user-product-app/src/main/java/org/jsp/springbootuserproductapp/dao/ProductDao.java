package org.jsp.springbootuserproductapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootuserproductapp.dto.Product;
import org.jsp.springbootuserproductapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}
	public boolean deleteById(int id){
		Optional<Product> recProduct = productRepository.findById(id);
		if(recProduct.isPresent()) {
			productRepository.delete(recProduct.get());
			return true;
		}
		return false;
	}
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	public List<Product> findByName(String name){
		return productRepository.findByName(name);
	}
	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}
	
	public List<Product> findByUserId(int user_id){
		return productRepository.findByUserId(user_id);
	}
	public List<Product> findByUser(long phone,String password){
		return productRepository.findByUserPhoneandPassword(phone, password);
	}
}
