package com.toutoujinrong.excel;

import java.io.InputStream;
import java.util.List;

/**
 * Excel解析类反序列化用
 * @author qiupeng
 *
 */
public interface IExcelParser {
	/**
	 * 行
	 */
	public final static String EXCEL_ROW = "row";
	
	/**
	 *单元格 
	 */
	public final static String EXCEL_CELL = "c";
	
	/**
	 * 单元格类型
	 */
	public final static String EXCEL_CELL_TPYE = "t";
	
	/**
	 * 字符类型
	 */
	public final static String EXCEL_CELL_TPYE_STRING = "s";
	
	/**
	 * 单元格内容
	 */
	public final static String EXCEL_CELL_CONTENT = "v";
	
	public abstract List<List<String>> parse(InputStream is, String sheetName);
}
