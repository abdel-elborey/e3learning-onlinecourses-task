/**
 * 
 * @author abdelrahman
 * Date 06/03/2015
 * This class represents the many to many relationship between courses and accounts
 * As the many to many relationship is not a pure relationship and it has attributes like: startDate, endDate and grade
 * it is required to model it as a separate class with a composite primary key an instance of TrainingPK class
 */

package com.e3learning.onlineeducation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Training implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2684954223852770734L;


	@EmbeddedId
	private TrainingPK trainingPK = new TrainingPK();

	@MapsId("courseId")
    @JoinColumn(name="course_id")
    @ManyToOne
	private Course course;

	@MapsId("accountId")
    @JoinColumn(name="account_id")
    @ManyToOne
	private Account account;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	private Integer grade;

	public TrainingPK getTrainingPK() {
		return trainingPK;
	}

	public void setTrainingPK(TrainingPK trainingPK) {
		this.trainingPK = trainingPK;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
 
		Training other = (Training) object;
 
		if (getTrainingPK() != null ? !getTrainingPK().equals(other.getTrainingPK())
				: other.getTrainingPK() != null)
			return false;
 
		return true;
	}
 
	public int hashCode() {
		return (getTrainingPK() != null ? getTrainingPK().hashCode() : 0);
	}
}
