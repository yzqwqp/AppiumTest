package com.toutoujinrong.utils;


/**
 * string工具类
 * @author qiupeng
 *
 */
public class StringUtils {
	
	/**
     * 判断字符串为空，或者空白字符或者Null
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
    	return org.apache.commons.lang3.StringUtils.isBlank(str);
    }
    
	/**
     * 判断字符串不为空，且不是空白字符，且不为Null
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }
    
    /**
     * 
     * 
     */
    public static String[] splitList(String str) {
    	return str.split(",");
    }
}
