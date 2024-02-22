package org.jsp.springbootuserproductapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootuserproductapp.dto.User;
import org.jsp.springbootuserproductapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user); // it will do both save or update as per requirement
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public boolean deleteById(int id) {
		Optional<User> recUser = findById(id);
		if (recUser.isPresent()) {
			userRepository.delete(recUser.get());
			return true;
		}
		return false;
	}

	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	public Optional<User> findByPhone(long phone) {
		return userRepository.findByPhone(phone);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<User> verify(long phone, String password) {
		return userRepository.verify(phone, password);
	}

	public Optional<User> verify(String email, String password) {
		return userRepository.verify(email, password);
	}

	public Optional<User> verify(int id, String password) {
		return userRepository.verify(id, password);
	}

}
