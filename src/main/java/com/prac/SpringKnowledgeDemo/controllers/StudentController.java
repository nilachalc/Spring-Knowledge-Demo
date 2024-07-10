package com.prac.springknowledgedemo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prac.springknowledgedemo.beans.Student;
import com.prac.springknowledgedemo.repository.StudentJpaRepository;

@RestController
public class StudentController {

	@Autowired
	public StudentJpaRepository jpaRepository;
	
	@RequestMapping(path = "/Students")
	public List<Student> readAllCourse() {
		return jpaRepository.findAllStudents();
	}
	
	@RequestMapping(path = "/Students/{sId}")
	private Student getCourseById(@PathVariable(name = "sId", required = true ) Integer cId) {
		return jpaRepository.findById(cId);
	}
	
	/*@RequestMapping(path = "/Courses/Instructor/{iName}")
	private List<Course> getCourseByInstructor(@PathVariable(name = "iName", required = true) String iName
											, @RequestParam(name = "qpType", defaultValue = "i") String qpType) {
		System.out.println("qpType : " + qpType);
		return repository.findCoursesByInstructor(iName);
	}*/
	
	@RequestMapping(path = "/Students/Marks/Top")
	private List<Student> getTopTwoStudentByMarksObtained(@RequestParam(name = "number", defaultValue = "2") Integer number) {
		return jpaRepository.findTopStudents(number);
	}
	
	@RequestMapping(path = "/Students/Insert")
	private String insert() {
		Student student = new Student();
		student.setStudentName("Anil");
		student.setPhoneNumber("9898989898");
		student.setMarksObtained(new BigDecimal(85));
		jpaRepository.insert(student);
		return "Number of Rows inserted.";
	}
	
	@RequestMapping(path = "/Students/Update/{sId}")
	private String update(@PathVariable(name = "sId", required = true) Integer sId
						, @RequestParam(name = "studentName", defaultValue = "Updated Student") String studentName
						, @RequestParam(name = "phoneNumber", defaultValue = "9999999999") String phoneNumber
						, @RequestParam(name = "marksObtained", defaultValue = "100") Integer marksObtained) {
		Student student = jpaRepository.findById(sId);
		student.setStudentId(sId);
		student.setStudentName(studentName);
		student.setPhoneNumber(phoneNumber);
		student.setMarksObtained(new BigDecimal(marksObtained));
		return "Number of Rows updated : " + jpaRepository.update(student);
	}
}