package com.incraftiv.interviewForm;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByEmail(String email);
	// User findByEmaily(String email);
	 
	 @Query(value = "SELECT * FROM incraftiv.interview_details  WHERE name LIKE %:name%",nativeQuery = true)
	    List<String> findAllNames(@Param("name") String name);
	
	 @Query(value = "SELECT * FROM incraftiv.interview_details  WHERE name LIKE %:name%",nativeQuery = true)
	    List<User> findAllNamess(@Param("name") String name);
	
	 @Query(value = "SELECT * FROM incraftiv.interview_details WHERE email = :email", nativeQuery = true)
	 User findByEmaily(String email);
	
	
	
}
