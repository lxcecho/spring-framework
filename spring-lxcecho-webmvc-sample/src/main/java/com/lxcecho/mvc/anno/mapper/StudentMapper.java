package com.lxcecho.mvc.anno.mapper;

import com.lxcecho.mvc.anno.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2025-06-01
 */
public interface StudentMapper {
	@Select("SELECT * FROM student")
	List<Student> findAll();

	@Select("SELECT * FROM student WHERE id = #{id}")
	Student findStudentById(Long id);

	@Insert("INSERT INTO student (name, email) VALUES (#{name}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
		// 如果需要获取自增主键
	int insertStudent(Student student);

	/**
	 * 根据ID更新学生信息
	 *
	 * @param student 要更新的学生对象，必须包含 id 属性
	 * @return 受影响的行数
	 */
	@Update("UPDATE student SET name = #{name}, email = #{email} WHERE id = #{id}")
	int updateStudent(Student student);

	/**
	 * 根据ID删除信息
	 *
	 * @param id id 属性
	 * @return 受影响的行数
	 */
	@Delete("DELETE FROM student WHERE id = #{id}")
	int deleteStudentById(Long id);
}
