package com.incraftiv.interviewForm;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "Interview_Details")
public class User {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private String name;
	@Column(unique = true)
	private String email;
	private Long contact;
	private String address;
	private String user_age;
	private String bio;
	private String job_role;
	
	private String date;
	
	@ElementCollection
	private List<String> user_interest;
	
	

}
