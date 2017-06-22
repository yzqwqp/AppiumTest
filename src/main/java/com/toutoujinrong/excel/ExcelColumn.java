package com.toutoujinrong.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解-Excel列
 * @author qiupeng
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
	/**
	 * 对于Excel文件中的列，列索引以0开始
	 * @return
	 */
	int pos() default 0;

	/**
	 * Excel中列对应的数据类型
	 * @return
	 */
	Class propTyep() default String.class;
	
	/**
	 * 日期格式使用
	 * @return
	 */
	String format() default "yyyyMMdd";

	/**
	 * 列的名称
	 * @return
	 */
	String name() default "";
	
	/**
	 * 是否非空
	 * @return
	 */
	boolean required() default false;
}
