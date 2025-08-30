package com.lxcecho.xml.ioc.bean;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2023/12/10
 */
public class Lesson {

	private String lessonName;

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	@Override
	public String toString() {
		return "Lesson{" +
				"lessonName='" + lessonName + '\'' +
				'}';
	}
}
