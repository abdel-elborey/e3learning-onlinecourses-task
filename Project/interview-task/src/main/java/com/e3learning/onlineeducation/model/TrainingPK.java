/**
 * 
 * @author abdelrahman
 * Date 06/03/2015
 * The class represents the composite primary key for the many to many relationship between accounts and courses
 */

package com.e3learning.onlineeducation.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable 
public class TrainingPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2323612114554099177L;
	
	private Long courseId;
	
	private Long accountId;

	

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		TrainingPK other = (TrainingPK) object;

		if (courseId != null ? !courseId.equals(other.courseId) : other.courseId != null)
			return false;
		if (accountId != null ? !accountId.equals(other.accountId) : other.accountId != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (accountId != null ? accountId.hashCode() : 0);
		result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
		return result;
	}
}
