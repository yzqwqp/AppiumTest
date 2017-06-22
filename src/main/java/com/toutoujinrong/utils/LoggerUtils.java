package com.toutoujinrong.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;


/**
 * 日志类
 * @author qiupeng
 */
public class LoggerUtils{
//	private static final String NO = " [ STEP:";
//	private static final String NO2 = " \t] ";
	private Logger logger;
	private ArrayList<String> messageHeap = new ArrayList<String>();
	private static HashMap<String, LoggerUtils> logMap = new HashMap<String, LoggerUtils>();
	private String threadid;
	
	public static LoggerUtils getInstant(){
		String logkey = String.valueOf(Thread.currentThread().getId());
		LoggerUtils ml = logMap.get(logkey);
		if(ml==null){
			ml=new LoggerUtils();
			ml.logger = Logger.getLogger("Operator_"+logkey);
			ml.threadid=logkey;
			logMap.put(logkey, ml);
		}
		return ml;
	}
	/**
	 * @param name
	 */
	private String parseMessage(Object message){
		//return "[Thread"+threadid+"]("+new Date().getTime()+")  "+message;
//		return "[Thread - "+threadid+"] [\t\t] "+message;
		return "[Thread - " + threadid + "]  "+message;
	}
	
	
	public void error(Object message,Throwable t){
		String me = parseMessage(message);
		insertIntoHeap("[error] "+me);
		logger.error(me);
	}
	
	public void info(Object message,Throwable t){
		String me = parseMessage(message);
		insertIntoHeap("[info] "+me);
		logger.info(me);
	}
	
	
	public void info(Object message) {
		String me = parseMessage(message);
		logger.info(me);
		insertIntoHeap("[info] "+me);
	}
	
	public void warn(Object message) {
		String me = parseMessage(message);
		logger.warn(me);
		insertIntoHeap("[warn] "+me);
	}
	
	public void error(Object message){
		String me = parseMessage(message);
		logger.error(me);
		insertIntoHeap("[error] "+me);
	}
	
	private void insertIntoHeap(String message){
		messageHeap.add(message);
	}
	
	public ArrayList<String> getMessageHeap() {
		return messageHeap;
	}
}
