package com.toutoujinrong.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.testng.internal.PropertyUtils;

import com.toutoujinrong.excel.ExcelColumn;
import com.toutoujinrong.excel.ExcelData;
import com.toutoujinrong.excel.ExcelParser;
import com.toutoujinrong.excel.IExcelParser;
import com.toutoujinrong.vo.TestCaseVo;


/**
 * Excel工具类，read方法返回testCase对象模型
 * @author qiupeng
 *
 */
public class ExcelUtils {
	
	public static void main(String args[]) throws IOException {
		String str = "D:\\test.xlsx";
		InputStream in = new FileInputStream(new File(str));
		ExcelData<TestCaseVo> e = ExcelUtils.read(in, "Sheet1", TestCaseVo.class);
		List<TestCaseVo> list = e.getResultList();
		for (TestCaseVo l1 : list) {
			System.out.println("-------------" + "\t");
			System.out.println(l1.getNo() + l1.getFunction());
		}
		
	}
	
	
	/**
	 * @param is
	 * @param sheetName
	 * @param type
	 * @return
	 */
	public static <T> ExcelData<T> read(InputStream is, String sheetName, final Class<T> type) {
		final ExcelData<T> dataResult = new ExcelData<T>();
		List<List<String>> excelData = null;
		try {
			IExcelParser parser = new ExcelParser();
			excelData = parser.parse(is, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int len = excelData.size();
		for (int row = 1; row < len; row++) {
			List<String> rowData = excelData.get(row);
			T obj = null;
			try {
				obj = type.newInstance();
			} catch (Exception e) {
				System.out.println("解析Excel时，实例化模型类失败");
				e.printStackTrace();
			}
			boolean isAllBlank = true;//整行为空标识
			for (final Field field : type.getDeclaredFields()) {//反射得到数组的值
				try {
					final ExcelColumn e = field.getAnnotation(ExcelColumn.class);
					if (e != null) {
						String content = rowData.get(e.pos());
						if (StringUtils.isNotBlank(content)) {
							parseElement(e, obj, field.getName(), content);
							isAllBlank = false;
						}
					}
				} catch (final Exception e) {
					System.out.println("填充数据时出错");
					e.printStackTrace();
				}
				if (isAllBlank) {
					continue;
				}
			}
			dataResult.getResultList().add(obj);
		}
		return dataResult;
	}
	
	
	private static <T> void parseElement(final ExcelColumn e, Object obj, String fieldName, String content) throws Exception {
		
		if (e != null) {
			if (StringUtils.isBlank(content)) {
				if (e.required()) {
					System.out.println(String.format("%s不能为空", e.name()));
				}
				return;
			}
			PropertyUtils.setProperty(obj, fieldName, content.trim());
		}
	}
	
	/**
     * 根据注解，将字符串转换为不同的数据类型格式
     *
     * @param content
     * @param metaColumn
     * @return
     * @throws Exception
     */
	private static Object convertContent(String content, ExcelColumn metaColumn) throws Exception {
		Class propType = metaColumn.propTyep();
		if (StringUtils.isBlank(content)) {
			return null;
		}
		if (Boolean.class.equals(propType)) {
			return Boolean.valueOf(content);
		} 
		else {
            throw new Exception("数据类型不支持:" + propType);
        }
	}
}
