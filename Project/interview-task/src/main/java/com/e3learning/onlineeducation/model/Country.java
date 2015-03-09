/**
 * 
 * @author abdelrahman
 * Date 06/03/2015
 * Country entity represents the Countries table in the database
 */

package com.e3learning.onlineeducation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "countries")
public class Country implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8832452056722010287L;

	@Range(min = 1, max = 242)
	@Id
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@NotNull
	private String code;
	
	@NotNull
	@Size(min=2, max=255)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
