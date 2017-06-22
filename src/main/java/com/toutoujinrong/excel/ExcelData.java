package com.toutoujinrong.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelData<T> {
	private List<T> resultList = new ArrayList<T>();

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
}
