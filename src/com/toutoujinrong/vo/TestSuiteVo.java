package com.toutoujinrong.vo;

import com.toutoujinrong.excel.ExcelColumn;


public class TestSuiteVo {
	@ExcelColumn(pos=0)
	private String no;
	
	@ExcelColumn(pos=1)
	private String testCaseName;
	
	@ExcelColumn(pos=2)
	private String des;

	@ExcelColumn(pos=3)
	private String methods;	
	
	@ExcelColumn(pos=4)
	private String enable;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}


}
