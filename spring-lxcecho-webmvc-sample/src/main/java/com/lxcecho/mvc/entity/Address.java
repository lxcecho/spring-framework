package com.lxcecho.mvc.entity;

/**
 * @author lxcecho lxcecho@gmail.com
 * @since 2020/8/3
 */
public class Address {

	private String province;

	private String city;

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + "]";
	}

}
