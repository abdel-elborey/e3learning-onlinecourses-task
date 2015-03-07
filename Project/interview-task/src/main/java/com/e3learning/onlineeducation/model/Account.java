/**
 * 
 * @author abdelrahman
 * Date 06/03/2015
 * Account entity represents the Accounts table in the database
 */

package com.e3learning.onlineeducation.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "accounts")
public class Account implements Serializable{
  
	private static final long serialVersionUID = -1136205768989027634L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@List({ @Length(min = 1, message = "The field must be at least 1 characters"),
			@Length(max = 255, message = "The field must be less than 255 characters") })
	@NotEmpty
	private String firstName;
	
	@List({ @Length(min = 1, message = "The field must be at least 1 characters"),
			@Length(max = 255, message = "The field must be less than 255 characters") })
	@NotEmpty
	private String lastName;
	
	@List({ @Length(min = 1, message = "The field must be at least 1 characters"),
			@Length(max = 255, message = "The field must be less than 255 characters") })
	@NotEmpty
	@Email
	private String email;

	@Enumerated(EnumType.ORDINAL)
	private AccountStatus status = AccountStatus.NONACTIVE;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private java.util.List<Training> training = new ArrayList<Training>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public java.util.List<Training> getTraining() {
		return training;
	}

	public void setTraining(java.util.List<Training> training) {
		this.training = training;
	}

	
	
}
