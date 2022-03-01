package com.zzq.paul_tools.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.text.TextUtils;
import android.util.Log;

/**
 * 
 * @Title: EgretLog.java 
 * @Package com.alibaba.ecgdoctor.utils 
 * @Description: 日志工具类
 * @Author Egret
 * @Email:906932256@qq.com
 * @Date 2015-5-22 上午10:30:54 
 * @Version V1.0
 */
public class EgretLog {


	/**
	 * 日志总开关
	 */
	public static boolean SHOW_LOG_FLAG = true;
	/**
	 * DEBUG日志开关
	 */
	private static boolean DEBUG = true;
	/**
	 * INFO日志开关
	 */
	private static boolean INFO = true;
	/**
	 * WARN日志开关
	 */
	private static boolean WARN = true;
	/**
	 * ERROR日志开关
	 */
	private static boolean ERROR = true;
	
	private EgretLog() {
	}
	private static EgretLog logUtils = null;
	/**
	 * 
	 * @Title: getEcgCtroller 
	 * @Description: 获取EcgCtroller唯一实例
	 * @param @param 上下文Activity
	 * @param @param 用户唯一id
	 * @param @return    方法参数 
	 * @return EcgCtroller    返回类型 
	 * @throws
	 */
	public static EgretLog getInstance(){
		if (logUtils == null) {
			logUtils = new EgretLog();
		}
		return logUtils;
	}
	 

	public void d(Class<?> clazz,String logInfo) {
		print(Log.DEBUG, clazz.getSimpleName(),logInfo);
	}
	public void i(Class<?> clazz,String logInfo) {
		print(Log.INFO, clazz.getSimpleName(),logInfo);
	}
	public void w(Class<?> clazz,String logInfo) {
		print(Log.WARN, clazz.getSimpleName(),logInfo);
	}
	public void e(Class<?> clazz,String logInfo) {
		print(Log.ERROR, clazz.getSimpleName(),logInfo);
	}
	/**
	 * 用于区分不同接口数据 打印传入参数
	 * @param index 
	 * @param str
	 */
	private void print(int index,String mClassName,String logInfo) {
		if (!SHOW_LOG_FLAG) {
			return;
		}
		if (!TextUtils.isEmpty(logInfo)) {
			try {
				logInfo = logInfo.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
				logInfo = URLDecoder.decode(logInfo, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			logInfo = "返回值为空...";
		}
		String name = getFunctionName();
		if (name != null) {
			logInfo = name + " - " + logInfo;
		}
		
		//Close the debug log When DEBUG is false 
		if(!DEBUG){
			if(index<=Log.DEBUG){
				return;
			}
		}
		//Close the info log When INFO is false 
		if(!INFO){
			if(index<=Log.INFO){
				return;
			}
		}
		//Close the warn log When WARN is false 
		if(!WARN){
			if(index<=Log.WARN){
				return;
			}
		}
		//Close the error log When ERROR is false 
		if(!ERROR){
			if(index<=Log.ERROR){
				return;
			}
		}
		switch (index) {
		case Log.VERBOSE:
			Log.v(mClassName, logInfo.toString());
			break;
		case Log.DEBUG:
			Log.d(mClassName, logInfo.toString());
			break;
		case Log.INFO:
			Log.i(mClassName, logInfo.toString());
			break;
		case Log.WARN:
			Log.w(mClassName, logInfo.toString());
			break;
		case Log.ERROR:
			Log.e(mClassName, logInfo.toString());
			break;
		default:
			break;
		}
	}
 
	/**
	 * Get The Current Function Name
	 * 
	 * @return Name
	 */
	private String getFunctionName() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		if (sts == null) {
			return null;
		}
		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}
			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}
			return "[ " + Thread.currentThread().getName() + ": "
					+ st.getFileName() + ":" + st.getLineNumber() + " "
					+ st.getMethodName() + " ]";
		}
		return null;
	}


}
