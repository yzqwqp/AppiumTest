package com.toutoujinrong.vo;

import com.toutoujinrong.excel.ExcelColumn;



/**
 * TestCase的值对象模型
 * @author qiupeng
 *
 */
public class TestCaseVo {
	
	@ExcelColumn(pos=0)
	private String no;
	
	@ExcelColumn(pos=1)
	private String methodName;
	
	@ExcelColumn(pos=2)
	private String des;
	
	@ExcelColumn(pos=3)
	private String findByType;
	
	@ExcelColumn(pos=4)
	private String obj;
	
	@ExcelColumn(pos=5)
	private String function;
	
	@ExcelColumn(pos=6)
	private String value;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}


	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getFindByType() {
		return findByType;
	}

	public void setFindByType(String findByType) {
		this.findByType = findByType;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	
}
