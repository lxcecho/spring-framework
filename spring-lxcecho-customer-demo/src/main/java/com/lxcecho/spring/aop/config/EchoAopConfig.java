package com.lxcecho.spring.aop.config;

/**
 * @author lxcecho@gmail.com
 * @since 2026/4/3
 */
public class EchoAopConfig {
	private String pointCut;
	private String aspectClass;
	private String aspectBefore;
	private String aspectAfter;
	private String aspectAfterThrow;
	private String aspectAfterThrowingName;

	@Override
	public String toString() {
		return "EchoAopConfig{" +
				"pointCut='" + pointCut + '\'' +
				", aspectClass='" + aspectClass + '\'' +
				", aspectBefore='" + aspectBefore + '\'' +
				", aspectAfter='" + aspectAfter + '\'' +
				", aspectAfterThrow='" + aspectAfterThrow + '\'' +
				", aspectAfterThrowingName='" + aspectAfterThrowingName + '\'' +
				'}';
	}

	public EchoAopConfig() {
	}

	public EchoAopConfig(String pointCut, String aspectClass, String aspectBefore, String aspectAfter, String aspectAfterThrow, String aspectAfterThrowingName) {
		this.pointCut = pointCut;
		this.aspectClass = aspectClass;
		this.aspectBefore = aspectBefore;
		this.aspectAfter = aspectAfter;
		this.aspectAfterThrow = aspectAfterThrow;
		this.aspectAfterThrowingName = aspectAfterThrowingName;
	}

	public String getPointCut() {
		return pointCut;
	}

	public void setPointCut(String pointCut) {
		this.pointCut = pointCut;
	}

	public String getAspectClass() {
		return aspectClass;
	}

	public void setAspectClass(String aspectClass) {
		this.aspectClass = aspectClass;
	}

	public String getAspectBefore() {
		return aspectBefore;
	}

	public void setAspectBefore(String aspectBefore) {
		this.aspectBefore = aspectBefore;
	}

	public String getAspectAfter() {
		return aspectAfter;
	}

	public void setAspectAfter(String aspectAfter) {
		this.aspectAfter = aspectAfter;
	}

	public String getAspectAfterThrow() {
		return aspectAfterThrow;
	}

	public void setAspectAfterThrow(String aspectAfterThrow) {
		this.aspectAfterThrow = aspectAfterThrow;
	}

	public String getAspectAfterThrowingName() {
		return aspectAfterThrowingName;
	}

	public void setAspectAfterThrowingName(String aspectAfterThrowingName) {
		this.aspectAfterThrowingName = aspectAfterThrowingName;
	}
}
