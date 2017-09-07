package com.toutoujinrong.utils;


/**
 * @author qiupeng
 *
 */
public class SystemUtils {
	public static void main(String[] args) {
	}
	
	/**
	 * 获取系统路径
	 * @return
	 */
	public static String getSysPath() {
		String path = System.getenv("cd");
		if (StringUtils.isNotBlank(path)) {
			return path+"/";
		}
		String ret = System.getProperty("user.dir").toString();
		ret = ret.replace("\\", "/") + "/";
		return ret;
	}
	
 
}
