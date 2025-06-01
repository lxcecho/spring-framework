package com.lxcecho.mvc.anno.service;

import com.lxcecho.mvc.anno.entity.Student;

import java.util.List;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-06-01
 */
public interface StudentService {

	List<Student> findAllStudents();

	Student findStudentById(Long id);

	int insertStudent(Student student);

	int updateStudent(Student student);

	int deleteStudentById(Long id);
}
