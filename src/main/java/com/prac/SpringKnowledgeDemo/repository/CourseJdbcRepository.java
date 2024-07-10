package com.prac.springknowledgedemo.repository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prac.springknowledgedemo.beans.Course;

@Repository
public class CourseJdbcRepository {

	@Autowired
	JdbcTemplate template;
	
	public List<Course> findAllCourses() {
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
		
		// Data extracted by query method with BeanPropertyRowMapper Class.
		/*return template.query("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM Course", new BeanPropertyRowMapper<Course>(Course.class));*/
		
		// Data extracted by query method with RowMapper Interface.
		/*return template.query("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM Course", (rs, rowNum) -> {
					Course course = new Course();
					
					course.setCourseId(rs.getString("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setInstructorName(rs.getString("instructorName"));
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
					course.setInstructorName(rs.getString("instructorName"));
					course.setNumberOfStudents(rs.getBigDecimal("numberOfStudents"));
					
					return course;
				}).collect(Collectors.toList());
	}
	
	public Course findCourseById(String courseId) {
		// This method is fine for single column.
		 /*return template.queryForObject("SELECT "
					//+ " courseid, \r\n"
					+ " coursename, \r\n"
					//+ " instructorname,\r\n"
					//+ " numberofstudents"
					+ " FROM Course WHERE courseid = ?", String.class, courseId);*/
		 
		// This method is fine for multiple column.
		 return template.queryForObject("SELECT "
						+ " courseid, \r\n"
						+ " coursename, \r\n"
						+ " instructorname,\r\n"
						+ " numberofstudents"
						+ " FROM Course WHERE courseid = ?", new BeanPropertyRowMapper<Course>(Course.class), new Object[] {courseId});
	}
	
	public List<Course> findCoursesByInstructor(String instructorName) {
		return template.query("SELECT "
						+ " courseid, \r\n"
						+ " coursename, \r\n"
						+ " instructorname,\r\n"
						+ " numberofstudents"
						+ " FROM Course WHERE instructorname = ?", new BeanPropertyRowMapper<Course>(Course.class), new Object[] {instructorName});
	}
	
	public List<Course> findTopTwoCourses(Integer number) {
		return template.query("SELECT "
						+ " courseid, \r\n"
						+ " coursename, \r\n"
						+ " instructorname,\r\n"
						+ " numberofstudents"
						+ " FROM Course", new BeanPropertyRowMapper<Course>(Course.class))
				.stream().sorted(Comparator.comparing(Course :: getNumberOfStudents).reversed()).limit(number).collect(Collectors.toList());
	}
	
	public Integer insert(Course course) {
		return template.update("INSERT INTO Course(courseid, coursename, instructorname, startdate, numberofstudents)"
				+ " VALUES(?, ?, ?, ?, ?)", course.getCourseId(), course.getCourseName(), course.getInstructorName()
				, new Timestamp(System.currentTimeMillis()), course.getNumberOfStudents());
	}
	
	public Integer update(Course course) {
		return template.update("UPDATE Course SET coursename = ?"
								+ ", instructorname = ?"
								+ ", startdate = ?"
								+ ", numberofstudents = ?"
				+ " WHERE courseid = ?", course.getCourseName(), course.getInstructorName()
				, new Timestamp(System.currentTimeMillis()), course.getNumberOfStudents(), course.getCourseId());
	}
}
