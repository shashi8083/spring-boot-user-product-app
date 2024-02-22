package org.jsp.springbootuserproductapp.repository;

import java.util.List;

import org.jsp.springbootuserproductapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByName(String name);

	public List<Product> findByBrand(String brand);

	public List<Product> findByCategory(String category);

	@Query("select u.products from User u where u.id=?1")
	public List<Product> findByUserId(int user_id);

	@Query("select u.products from User u where u.phone=?1 and u.password=?2")
	public List<Product> findByUserPhoneandPassword(long phone, String password);
}
