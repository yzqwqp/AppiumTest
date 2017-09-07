package com.toutoujinrong.executor;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

import com.toutoujinrong.excel.ExcelData;
import com.toutoujinrong.utils.ConstantUtils;
import com.toutoujinrong.utils.LoggerUtils;
import com.toutoujinrong.utils.RedisUtils;
import com.toutoujinrong.utils.StringUtils;
import com.toutoujinrong.vo.TestCaseVo;
import com.toutoujinrong.vo.TestSuiteVo;

/**
 * appium执行类
 * @author qiupeng
 *
 */
public class AppiumExecutor {
	AppiumOperator ao;
	AndroidDriver  driver;
	private LoggerUtils logger =  LoggerUtils.getInstant();
	private String regCode;
	
	public AppiumExecutor(AppiumOperator appiumOperator) {
		ao = appiumOperator;
		this.driver = ao.getDriver();
	}
	
	public void executeSuite(ExcelData<TestSuiteVo> testSuite, ExcelData<TestCaseVo> testCase) throws Exception {
		if (testSuite == null) {
			logger.error("执行用例集时，testSuite 为空");
		} else {
			String[] methodList;
			for (TestSuiteVo l2 : testSuite.getResultList()) {
				//判断是否需要执行（enable = true）
				if (l2.getEnable().equals("1")) {
					//包含逗号
					if (l2.getMethods().indexOf(",") != -1) {
						methodList = l2.getMethods().split(",");
						for (int i=0; i < methodList.length; i++) {
							logger.info("*******【用例编号】 ：" + l2.getNo() + "******* 【用例名称：】  " + l2.getTestCaseName() + "******* 【方法用例"+ i+1 +"】：  " + methodList[i] + " 开始执行 !*******");
							executeCase(testCase, methodList[i]);
						}
					} else {
						logger.info("*******【用例编号】 ：" + l2.getNo() + "******* 【用例名称：】  " + l2.getTestCaseName() + "******* 【方法用例】：  " + l2.getMethods() + " 开始执行 !*******");
						executeCase(testCase, l2.getMethods());
					}
				}
			}
		}
	}
	
	
	public void executeCase(ExcelData<TestCaseVo> testCase, String methodName) throws Exception {
		if (testCase == null) {
			logger.error("执行用例时，testCase 为空");
		} else {
			int i;
			for (TestCaseVo l1 : testCase.getResultList()) {
				//根据传入的methodName执行testCase中的method
				if (l1.getMethodName().equals(methodName)) {
					for (i = 1; i < ConstantUtils.RETRY_FAIL_SHORT_COUNT + 1; i++) {
						try {
							executeFunction(l1);
							break;
//						} catch (FindElementException e) {
						} catch (Exception e) {
//							shotRetryFlag = true;
//							e.retryLog(logger);
							logger.info(l1.getNo() + " run failed " + i +  " times " );
							Thread.sleep(ConstantUtils.RETRY_SHORT_INTERVAL);
							continue;
						} 
//						catch (CheckElementException e) {
//							shotRetryFlag = true;
//							e.retryLog(logger);
//							Thread.sleep(ConstantUtils.RETRY_SHORT_INTERVAL);
//							continue;
//						}catch (Exception e){
//							throw new SeleniumException(xpath, jquery, this.type,e);
//						}
					}
					if(i == ConstantUtils.RETRY_SHORT_COUNT){
//						throw new MaxRetryException();
					}
					logger.info(l1.getNo() + " " +l1.getMethodName() + " " + l1.getDes() + "  run pass!");
				}
			} 
		}
	}
	
	
	public void executeFunction(TestCaseVo testCaseVo) {
		switch (testCaseVo.getFunction()) {
			case "click" :
				driver.findElement(by(testCaseVo.getFindByType(),testCaseVo.getObj())).click();
				break;
			case "input" :
				driver.findElement(by(testCaseVo.getFindByType(),testCaseVo.getObj())).click();
				driver.findElement(by(testCaseVo.getFindByType(),testCaseVo.getObj())).sendKeys(testCaseVo.getValue());;
				break;
			case "inputPwd" :
				String enabledFlag;
				do {
					driver.findElement(by(testCaseVo.getFindByType(),testCaseVo.getObj())).sendKeys(testCaseVo.getValue());
					enabledFlag = driver.findElement(by("By.id","com.toutouunion:id/alert_prompt_right_btn")).getAttribute("enabled");
				} while (enabledFlag.equals("false")) ;
//				((JavascriptExecutor)driver).executeScript("document.getElementById(\"em\").type ='text';");
			case "inputVerifyCode" :
				String[] verifyCode = StringUtils.splitList(testCaseVo.getValue());
				if (verifyCode == null || verifyCode.length == 0 || verifyCode[1] == null) {
					logger.info(testCaseVo.getNo() + " : " + testCaseVo.getDes() + " : " + "验证码校验类型出错!");
				} else {
					regCode = RedisUtils.getValue(verifyCode[0], verifyCode[1]);
					if (regCode != null || regCode.length() > 0) {
						System.out.println("error -0 ");
						logger.info(testCaseVo.getNo() + " : " + testCaseVo.getDes() + " : " + regCode);
						driver.findElement(by(testCaseVo.getFindByType(),testCaseVo.getObj())).sendKeys(regCode);
					} else {
						logger.info(testCaseVo.getNo() + " : " + testCaseVo.getDes() + " : " + regCode + "error");
					}
				} 
				logger.info("----------------------error");
				break;
			case "check" :
				//TODO，断言后续需要做
				break;
			//TODO
			default:
				break;
		}
		
		
//		l1.getFindByType(),l1.getObj(),l1.getFunction(),l1.getValue()
	}
	
	
	public By by(String bytype,String Object){
		By by=null;
		switch (bytype) {
		case "By.xpath":
			return By.xpath(Object);
		case "By.id":
			return By.id(Object);
		case "By.name":
			return By.name(Object);
		case "By.linkText":
			return By.linkText(Object);
		case "By.partialLinkText":
			return By.partialLinkText(Object);
		case "By.className":
			return By.className(Object);
		case "By.cssSelector":
			return By.cssSelector(Object);	
		default:
			return by;
		}
	}
}
