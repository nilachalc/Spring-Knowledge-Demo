package com.prac.springknowledgedemo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prac.springknowledgedemo.beans.JdbcCourse;
import com.prac.springknowledgedemo.repository.CourseJdbcRepository;

@RestController
public class CourseJdbcController {

	@Autowired
	public CourseJdbcRepository repository;
	
	@RequestMapping(path = "/Courses")
	public List<JdbcCourse> readAllCourse() {
		/*return List.of(new JdbcCourse("1", "CSE", 10)
					 , new JdbcCourse("2", "ECE", 20)
					 , new JdbcCourse("3", "IT", 30));*/
		
		return repository.findAllCourses();
	}
	
	@RequestMapping(path = "/Courses/{cId}")
	private JdbcCourse getCourseById(@PathVariable(name = "cId", required = true ) String cId) {
		return repository.findCourseById(cId);
	}
	
	@RequestMapping(path = "/Courses/Instructor/{iName}")
	private List<JdbcCourse> getCourseByInstructor(@PathVariable(name = "iName", required = true) String iName
											, @RequestParam(name = "qpType", defaultValue = "i") String qpType) {
		System.out.println("qpType : " + qpType);
		return repository.findCoursesByInstructor(iName);
	}
	
	@RequestMapping(path = "/Courses/Instructor/Top")
	private List<JdbcCourse> getTopTwoCourseByInstructor(@RequestParam(name = "number", defaultValue = "2") Integer number) {
		return repository.findTopTwoCourses(number);
	}
	
	@RequestMapping(path = "/Courses/Insert")
	private String insert() {
		JdbcCourse jdbcCourse = new JdbcCourse();
		jdbcCourse.setCourseId("106");
		jdbcCourse.setCourseName("ME");
		jdbcCourse.setInstructorName("Jayanta");
		jdbcCourse.setNumberOfStudents(new BigDecimal(65));
		return "Number of Rows inserted : " + repository.insert(jdbcCourse);
	}
	
	@RequestMapping(path = "/Courses/Update/{cId}")
	private String update(@PathVariable(name = "cId", required = true) String cId
						, @RequestParam(name = "courseName", defaultValue = "Updated JdbcCourse") String courseName
						, @RequestParam(name = "instructorName", defaultValue = "Updated Instructor") String instructorName
						, @RequestParam(name = "numberOfStudents", defaultValue = "100") Integer numberOfStudents) {
		JdbcCourse jdbcCourse = repository.findCourseById(cId);
		jdbcCourse.setCourseId(cId);
		jdbcCourse.setCourseName(courseName);
		jdbcCourse.setInstructorName(instructorName);
		jdbcCourse.setNumberOfStudents(new BigDecimal(numberOfStudents));
		return "Number of Rows updated : " + repository.update(jdbcCourse) + repository.findCourseById(cId);
	}
}