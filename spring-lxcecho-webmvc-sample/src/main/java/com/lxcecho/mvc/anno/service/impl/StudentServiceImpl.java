package com.lxcecho.mvc.anno.service.impl;

import com.lxcecho.mvc.anno.entity.Student;
import com.lxcecho.mvc.anno.mapper.StudentMapper;
import com.lxcecho.mvc.anno.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-06-01
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> findAllStudents() {
		return studentMapper.findAll();
	}

	@Override
	public Student findStudentById(Long id) {
		return studentMapper.findStudentById(id);
	}

	@Override
	public int insertStudent(Student student) {
		return studentMapper.insertStudent(student);
	}

	@Override
	public int updateStudent(Student student) {
		return studentMapper.updateStudent(student);
	}

	@Override
	public int deleteStudentById(Long id) {
		return studentMapper.deleteStudentById(id);
	}
}
