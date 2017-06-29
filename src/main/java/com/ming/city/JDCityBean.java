package com.ming.city;

import java.util.ArrayList;
import java.util.List;

public class JDCityBean {

	private String code;
	private String name;
	private String pcode;
	private List<JDCityBean> subCities = new ArrayList<JDCityBean>();

	public JDCityBean(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public JDCityBean(String code, String name, String pcode) {
		super();
		this.code = code;
		this.name = name;
		this.pcode = pcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public List<JDCityBean> getSubCities() {
		return subCities;
	}

	public JDCityBean addSubCity(String name) {
		JDCityBean jdCityBean = new JDCityBean(null, name);
		jdCityBean.setPcode(this.code);
		int index = subCities.size() + 1;
		jdCityBean.setCode(this.code + (index < 10 ? "0" + index : "" + index));
		subCities.add(jdCityBean);
		return jdCityBean;
	}
}
