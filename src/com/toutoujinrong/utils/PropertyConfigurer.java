package com.toutoujinrong.utils;


import org.apache.log4j.Logger;

import java.util.ResourceBundle;

/**
 * 作者: 冯灏
 * 创建时间: 16-2-18
 * 该类功能: 读取配置文件信息
 */
public class PropertyConfigurer {
    private static final Logger LOGGER = Logger.getLogger(PropertyConfigurer.class);

    private ResourceBundle bundle;

    public PropertyConfigurer(String filename){
        try {
            bundle = ResourceBundle.getBundle(filename);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}