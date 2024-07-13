package com.prac.springknowledgedemo.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

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
@Component
public class JdbcCourse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String courseId;
	public String courseName;
	public String instructorName;
	public BigDecimal numberOfStudents;
	
}