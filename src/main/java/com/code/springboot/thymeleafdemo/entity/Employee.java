package com.code.springboot.thymeleafdemo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


@Entity
@Table(name="employee")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name = "info")
	private String info;

	@Column(name="profilePicture")
	private  String profilePicture;

	@Column(name="size")
	private long size;

	@Lob
	@Column(name="content")
	private byte[] content;



// define constructors
	
	public Employee() {
		
	}




}











