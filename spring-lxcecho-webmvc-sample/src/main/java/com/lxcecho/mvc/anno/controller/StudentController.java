package com.lxcecho.mvc.anno.controller;

import com.lxcecho.mvc.anno.entity.Student;
import com.lxcecho.mvc.anno.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-06-01
 */
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * 获取所有学生信息：HTTP GET /findAllUsers
	 *
	 * @return
	 */
	@GetMapping(value = "findAllStudents")
	public List<Student> findAllStudents() {
		return studentService.findAllStudents();
	}

	/**
	 * 根据ID获取学生信息：HTTP GET /findStudentById/{id}
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("findStudentById/{id}")
	public Student findStudentById(@PathVariable Long id) {
		return studentService.findStudentById(id);
	}

	/**
	 * 创建新学生记录：HTTP POST /createStudent
	 *
	 * @param student
	 * @return
	 */
	@PostMapping("createStudent")
	public String createStudent(@RequestBody Student student) {
		int result = studentService.insertStudent(student);
		if (result > 0) {
			return "学生创建成功，ID: " + student.getId();
		} else {
			return "学生创建失败";
		}
	}

	/**
	 * 更新学生信息：HTTP PUT /updateStudent
	 *
	 * @param student
	 * @return update raws
	 */
	@PutMapping("updateStudent")
	public String updateStudent(@RequestBody Student student) {
		int result = studentService.updateStudent(student);
		if (result > 0) {
			return "学生更新成功";
		} else {
			return "学生更新失败或ID不存在";
		}
	}

	/**
	 * 删除学生记录：HTTP DELETE /deleteStudent/{id}
	 *
	 * @param id
	 * @return delete raws
	 */
	@DeleteMapping("deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id) {
		int result = studentService.deleteStudentById(id);
		if (result > 0) {
			return "学生删除成功";
		} else {
			return "学生删除失败或ID不存在";
		}
	}

}
