package com.toutoujinrong.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotUtils {
	
	
	
	/*WebDriver 可以对浏览器中的页面进行截图*/
	public byte[] takeScreenshot(WebDriver driver) throws IOException {
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  return takesScreenshot.getScreenshotAs(OutputType.BYTES);
		}
	
	/*
	 *其中 ImageIO 和 BufferedImage 分别来自 javax.imageio 和 java.awt.image。

这种方法可以很好的削减截图的大小，也避免了一些不确定因素（比如日期时间等），是测试中保存截图的很好的方式。

注：如果测试的网页中含有 iframe，则需要使用更复杂的方式计算 WebElement 的位置，
	 * */
	
	public BufferedImage createElementImage(WebElement webElement, WebDriver driver)
		    throws IOException {
		  // 获得webElement的位置和大小。
		  Point location = webElement.getLocation();
		  Dimension size = webElement.getSize();
		  // 创建全屏截图。
		  BufferedImage originalImage =
		      ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
		  // 截取webElement所在位置的子图。
		  BufferedImage croppedImage = originalImage.getSubimage(
		      location.getX(),
		      location.getY(),
		      size.getWidth(),
		      size.getHeight());
		  return croppedImage;
		}
}
