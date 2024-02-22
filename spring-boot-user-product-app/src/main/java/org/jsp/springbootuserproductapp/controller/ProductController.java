package org.jsp.springbootuserproductapp.controller;

import java.util.List;

import org.jsp.springbootuserproductapp.dto.Product;
import org.jsp.springbootuserproductapp.dto.ResponseStructure;
import org.jsp.springbootuserproductapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,@PathVariable(name="user_id")int user_id){
		return productService.saveProduct(product, user_id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product){
		return productService.updateProduct(product);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable(name="id") int id){
		return productService.findById(id);
	}
	@GetMapping("/by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByName(@PathVariable(name="name") String name){
		return productService.findByName(name);
	}
	@GetMapping("/by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable(name="brand")String brand){
		return productService.findByBrand(brand);
	}
	@GetMapping("/by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable(name="category") String category){
		return productService.findByCategory(category);
	}
	@GetMapping("/by-user/{user_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByUser(@PathVariable(name="user_id")int user_id){
		return productService.findByUserId(user_id);
	}
	@PostMapping("/by-user")
	public ResponseEntity<ResponseStructure<List<Product>>> findByUser(@RequestParam long phone, @RequestParam String password){
		return productService.findByUser(phone, password);
				
	}
	
}
