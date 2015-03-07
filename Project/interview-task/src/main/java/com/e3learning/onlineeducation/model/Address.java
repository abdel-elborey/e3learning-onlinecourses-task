/**
 * 
 * @author abdelrahman
 * Date 06/03/2015
 * Address entity represents the Addresses table in the database
 */

package com.e3learning.onlineeducation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "addresses")
public class Address implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8336856096536422928L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@List({ @Length(min = 1, message = "The field must be at least 1 characters"),
			@Length(max = 255, message = "The field must be less than 255 characters") })
	@NotEmpty
	private String streetName;
	
	@List({ @Length(min = 1, message = "The field must be at least 1 characters"),
			@Length(max = 255, message = "The field must be less than 255 characters") })
	@NotEmpty	
	private String suburb;

	@List({ @Length(min = 1, message = "The field must be at least 1 characters"),
			@Length(max = 255, message = "The field must be less than 255 characters") })
	@NotEmpty
	private String State;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
}
