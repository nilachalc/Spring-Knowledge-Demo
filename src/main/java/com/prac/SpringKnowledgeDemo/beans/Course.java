package com.prac.springknowledgedemo.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String courseId;
	public String courseName;
	public String instructorName;
	public BigDecimal numberOfStudents;
	
	public Course(String courseId, String courseName, String instructorName, BigDecimal numberOfStudents) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.instructorName = instructorName;
		this.numberOfStudents = numberOfStudents;
	}
}