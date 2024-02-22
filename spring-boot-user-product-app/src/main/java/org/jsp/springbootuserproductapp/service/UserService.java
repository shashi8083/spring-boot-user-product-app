package org.jsp.springbootuserproductapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootuserproductapp.dao.UserDao;
import org.jsp.springbootuserproductapp.dto.ResponseStructure;
import org.jsp.springbootuserproductapp.dto.User;
import org.jsp.springbootuserproductapp.exception.DataNotFoundException;
import org.jsp.springbootuserproductapp.exception.IdNotFoundException;
import org.jsp.springbootuserproductapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("User saved");
		structure.setData(userDao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		Optional<User> recUser = userDao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());

			structure.setMessage("User Updated");
			structure.setData(userDao.saveUser(user));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Found By Id");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User found");
			structure.setData("User Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			userDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseStructure<List<User>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setMessage("User Found");
		structure.setData(userDao.findAll());
		structure.setStatusCode(HttpStatus.FOUND.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<List<User>>> findByName(String name) {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> users = userDao.findByName(name);
		structure.setData(users);
		if (users.size() > 0) {
			structure.setMessage("List of User with entered name");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		throw new DataNotFoundException("name not belongs to User");
	}

	public ResponseEntity<ResponseStructure<User>> findByPhone(long phone) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findByPhone(phone);
		if (recUser.isPresent()) {
			structure.setMessage("Merchant found By phone number");
			structure.setData(userDao.findByPhone(phone).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);

		}
		throw new DataNotFoundException("Phone number not belongs to  User");
	}
	
	public ResponseEntity<ResponseStructure<User>> findByEmail(String email) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findByEmail(email);
		if (recUser.isPresent()) {
			structure.setMessage("Merchant found By email id");
			structure.setData(userDao.findByEmail(email).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);

		}
		throw new DataNotFoundException("Email not belongs to  User");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone,String password){
		Optional<User> recUser = userDao.verify(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if(recUser.isPresent()) {
			structure.setMessage("User verify by email and password");
			structure.setData(userDao.verify(phone, password).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Phone Number or Password"); 
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email,String password){
		Optional<User> recUser = userDao.verify(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if(recUser.isPresent()) {
			structure.setMessage("User verify by email and password");
			structure.setData(userDao.verify(email, password).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Phone Number or Password"); 
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(int id,String password){
		Optional<User> recUser = userDao.verify(id, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if(recUser.isPresent()) {
			structure.setMessage("User verify by id and password");
			structure.setData(userDao.verify(id, password).get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid id or Password"); 
	}
	
	
}
