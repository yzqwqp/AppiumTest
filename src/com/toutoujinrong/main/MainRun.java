package com.toutoujinrong.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.toutoujinrong.excel.ExcelData;
import com.toutoujinrong.executor.AppiumExecutor;
import com.toutoujinrong.executor.AppiumOperator;
import com.toutoujinrong.utils.AppiumUtils;
import com.toutoujinrong.utils.ConstantUtils;
import com.toutoujinrong.utils.ExcelUtils;
import com.toutoujinrong.vo.TestCaseVo;
import com.toutoujinrong.vo.TestSuiteVo;




/**
 * 自动化执行主方法
 * @author qiupeng
 *
 */
public class MainRun {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		AppiumUtils.startAppuim();
		String str = "D:\\TestSuite.xlsx";
		InputStream in = new FileInputStream(new File(str));
		InputStream in2 = new FileInputStream(new File(str));
		
		ExcelData<TestSuiteVo> testSuite = ExcelUtils.read(in, "TestSuites", TestSuiteVo.class);
		ExcelData<TestCaseVo> testCase = ExcelUtils.read(in2, "PublicMethods", TestCaseVo.class);
		AppiumOperator a = new AppiumOperator();
		AppiumExecutor b = new AppiumExecutor(a);
		b.executeSuite(testSuite, testCase);
	}
}
