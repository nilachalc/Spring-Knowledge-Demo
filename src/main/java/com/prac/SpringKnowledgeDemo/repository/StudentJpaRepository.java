package com.prac.springknowledgedemo.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.springknowledgedemo.beans.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentJpaRepository {

	@Autowired
	EntityManager entityManager;
	
	public List<Student> findAllStudents() {
		return entityManager.createNativeQuery("SELECT "
					+ " student_Id,\r\n"
					+ " student_Name,\r\n"
					+ " phone_Number,\r\n"
					+ " marks_Obtained,\r\n"
					+ " created_Time,\r\n"
					+ " last_Updated_Time\r\n"
					+ " FROM Student", Student.class).getResultList();
	}
	
	public Student findById(Integer sId) {
		return entityManager.find(Student.class, sId);
	}
	
	public List<Student> findTopStudents(Integer number) {
		//TypedQuery<Student> namedQuery = entityManager.createNamedQuery("findAllStudents", Student.class);
		TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
		return query.getResultList().stream()
									 .sorted(Comparator.comparing(Student :: getMarksObtained).reversed())
									 .limit(number).collect(Collectors.toList());
	}
	
	@Transactional
	public Student save(Student student) {
		if (student.getStudentId() == null) {
			entityManager.persist(student);
			return student;
		} else {
			return entityManager.merge(student);
		}
	}
	
	@Transactional
	public List<String> efTest(Student student) {
		List<String> list = new ArrayList<>();
		list.add("Inside Repository");
		
		student = entityManager.merge(student);
		entityManager.flush();
		entityManager.detach(student);
		student.setMarksObtained(new BigDecimal(500));
		
		list.add("The current student details are : " + student.toString());
		//entityManager.refresh(student);
		//list.add("The current student details after refresh are : " + student.toString());
		entityManager.detach(student);
		list.add("The current student details after detach are : " + student.toString());
		list.add("Going out of Repository");
		return list;
	}
}
