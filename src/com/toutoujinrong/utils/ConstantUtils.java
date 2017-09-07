package com.toutoujinrong.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 常量类
 * @author qiupeng
 *
 */
public class ConstantUtils {
	
//	public static final String TEST_SUITE_PATH	= System.getProperty("user.dir")+"\\excel\\TestSuite\\TestSuite.xlsx";
//	public static final String TEST_CASE_PATH	= System.getProperty("user.dir")+"\\excel\\TestCase\\TestCase.xlsx";
//	public static final String TEST_DATA_PATH	= System.getProperty("user.dir")+"\\excel\\TestData\\TestData.xlsx";
	
	public static final String REDIS_KEYSPILT 	= ":";
	public static final String REDIS_INSTID 	= "LT0000001";
	public static final String REDIS_TYPE_REGISTER 	= "1";
	public static final String REDIS_TYPE_SETPWD 	= "2";
	public static final String REDIS_TYPE_FINDPWD 	= "3";
	
	public static final String PATH_SYS = SystemUtils.getSysPath();
	public static final String PATH_CONFIG_PROPERTIES = PATH_SYS + "config/properties/";

//	public static Properties prop = getProperties2();
	
	/*重试的次数和间隔*/
	public static final int RETRY_FAIL_SHORT_COUNT = Integer.valueOf(getProperties("retryFailShortCount"));
	public static final int RETRY_SHORT_COUNT = Integer.valueOf(getProperties("retryShortCount"));
	public static final int RETRY_SHORT_INTERVAL = Integer.valueOf(getProperties("retryShortInterval"));
	public static final int RETRY_LONG_COUNT = Integer.valueOf(getProperties("retryLongCount"));
	public static final int RETRY_LONG_INTERVAL = Integer.valueOf(getProperties("retryLongInterval"));
	
//	public static final int RETRY_FAIL_SHORT_COUNT = Integer.valueOf(prop.getProperty("retryFailShortCount",null));
//	public static final int RETRY_SHORT_COUNT = Integer.valueOf(prop.getProperty("retryShortCount",null));
//	public static final int RETRY_SHORT_INTERVAL = Integer.valueOf(prop.getProperty("retryShortInterval",null));
//	public static final int RETRY_LONG_COUNT = Integer.valueOf(prop.getProperty("retryLongCount",null));
//	public static final int RETRY_LONG_INTERVAL = Integer.valueOf(prop.getProperty("retryLongInterval",null));
//	
//	//单步最长等待
	public static final int TIME_WAIT = Integer.valueOf(getProperties("timeWait"));//prop.getProperty("timeWait",null));
	public static final Integer stepInterval = Integer.valueOf(getProperties("StepInterval"));//prop.getProperty("StepInterval", null));
//	public static final int TIME_WAIT = Integer.valueOf(prop.getProperty("timeWait",null));
//	public static final Integer stepInterval = Integer.valueOf(prop.getProperty("StepInterval", null));
//	
//	//Android模拟器配置
	public static final String CAPABILITY_PLATFORMNAME = getProperties("capabilityPlatformName");//String.valueOf(prop.getProperty("capabilityPlatformName",null));
	public static final String CAPABILITY_PLATFORMVERSION = getProperties("capabilityPlatformVersion");//String.valueOf(prop.getProperty("capabilityPlatformVersion",null));
	public static final String CAPABILITY_DEVICENAME = getProperties("capabilityDeviceName");//String.valueOf(prop.getProperty("capabilityDeviceName",null));
	public static final String CAPABILITY_UDID = getProperties("capabilityUdid");
//	public static final String CAPABILITY_PLATFORMNAME = String.valueOf(prop.getProperty("capabilityPlatformName",null));
//	public static final String CAPABILITY_PLATFORMVERSION = String.valueOf(prop.getProperty("capabilityPlatformVersion",null));
//	public static final String CAPABILITY_DEVICENAME = String.valueOf(prop.getProperty("capabilityDeviceName",null));
//	public static final String CAPABILITY_UDID = String.valueOf(prop.getProperty("capabilityUdid",null));

	
	
	
	/*public static Properties getProperties2() {
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(PATH_CONFIG_PROPERTIES + "prop.properties");
			prop.load(file);
			file.close();
		} catch (Exception e) {
			System.out.println(PATH_CONFIG_PROPERTIES);
			System.out.println("prop.properties  招不到");
			e.printStackTrace();
		}
		return prop;
	}*/
	
	public static String getProperties(String str) {
		PropertyConfigurer getConfiguere = new PropertyConfigurer("prop");
	    return getConfiguere.getValue(str);
		
	}
	
	

	
}
