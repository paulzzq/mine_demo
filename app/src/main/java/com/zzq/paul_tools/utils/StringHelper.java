package com.zzq.paul_tools.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Steven.Li
 *
 */

/**
 * @author Steven.Li
 */
public final class StringHelper {

	public final static String STR_SEPARATOR = ",";
	public final static String STR_LINE_BREAK = "\n";

	public static final String merger(String s1, String s2, String defaultS2) {
		StringBuffer mSm = new StringBuffer();
		mSm.append(s1);
		mSm.append(": ");
		if (StringHelper.isText(s2)) {
			mSm.append(s2);
		} else {
			mSm.append(defaultS2);
		}
		return mSm.toString();
	}

	public static String hex2Str(byte[] diagnose) {
		// return new String(byteArr2, "UTF-8");
		try {
			return new String(diagnose, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "暂无诊断结论";
		}
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	final public static boolean isText(String text) {
		if (text == null || text.trim().length() == 0 || text.equals("")
				|| text.equals("null") || text.equals("NULL")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断用户是否输入
	 * 
	 * @param context
	 * @param text
	 * @return
	 */
	final public static String checkText(String text) {
		if (isText(text)) {
			return text;
		}
		return "";
	}

	/**
	 * 字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (!isText(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 去除最后的连接符号
	 * 
	 * @param source
	 * @param separator
	 * @return
	 */
	final public static String removeLastSeparator(String source,
			String separator) {
		if (isText(source)) {
			if (source.endsWith(separator)) {
				source = source.substring(0, source.length() - 1);
			}
		}
		return source;
	}

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	final public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":").replaceAll("（", "(")
				.replaceAll("）", ")").replaceAll("“", "\"")
				.replaceAll("”", "\"");// 替换中文标号
		String regEx = "[『』]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	// 半角转化为全角的方法
	final public static String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127 && c[i] > 32)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	// 全角转化为半角的方法
	final public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (isChinese(c[i])) {
				if (c[i] == 12288) {
					c[i] = (char) 32;
					continue;
				}
				if (c[i] > 65280 && c[i] < 65375)
					c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 判断是否为中文
	 * 
	 * @param c
	 * @return
	 */
	final private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是手机号码
	 * 
	 * @param text
	 * @return
	 */
	final public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
		// ^((13[0-9])|(15[^4,\D])|(18[0-9]))|(17[0-9]))\d{8}$

		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * bytes[]转换成Hex字符�?可用于URL转换，IP地址转换.
	 */
	final public static String bytesToHexDisplayString(byte[] bytes) {
		// http://stackoverflow.com/questions/332079
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			// sb.append("0x");
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);

			if (i < (bytes.length - 1)) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * bytes[]转换成Hex字符�?可用于URL转换，IP地址转换.
	 */
	final public static String bytesToHexString(byte[] bytes) {
		// http://stackoverflow.com/questions/332079
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	final private static int HEX_CHAR_TYPE_NUM = 0;
	final private static int HEX_CHAR_TYPE_AF = 1;
	final private static int HEX_CHAR_TYPE_af = 2;

	final public static int checkHexCharType(char hexChar) {
		int type = -1;

		do {
			if (('0' <= hexChar) && ('9' >= hexChar)) {
				type = HEX_CHAR_TYPE_NUM;
				break;
			}

			if (('A' <= hexChar) && ('F' >= hexChar)) {
				type = HEX_CHAR_TYPE_AF;
				break;
			}

			if (('a' <= hexChar) && ('f' >= hexChar)) {
				type = HEX_CHAR_TYPE_af;
				break;
			}
		} while (false);

		return type;
	}

	final public static boolean checkIsHexString(char hexChar) {
		boolean bRet = false;

		if (0 <= checkHexCharType(hexChar)) {
			bRet = true;
		}

		return bRet;
	}

	/**
	 * Hex字符转byte
	 */
	final public static byte hexString2Byte(char hChar, char lChar) {
		boolean bRst = true;
		byte value = 0;

		do {
			switch (checkHexCharType(hChar)) {
			case HEX_CHAR_TYPE_NUM:
				value = (byte) (hChar - '0');
				break;
			case HEX_CHAR_TYPE_AF:
				value = (byte) (hChar - 'A');
				value += 10;
				break;
			case HEX_CHAR_TYPE_af:
				value = (byte) (hChar - 'a');
				value += 10;
				break;
			default:
				bRst = false;
				break;
			}
			if (!bRst) {
				break;
			}
			value = (byte) (value << 4);
			switch (checkHexCharType(lChar)) {
			case HEX_CHAR_TYPE_NUM:
				value += (byte) (lChar - '0');
				break;
			case HEX_CHAR_TYPE_AF:
				value += (byte) (lChar - 'A');
				value += 10;
				break;
			case HEX_CHAR_TYPE_af:
				value += (byte) (lChar - 'a');
				value += 10;
				break;
			default:
				bRst = false;
				break;
			}
			if (!bRst) {
				value = 0;
				break;
			}
		} while (false);

		return value;
	}

	/**
	 * Hex字符串转bytes[]
	 */
	final public static byte[] hexString2bytes(String hexStr) {
		byte[] data = null;
		int length;
		int i, j;
		do {
			if (null == hexStr) {
				break;
			}
			length = hexStr.length();
			if (1 >= length) {
				break;
			}

			if (0 != (length % 2)) {
				break;
			}
			data = new byte[length / 2];

			for (i = 0, j = 0; i < length;) {
				if ((!checkIsHexString(hexStr.charAt(i)))
						|| (!checkIsHexString(hexStr.charAt(i + 1)))) {
					break;
				}
				data[j++] = hexString2Byte(hexStr.charAt(i),
						hexStr.charAt(i + 1));
				i += 2;
			}
			if (i != length) {
				data = null;
			}
		} while (false);

		return data;
	}

	/**
	 * Hex字符串转bytes[]
	 */
	final public static byte[] hexStringSingalDot2bytes(String hexStr) {
		byte[] data = null;
		byte[] retData = null;
		char hChar, lChar;
		int length;
		int i = 0;
		int j = 0;
		do {
			if (null == hexStr) {
				break;
			}
			length = hexStr.length();
			if (1 >= length) {
				break;
			}
			// if(0 != (length%2))
			// {
			// break;
			// }
			data = new byte[length];

			for (i = 0, j = 0; i < length;) {
				if (!checkIsHexString(hexStr.charAt(i))) {
					i++;
					continue;
				}
				hChar = hexStr.charAt(i);
				i++;

				if (!checkIsHexString(hexStr.charAt(i))) {
					lChar = hChar;
					hChar = '0';
				} else {
					lChar = hexStr.charAt(i);
				}
				i++;
				data[j++] = hexString2Byte(hChar, lChar);
			}
			if (i != length) {
				data = null;
			}
		} while (false);

		if ((null != data) && (0 < j)) {
			retData = new byte[j];
			System.arraycopy(data, 0, retData, 0, j);
		}
		return data;
	}

	final public static String stringToStringSHA_1(String str) {
		String cacheKey = str;
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA-1");
			mDigest.update(str.getBytes());
			cacheKey = StringHelper.bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return cacheKey;
	}

	final public static String prettyBytes(long value) {

		String args[] = { "B", "KB", "MB", "GB", "TB" };
		StringBuilder sb = new StringBuilder();
		int i;
		if (value < 1024L) {
			sb.append(String.valueOf(value));
			i = 0;
		} else if (value < 1048576L) {
			sb.append(String.format("%.1f", value / 1024.0));
			i = 1;
		} else if (value < 1073741824L) {
			sb.append(String.format("%.2f", value / 1048576.0));
			i = 2;
		} else if (value < 1099511627776L) {
			sb.append(String.format("%.3f", value / 1073741824.0));
			i = 3;
		} else {
			sb.append(String.format("%.4f", value / 1099511627776.0));
			i = 4;
		}
		sb.append(' ');
		sb.append(args[i]);
		return sb.toString();
	}

	/**
	 * 将Excepiton信息转换成String字符�?
	 */
	final public static String exceptionToString(Throwable t) {
		if (t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			t.printStackTrace(new PrintStream(baos));
		} finally {
			try {
				baos.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return baos.toString();
	}

	final public static String getPriceSpread(String price1, String price2) {

		try {
			Float saveMoney = Float.valueOf(price1) - Float.valueOf(price2);
			saveMoney = Math.abs(saveMoney);
			return new DecimalFormat("###0.00").format(saveMoney);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "0.00";
		}
	}

	final public static String formatDouble(double d) {
		return new DecimalFormat("###0.00").format(d);
	}

	final public static int string2Int(String string) {
		int result = 0;
		try {
			result = Integer.valueOf(string);
		} catch (NumberFormatException e) {
			result = -1;
			e.printStackTrace();
		}
		return result;
	}

	final public static String int2String(int value) {
		String result = null;
		try {
			result = String.valueOf(value);
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}

		return result;
	}

	final public static boolean idDouble(String value) {
		boolean flag = false;
		try {
			Double.parseDouble(value);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}

	final public static StringBuilder removeLastSeparator(StringBuilder source,
			String separator) {
		int pos = source.lastIndexOf(separator);
		if (pos != -1)
			return source.delete(pos, source.length());
		return source;
	}

	final public static String[] getCheckCode(CheckBox[] cbs, EditText[] ets) {
		StringBuilder ctStr = new StringBuilder();
		StringBuilder etStr = new StringBuilder();
		String[] results = new String[2];
		for (int i = 0; i < cbs.length; i++) {
			CheckBox cb = cbs[i];
			EditText et = ets[i];
			if (cb.isChecked()) {
				ctStr.append(cb.getTag()).append(STR_SEPARATOR);
				etStr.append(et.getText().toString()).append(STR_SEPARATOR);
			}
		}
		if (isText(ctStr.toString())) {
			ctStr = removeLastSeparator(ctStr, STR_SEPARATOR);
		}
		if (isText(etStr.toString())) {
			etStr = removeLastSeparator(etStr, STR_SEPARATOR);
		}
		results[0] = ctStr.toString();
		results[1] = etStr.toString();
		return results;

	}

	/**
	 * 按args匹配是不是32位数据库中的uuid值
	 */
	public static boolean isUUID(String args) {
		if (!isText(args)) {
			return false;
		}
		String reg = "[0-9a-z]{32}";
		boolean flag = args.matches(reg);
		return flag;
	}

	final public static int generateAge(String birthday) {
		if (!isText(birthday))
			return 0;
		Date birthdate;
		int age = 0;
		try {
			birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			Calendar currentDay = Calendar.getInstance();
			currentDay.setTime(birthdate);
			int year = currentDay.get(Calendar.YEAR);
			// 获取年龄
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
			String years = simpleDateFormat.format(new Date());
			age = Integer.parseInt(years) - year;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}

	public static String generateVipCardId() {
		return "888088880000";
	}

	public static int getBatteryInfo(Context context) {
		int battery = 0;
		Intent batteryInfoIntent = context.getApplicationContext()
				.registerReceiver(null,
						new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		if (Intent.ACTION_BATTERY_CHANGED.equals(batteryInfoIntent.getAction())) {
			// 获取当前电量
			int level = batteryInfoIntent.getIntExtra("level", 0);
			// 电量的总刻度
			int scale = batteryInfoIntent.getIntExtra("scale", 100);
			battery = ((level * 100) / scale);
			System.out.println(battery + " 电池用量");
		}

		return battery;
	}

	public static String fromArrayAsString(String[] array, String strIndex) {
		String content = "";
		if (isText(strIndex) && isNumeric(strIndex)) {
			int index = Integer.parseInt(strIndex);
			if (array != null && array.length > 0 && index < array.length) {
				content = array[index];
				return content;
			}
		}
		return content;
	}

	/**
	 * 获取系统版本号
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName(Context context) throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(
				context.getPackageName(), 0);
		String version = packInfo.versionName;
		return version;
	}

	private static long lastClickTime;
	private static long PeriodTime = 800;

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < PeriodTime) {
			return true;
		}
		lastClickTime = time;
		return false;
	}





	public static String heartRate(String heartRateString) {
		int i = Integer.valueOf(heartRateString);
		if (i >= 60 && i <= 100) {
			heartRateString = "您本次监测心率为" + i + "，心率正常 。正常成年人心率范围60~100";
		} else if (i > 100) {
			heartRateString = "您本次监测心率为" + i + "，心率偏高 。正常成年人心率范围60~100";
		} else if (i < 60) {
			heartRateString = "您本次监测心率为" + i + "，心率偏低 。正常成年人心率范围60~100";
		}
		return heartRateString;
	}

	/*
	 * 功能：多行横排文本转换为多列直排（以换行符 \n 作为断行标记） 说明：可支持1～N行，但要求有换行标记符，或稍作修改以标点符号换行
	 * 
	 * @author: 云冰工作室 yunbing.com
	 * 
	 * @param: strText
	 * 
	 * @return: 返回值为行列转置后的多行文本
	 */
	public static String getTextHtoV(String strText) {
		String strResult = "";
		String br = "\n"; // 断行标记，这里可改用逗号或分号等字符
		String strArr[] = strText.split(br);
		int nMaxLen = 0; // 各行最多字符数
		int nLines = strArr.length; // 总共的行数
		char charArr[][] = new char[nLines][]; // 字符陈列（即二维数组）
		for (int i = 0; i < nLines; i++) {
			String str = strArr[i];
			int n = str.length();

			// 以最长的行的字符数（即原列数）作为目标陈列的行数
			if (n > nMaxLen)
				nMaxLen = n;
			charArr[i] = strArr[i].toCharArray();
		}

		// 行列转换
		for (int i = 0; i < nMaxLen; i++) {
			for (int j = 0; j < nLines; j++) {
				// 若短句字符已“用完”则以空格代替
				char c = i < charArr[j].length ? charArr[j][i] : '　';
				strResult += String.valueOf(c);

				// 两列文字之间加空格，不需要的话请注释掉下面一行
				if (j < nLines - 1)
					strResult += " "; // ★
			}
			strResult += br; // 添加换行符
		}

		return strResult;
	}

	/**
	 * 字符串保持两位 小数
	 * 
	 * @param str
	 * @return
	 */
	public static String keepTwoDecimal(String str) {
		BigDecimal value = new BigDecimal(str);
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return value.toString();
	}

	/**
	 * 字符串保持两位 小数
	 * 
	 * @param str
	 * @return
	 */
	public static String keepOneDecimal(String str) {
		BigDecimal value = new BigDecimal(str);
		value = value.setScale(1, BigDecimal.ROUND_HALF_UP);
		return value.toString();
	}

	public static String keepZeroDecimal(String str) {
		BigDecimal value = new BigDecimal(str);
		value = value.setScale(0, BigDecimal.ROUND_HALF_UP);
		return value.toString();
	}

	/**
	 * 获取当前的时间格式 MM月dd日
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
		Date date = new Date(time);
		return format.format(date);
	}

	/**
	 * 获取当前时间格式 2016-08-26
	 * 
	 * @return
	 */
	public static String getCurrentTimeYear() {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(time);
		return format.format(date);
	}

	/**
	 * 获取当前时间格式 yyyy年MM月dd日 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurTime1(long time) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy年MM月dd日    HH:mm:ss");
		Date date = new Date(time);
		return format.format(date);
	}

	/**
	 * 获取当前时间格式 2016-08-26
	 * 
	 * @return
	 */
	public static String getCurTime2(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return format.format(date);
	}

	public static String getCurTime3(long time) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy年MM月dd日    HH:mm:ss");
		Date date = new Date(time);
		return format.format(date);
	}

	/**
	 * 时间戳转毫秒数 年-月-日
	 */
	public static long timeToMSecond(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long millionSeconds = 0;
		try {
			millionSeconds = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millionSeconds;
	}

	/**
	 * 以这种格式 获取时间错的毫秒数 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static long timeToMSecond2(String str) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long millionSeconds = 0;
		try {
			millionSeconds = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millionSeconds;
	}

	/**
	 * 打印过长的日志
	 * 
	 * @param teststr
	 */
	public static void printLog(String teststr, String fileName) {
		String sd = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/AlOG/";
		Log.i("aaa", "来了2");
		File file = new File(sd);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			File file2 = new File(sd + "/" + fileName + ".txt");
			if (file2.exists()) {
				file2.delete();
			}
			file2.createNewFile();
			FileOutputStream fos = new FileOutputStream(sd + "/" + fileName
					+ ".txt", true);
			fos.write(teststr.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单次写入数据到指定文件
	 * @param teststr
	 * @param fileName
	 */
	public static void printByteLog(byte[] teststr, String fileName) {
		String sd = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/AlOG/";
		Log.i("aaa", "来了2");
		File file = new File(sd);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			File file2 = new File(sd + "/" + fileName + ".txt");
			if (file2.exists()) {
				file2.delete();
			}else{
				file2.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(sd + "/" + fileName
					+ ".txt", true);
			fos.write(teststr);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多次写入数据到指定文件
	 * @param teststr
	 * @param fileName
	 */
	public static void printByteLog2(byte[] teststr, String fileName) {
		String sd = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/AlOG/";
		FileOutputStream fos=null;
		File file = new File(sd);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			File file2 = new File(sd + "/" + fileName + ".txt");
			if (file2.exists()) {
				fos = new FileOutputStream(sd + "/" + fileName
						+ ".txt", true);
			}else{
				file2.createNewFile();
				fos = new FileOutputStream(sd + "/" + fileName
						+ ".txt", true);
			}
			fos.write(teststr);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以这种格式 获取时间错的毫秒数 yyyy-MM-dd HH:mm
	 * 
	 * @param str
	 * @return
	 */
	public static long timeToMSecond3(String str) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long millionSeconds = 0;
		try {
			millionSeconds = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millionSeconds;
	}

	/**
	 * 将file转化成string
	 * 
	 * @param filePath
	 * @return
	 */
//	public static String fileToString(String filePath) {
//		byte[] buffer = null;
//		String str = null;
//		try {
//			File file = new File(filePath);
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			byte[] b = new byte[1024];
//			int n;
//			while ((n = fis.read(b)) != -1) {
//				bos.write(b, 0, n);
//			}
//			fis.close();
//			bos.close();
//			buffer = bos.toByteArray();
//			str = URLEncoder.encode(Base64.encode(buffer), "UTF-8");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return str;
//	}

	/**
	 * 获取当前时间格式 2016-08-26
	 * 
	 * @return
	 */
	public static String getTimeYear(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(Long.valueOf(time));
		return format.format(date);
	}

	public static boolean isVoid(Object obj) {// 判断是否为空
		return null == obj || obj.equals("");
	}

	/*
	 * 毫秒转BCD码
	 */
	public final static byte[] mills2BCDDateTime(long mills) {
		int i = 0;
		int value;
		byte[] dateTime = new byte[6];
		Calendar cal = mills2Calendar(mills);

		value = cal.get(Calendar.YEAR);
		dateTime[i++] = (byte) ((((value - 2000) / 10) << 4) + (value - 2000) % 10);
		value = cal.get(Calendar.MONTH);
		dateTime[i++] = (byte) ((((value + 1) / 10) << 4) + ((value + 1) % 10));
		value = cal.get(Calendar.DAY_OF_MONTH);
		dateTime[i++] = (byte) (((value / 10) << 4) + (value % 10));
		value = cal.get(Calendar.HOUR_OF_DAY);
		dateTime[i++] = (byte) (((value / 10) << 4) + (value % 10));
		value = cal.get(Calendar.MINUTE);
		dateTime[i++] = (byte) (((value / 10) << 4) + (value % 10));
		value = cal.get(Calendar.SECOND);
		dateTime[i++] = (byte) (((value / 10) << 4) + (value % 10));

		return dateTime;
	}

	public static Calendar mills2Calendar(long mills) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return calendar;
	}

}
