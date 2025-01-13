package com.springBootCURD.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table
//@Data
@Getter
@Setter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@Getter
//	@Setter
	@Column
	private String name;
//	@Getter
//	@Setter
	@Column
	private String email;
//	@Getter
//	@Setter
	@Column
	private String mobile;

	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getMobile() { return mobile; }
	 * 
	 * public void setMobile(String mobile) { this.mobile = mobile; }
	 */
}
