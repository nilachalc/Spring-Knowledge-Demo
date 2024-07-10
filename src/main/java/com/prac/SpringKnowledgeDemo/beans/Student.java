package com.prac.springknowledgedemo.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@NamedQuery(name = "findAllStudents", query = "SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue
	public Integer studentId;
	public String studentName;
	public String phoneNumber;
	public BigDecimal marksObtained;
}