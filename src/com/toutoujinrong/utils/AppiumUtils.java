package com.toutoujinrong.utils;


import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * demo 类，已弃用
 * @author qiupeng
 *
 */
public class AppiumUtils {
	private static AndroidDriver  driver;
	
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		//启动Android
		launchApp();
		
		/*
		Thread.sleep(1500);
		//向左滑屏3次
		for (int i=0; i<3; i++ ) {
			slideLeft();
			Thread.sleep(100);
		}
		
		
		//逛一下
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/guide_stroll_btn")).click();
		
		//便捷投资抓牛市--浮层
		Thread.sleep(1500);
		driver.findElement(By.id("com.toutouunion:id/tiro_guide_product_iv")).click();
		
		*/
		
		//切换环境
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/title_left_ibtn")).click();
		
		//点击灰度测试环境
		Thread.sleep(500);
		driver.findElement(By.name("http://114.141.132.227:9088/optt/munions")).click();
		
		//点击我
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/rl_me")).click();
		
		//点击来这里--浮层
//		Thread.sleep(500);
//		driver.findElement(By.id("com.toutouunion:id/tiro_guide_person_iv")).click();
		
		//登录
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/person_fragment_login_tv")).click();		
		
		//输入【手机号】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/login_username_tv")).click();
		driver.findElement(By.id("com.toutouunion:id/login_username_tv")).sendKeys("18115568455");
		
		//输入【密码】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/login_password_tv")).click();
		driver.findElement(By.id("com.toutouunion:id/login_password_tv")).sendKeys("qqqqqq");

		//点击【登录】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/login_confirm_btn")).click();
				
		
		
		
		/*
		//点击【立即体验】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/register_btn")).click();
		
		//点击【退回】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/title_left_ibtn")).click();
		
		//输入【手机号】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/login_username_tv")).click();
		driver.findElement(By.id("com.toutouunion:id/login_username_tv")).sendKeys("18115568455");
		
		//输入【密码】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/login_password_tv")).click();
		driver.findElement(By.id("com.toutouunion:id/login_password_tv")).sendKeys("qqqqqqqq");

		//点击【登录】
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/login_confirm_btn")).click();
		
		//
		Thread.sleep(500);
		driver.findElement(By.id("com.toutouunion:id/title_right_ibtn")).click();
		*/
		
		
		
	}
	
	
	
	/**
	 * 启动安卓设备
	 * 
	 * @throws MalformedURLException
	 */
	public static void launchApp() throws MalformedURLException {

		//设置APK路径--如果这里不需要每次都安装apk的话，可以这里注释掉 
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File appDir = new File(classpathRoot, "apps");
//		File app = new File(appDir, "TouTou_1.7.0.10.apk");
		
		//设置Android参数
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
//		platformName|要测试的手机操作系统|`iOS`, `Android`, 或 `FirefoxOS`|
		capabilities.setCapability("platformName", "Android");
//		手机操作系统版本
		capabilities.setCapability("platformVersion", "4.4.2");
//		deviceName|使用的手机类型或模拟器类型|`iPhone Simulator`, `iPad Simulator`,
//		`iPhone Retina 4-inch`, `Android Emulator`, `Galaxy S4`, 等。
//		在 iOS 上，这个关键字的值必须是使用 `instruments -s devices` 得到的可使用的设备名称之一。在 Android 上，这个关键字目前不起作用
		capabilities.setCapability("deviceName", "Android Emulator");//TODO
//		连接的物理设备的唯一设备标识
//		capabilities.setCapability("udid", "127.0.0.1:26944");
		capabilities.setCapability("udid", "HC48FZ000271");
		
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
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	/***
	* 左滑1/2屏幕
	*/
	public static void slideLeft(){
	int x=driver.manage().window().getSize().width;
	int y=driver.manage().window().getSize().height;
	driver.swipe(x/4*3, y/2, x/4*1, y/2, 0);
	}
	
	
	/**
	 * 启动appium
	 */
	public static void startAppuim(){
	   	 List<String> cmdLine = new ArrayList<String>(Arrays.asList(new String[] 
//	   			 {"cmd.exe", "/C", "start", "node", "D:/00-ProgramFiles/Appium/node_modules/appium", "--no-reset"}));  
	   	 {"cmd.exe", "/C", "start", "node", "D:/00-ProgramFiles/Appium/node_modules/appium", "--no-reset"}));  
	   	 //D:\00-ProgramFiles\Appium\Appium.exe
	        ProcessBuilder pb = new ProcessBuilder(cmdLine);  
	        try {  
	            pb.start();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	            Thread.sleep(1000);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println("node start");  
	   
	  }
}
