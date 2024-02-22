package org.jsp.springbootuserproductapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootuserproductapp.dao.ProductDao;
import org.jsp.springbootuserproductapp.dao.UserDao;
import org.jsp.springbootuserproductapp.dto.Product;
import org.jsp.springbootuserproductapp.dto.ResponseStructure;
import org.jsp.springbootuserproductapp.dto.User;
import org.jsp.springbootuserproductapp.exception.IdNotFoundException;
import org.jsp.springbootuserproductapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User user = recUser.get();
			user.getProducts().add(product);
			
			product.setUser(user);
			
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("Product saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		Optional<Product> recProduct = productDao.findById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("Product updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new ProductNotFoundException("Cannot update Product as Product Id is invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		Optional<Product> recProduct = productDao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("invalid product id");
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByName(String name) {
		List<Product> products = productDao.findByName(name);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("List of products with the product name:" + name);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Product for the  name: " + name);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		List<Product> products = productDao.findByBrand(brand);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("List of products with the brand:" + brand);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Product for the  brand " + brand);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category){
		List<Product> products = productDao.findByCategory(category);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if(products.size()>0) {
			structure.setData(products);
			structure.setMessage("List of Products with the category:" + category);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Product for the category "+category);
		
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByUserId(int id){
		List<Product> products = productDao.findByUserId(id);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if(products.size()>0) {
			structure.setData(products);
			structure.setMessage("List of Product found by User id: "+id);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Products found for user id: "+id);
		}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByUser(long phone,String password){
		List<Product> products = productDao.findByUser(phone,password);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if(products.size()>0) {
			structure.setData(products);
			structure.setMessage("List of Product found by User phone and password ");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Products found for entered phone and password");
		}
	
}
