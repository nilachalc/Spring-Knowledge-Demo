package com.prac.springknowledgedemo.repository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prac.springknowledgedemo.beans.JdbcCourse;

@Repository
public class CourseJdbcRepository {

	@Autowired
	JdbcTemplate template;
	
	public List<JdbcCourse> findAllCourses() {
		// Data extracted by queryForList method.
		
		/*List<Map<String,Object>> rawData= template.queryForList("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM JdbcCourse");
		
		return rawData.stream().map(cm -> new JdbcCourse(
										  (String)cm.get("courseId")
										, (String)cm.get("courseName")
										, (String)cm.get("instructorName")
										, (BigDecimal)cm.get("numberOfStudents"))).collect(Collectors.toList());*/
		
		// Data extracted by query method with BeanPropertyRowMapper Class.
		/*return template.query("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM JdbcCourse", new BeanPropertyRowMapper<JdbcCourse>(JdbcCourse.class));*/
		
		// Data extracted by query method with RowMapper Interface.
		/*return template.query("SELECT "
				+ " courseid, \r\n"
				+ " coursename, \r\n"
				+ " instructorname,\r\n"
				+ " numberofstudents FROM JdbcCourse", (rs, rowNum) -> {
					JdbcCourse course = new JdbcCourse();
					
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
				+ " numberofstudents FROM JdbcCourse", (rs, rowNum) -> {
					JdbcCourse jdbcCourse = new JdbcCourse();
					
					jdbcCourse.setCourseId(rs.getString("courseId"));
					jdbcCourse.setCourseName(rs.getString("courseName"));
					jdbcCourse.setInstructorName(rs.getString("instructorName"));
					jdbcCourse.setNumberOfStudents(rs.getBigDecimal("numberOfStudents"));
					
					return jdbcCourse;
				}).collect(Collectors.toList());
	}
	
	public JdbcCourse findCourseById(String courseId) {
		// This method is fine for single column.
		 /*return template.queryForObject("SELECT "
					//+ " courseid, \r\n"
					+ " coursename, \r\n"
					//+ " instructorname,\r\n"
					//+ " numberofstudents"
					+ " FROM JdbcCourse WHERE courseid = ?", String.class, courseId);*/
		 
		// This method is fine for multiple column.
		 return template.queryForObject("SELECT "
						+ " courseid, \r\n"
						+ " coursename, \r\n"
						+ " instructorname,\r\n"
						+ " numberofstudents"
						+ " FROM JdbcCourse WHERE courseid = ?", new BeanPropertyRowMapper<JdbcCourse>(JdbcCourse.class), new Object[] {courseId});
	}
	
	public List<JdbcCourse> findCoursesByInstructor(String instructorName) {
		return template.query("SELECT "
						+ " courseid, \r\n"
						+ " coursename, \r\n"
						+ " instructorname,\r\n"
						+ " numberofstudents"
						+ " FROM JdbcCourse WHERE instructorname = ?", new BeanPropertyRowMapper<JdbcCourse>(JdbcCourse.class), new Object[] {instructorName});
	}
	
	public List<JdbcCourse> findTopTwoCourses(Integer number) {
		return template.query("SELECT "
						+ " courseid, \r\n"
						+ " coursename, \r\n"
						+ " instructorname,\r\n"
						+ " numberofstudents"
						+ " FROM JdbcCourse", new BeanPropertyRowMapper<JdbcCourse>(JdbcCourse.class))
				.stream().sorted(Comparator.comparing(JdbcCourse :: getNumberOfStudents).reversed()).limit(number).collect(Collectors.toList());
	}
	
	public Integer insert(JdbcCourse jdbcCourse) {
		return template.update("INSERT INTO JdbcCourse(courseid, coursename, instructorname, startdate, numberofstudents)"
				+ " VALUES(?, ?, ?, ?, ?)", jdbcCourse.getCourseId(), jdbcCourse.getCourseName(), jdbcCourse.getInstructorName()
				, new Timestamp(System.currentTimeMillis()), jdbcCourse.getNumberOfStudents());
	}
	
	public Integer update(JdbcCourse jdbcCourse) {
		return template.update("UPDATE JdbcCourse SET coursename = ?"
								+ ", instructorname = ?"
								+ ", startdate = ?"
								+ ", numberofstudents = ?"
				+ " WHERE courseid = ?", jdbcCourse.getCourseName(), jdbcCourse.getInstructorName()
				, new Timestamp(System.currentTimeMillis()), jdbcCourse.getNumberOfStudents(), jdbcCourse.getCourseId());
	}
}
