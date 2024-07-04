package com.prac.springknowledgedemo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prac.springknowledgedemo.beans.Course;

@Repository
public class CourseRepository {

	@Autowired
	JdbcTemplate template;
	
	public List<Course> FindAllCourse() {
		// Data extracted by queryForList method.
		
		/*List<Map<String,Object>> rawData= template.queryForList("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM Course");
		
		return rawData.stream().map(cm -> new Course(
										  (String)cm.get("courseId")
										, (String)cm.get("courseName")
										, (String)cm.get("instructorName")
										, (BigDecimal)cm.get("numberOfStudents"))).collect(Collectors.toList());*/
		
		// Data extracted by query method with RowMapper Interface.
		/*return template.query("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM Course", (rs, rowNum) -> {
					Course course = new Course();
					
					course.setCourseId(rs.getString("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setInstructorName(rs.getString("courseName"));
					course.setNumberOfStudents(rs.getBigDecimal("numberOfStudents"));
					
					return course;
				});*/
		
		// Data extracted by queryForStream method with RowMapper Interface.
		return template.queryForStream("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM Course", (rs, rowNum) -> {
					Course course = new Course();
					
					course.setCourseId(rs.getString("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setInstructorName(rs.getString("courseName"));
					course.setNumberOfStudents(rs.getBigDecimal("numberOfStudents"));
					
					return course;
				}).collect(Collectors.toList());
	}
}
