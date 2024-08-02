package com.incraftiv.interviewForm;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * this method save the data and for dublicate email give error beacuse email is
	 * unique but we want that if client enter the dublicate email than email.html
	 * page show in whch show some message so i have to create new method
	 */
	public void saveUser(User user) {
		userRepository.save(user);
	}
/*
 * this method return true if email will be exist otherwise return false.
 */
	public boolean emailExists(String email) {
		if(userRepository.findByEmaily(email)!=null) {
			return true;
		}{
			return false;
		}
	    
	}
	public User getDetailById(long id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}

	}

	
	/*
	 * @Query(value ="SELECT * FROM incraftiv.interview_details  WHERE name LIKE %:name%",nativeQuery = true) 
	 * List<String> findAllNames(@Param("name") String name);
	 * return string 
	 */
	public List<String> getDetailService(String name) {
		return userRepository.findAllNames(name);
	}

	/*
	 * @Query(value ="SELECT * FROM incraftiv.interview_details  WHERE name LIKE %:name%",nativeQuery = true)
	 *  List<User> findAllNamess(@Param("name") String name);
	 *  return User[best way return Entity]
	 */
	public List<User> getDetailServicee(String name) {
		return userRepository.findAllNamess(name);
	}

	public Optional<User> getDetailByemail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> getAllUsers() {
		// we can sort by name email id or xyz
		// return userRepository.findAll(Sort.by(Sort.Direction.DESC, "email"));
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		// return userRepository.findAll();
	}

	public User deleteUser(long id) {
		User user = getDetailById(id);
		if (user != null) {
			userRepository.delete(user);
			return user;
		} else {
			return null;
		}

	}

}
