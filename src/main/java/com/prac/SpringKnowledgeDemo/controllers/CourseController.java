package com.prac.springknowledgedemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.springknowledgedemo.beans.Course;
import com.prac.springknowledgedemo.repository.CourseRepository;

@RestController
public class CourseController {

	@Autowired
	public CourseRepository repository;  
	
	@RequestMapping(path = "/Courses")
	public List<Course> readAllCourse() {
		/*return List.of(new Course("1", "CSE", 10)
					 , new Course("2", "ECE", 20)
					 , new Course("3", "IT", 30));*/
		
		return repository.FindAllCourse();
	}
}