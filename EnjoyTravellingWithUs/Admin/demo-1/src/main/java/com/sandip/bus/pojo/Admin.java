package com.sandip.bus.pojo;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity 
@Table(name = "users") 
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = {"password"} )

public class Admin extends BaseEntity {

	@Column(name = "first_name", length = 20) 
	private String firstName;
	@Column(name = "last_name", length = 20) 
	private String lastName;
	@Column(length = 25, name = "email",unique = true) 
	private String email;
	@Column(length = 20,name = "password", nullable = false) 
	private String password;



	public Admin(String firstName, String lastName, String email, String password	) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	
	}
}

