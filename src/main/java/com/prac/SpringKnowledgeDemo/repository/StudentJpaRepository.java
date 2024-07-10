package com.prac.springknowledgedemo.repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.springknowledgedemo.beans.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentJpaRepository {

	@Autowired
	EntityManager entityManager;
	
	public List<Student> findAllStudents() {
		return entityManager.createNativeQuery("SELECT "
					+ " student_Id, \r\n"
					+ " student_Name, \r\n"
					+ " phone_Number,\r\n"
					+ " marks_Obtained"
					+ " FROM Student", Student.class).getResultList();
	}
	
	public Student findById(Integer sId) {
		return entityManager.find(Student.class, sId);
	}
	
	public List<Student> findTopStudents(Integer number) {
		TypedQuery<Student> namedQuery = entityManager.createNamedQuery("findAllStudents", Student.class);
		return namedQuery.getResultList().stream()
										 .sorted(Comparator.comparing(Student :: getMarksObtained).reversed())
										 .limit(number).collect(Collectors.toList());
	}
	
	@Transactional
	public void insert(Student student) {
		entityManager.persist(student);
	}
	
	@Transactional
	public Student update(Student student) {
		return entityManager.merge(student);
	}
}
