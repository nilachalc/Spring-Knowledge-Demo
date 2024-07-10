package com.prac.springknowledgedemo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prac.springknowledgedemo.beans.Course;
import com.prac.springknowledgedemo.repository.CourseJdbcRepository;

@RestController
public class CourseController {

	@Autowired
	public CourseJdbcRepository repository;
	
	@RequestMapping(path = "/Courses")
	public List<Course> readAllCourse() {
		/*return List.of(new Course("1", "CSE", 10)
					 , new Course("2", "ECE", 20)
					 , new Course("3", "IT", 30));*/
		
		return repository.findAllCourses();
	}
	
	@RequestMapping(path = "/Courses/{cId}")
	private Course getCourseById(@PathVariable(name = "cId", required = true ) String cId) {
		return repository.findCourseById(cId);
	}
	
	@RequestMapping(path = "/Courses/Instructor/{iName}")
	private List<Course> getCourseByInstructor(@PathVariable(name = "iName", required = true) String iName
											, @RequestParam(name = "qpType", defaultValue = "i") String qpType) {
		System.out.println("qpType : " + qpType);
		return repository.findCoursesByInstructor(iName);
	}
	
	@RequestMapping(path = "/Courses/Instructor/Top")
	private List<Course> getTopTwoCourseByInstructor(@RequestParam(name = "number", defaultValue = "2") Integer number) {
		return repository.findTopTwoCourses(number);
	}
	
	@RequestMapping(path = "/Courses/Insert")
	private String insert() {
		Course course = new Course();
		course.setCourseId("106");
		course.setCourseName("ME");
		course.setInstructorName("Jayanta");
		course.setNumberOfStudents(new BigDecimal(65));
		return "Number of Rows inserted : " + repository.insert(course);
	}
	
	@RequestMapping(path = "/Courses/Update/{cId}")
	private String update(@PathVariable(name = "cId", required = true) String cId
						, @RequestParam(name = "courseName", defaultValue = "Updated Course") String courseName
						, @RequestParam(name = "instructorName", defaultValue = "Updated Instructor") String instructorName
						, @RequestParam(name = "numberOfStudents", defaultValue = "100") Integer numberOfStudents) {
		Course course = repository.findCourseById(cId);
		course.setCourseId(cId);
		course.setCourseName(courseName);
		course.setInstructorName(instructorName);
		course.setNumberOfStudents(new BigDecimal(numberOfStudents));
		return "Number of Rows updated : " + repository.update(course) + repository.findCourseById(cId);
	}
}