package com.toutoujinrong.executor;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.toutoujinrong.utils.ConstantUtils;
import com.toutoujinrong.utils.LoggerUtils;



/**
 * appium操作对象类
 * @author qiupeng
 *
 */
public class AppiumOperator {
	private LoggerUtils logger =  LoggerUtils.getInstant();
	private static HashMap<Long, AppiumOperator> osMap = new HashMap<Long, AppiumOperator>();
	private Long dateTime;
	private String strDateTime;
	private AndroidDriver  driver;

	/**
	 * 获取当前线程
	 * @return
	 */
	public static AppiumOperator getInstance() {
		return osMap.get(Thread.currentThread().getId());
	}
	
	public AppiumOperator() {
		Long threadName = Thread.currentThread().getId();
		logger.info("Thread name is " + String.valueOf(threadName));
		
		if (osMap.get(threadName) == null)
			osMap.put(threadName, this);//TODO
		
		Date date = new Date();
		dateTime = date.getTime();
		strDateTime = String.valueOf(dateTime);
		
		//TODO log初始化
		//初始化appium
		initAppium();
	}
	
	private void initAppium() {
		//设置APK路径--如果这里不需要每次都安装apk的话，可以这里注释掉 
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "apps");
//		File app = new File(appDir, "TouTou_1.7.0.10.apk");
		
		//设置Android参数
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
//		platformName|要测试的手机操作系统|`iOS`, `Android`, 或 `FirefoxOS`|
		capabilities.setCapability("platformName", ConstantUtils.CAPABILITY_PLATFORMNAME);
//		手机操作系统版本
		capabilities.setCapability("platformVersion", ConstantUtils.CAPABILITY_PLATFORMVERSION);
//		deviceName|使用的手机类型或模拟器类型|`iPhone Simulator`, `iPad Simulator`,
//		`iPhone Retina 4-inch`, `Android Emulator`, `Galaxy S4`, 等。
//		在 iOS 上，这个关键字的值必须是使用 `instruments -s devices` 得到的可使用的设备名称之一。在 Android 上，这个关键字目前不起作用
		capabilities.setCapability("deviceName", ConstantUtils.CAPABILITY_DEVICENAME);//TODO
//		连接的物理设备的唯一设备标识
//		capabilities.setCapability("udid", "127.0.0.1:26944");
		capabilities.setCapability("udid", ConstantUtils.CAPABILITY_UDID);
		
//		装载apk
//		capabilities.setCapability("app", app.getAbsolutePath());
		
//		|appPackage|你想运行的Android应用的包名|比如`com.example.android.myApp`, `com.android.settings`|
		capabilities.setCapability("appPackage", "com.toutouunion");//TODO
//		|appActivity| 你要从你的应用包中启动的 Android Activity 名称。它通常需要在前面添加 `.` 
//		capabilities.setCapability("appActivity", "com.toutouunion.ui.HomeActivity");//TODO
		capabilities.setCapability("appActivity", "com.toutouunion.ui.welcome.GuideActivity");//TODO
		//com.lcfool.enjoy.activity
//		|unicodeKeyboard|使用 Unicode 输入法
//		capabilities.setCapability("unicodeKeyboard", "True");
//		|resetKeyboard|重置输入法到原有状态
//		capabilities.setCapability("resetKeyboard", "True");
		
		try {
			logger.info("Start a new driver");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(ConstantUtils.TIME_WAIT, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			logger.info("url not correct");
			e.printStackTrace();
		}
	}
	
	public AndroidDriver getDriver() {
		return driver;
	}

	public void setDriver(AndroidDriver driver) {
		this.driver = driver;
	}
}
