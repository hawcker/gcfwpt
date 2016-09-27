package com.gcfwpt.designonline.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonTools {

	/** 获取当前apk包名 */
	public static String getPackageName(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info != null) {
			return info.packageName;  //得到安装包名称
		}
		return null;
	}

	/**
	 * 身份证号码正则表达式
	 */
	public static boolean isIDCard(String idNum){
		//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
//        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
//        //通过Pattern获得Matcher
//        Matcher idNumMatcher = idNumPattern.matcher(idNum);
//         return idNumMatcher.matches();
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (idNum.length() != 15 && idNum.length() != 18) {
			return false;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (idNum.length() == 18) {
			Ai = idNum.substring(0, 17);
		} else if (idNum.length() == 15) {
			Ai = idNum.substring(0, 6) + "19" + idNum.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			return false;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
			return false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                return false;
            }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			return false;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			return false;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			return false;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (idNum.length() == 18) {
			if (Ai.equals(idNum) == false) {
				return false;
			}
		} else {
			return false;
		}
		return true;

	}


	/**
	 * 功能：判断字符串是否为数字
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：设置地区编码
	 * @return Hashtable 对象
	 */
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 金钱对比大小
	 */

//	public static int compareMoney(String a,String b){
//		String[] arra = a.split(".");
//		LogUtil.i("mt","arra[0]:"+arra[0]);
//		LogUtil.i("mt","arra[1]"+arra[1]);
//		double tixian = Double.parseDouble(b);
//		String[] arrb = String.valueOf(tixian).split(".");
//		LogUtil.i("mt","arrb[0]:"+arrb[0]);
//		LogUtil.i("mt","arrb[1]"+arrb[1]);
//		// 余额中小数点前
//		int c = Integer.parseInt(arra[0]);
//		LogUtil.i("mt",c+"");
//		// 余额中小数点后
//		int d = Integer.parseInt(arra[1]);
//		LogUtil.i("mt",d+"");
//		if(d < 10){
//			d = Integer.parseInt(arra[1].substring(1,2));
//			LogUtil.i("mt","新d:"+d+"");
//		}
//
//		// 提现中小数点前
//		int e = Integer.parseInt(arra[0]);
//		LogUtil.i("mt",e+"");
//		// 提现中小数点后
//		int f = Integer.parseInt(arra[1]);
//		LogUtil.i("mt",f+"");
//		if(f < 10){
//			f = Integer.parseInt(arra[1].substring(1,2));
//			LogUtil.i("mt","新f:"+f+"");
//		}
//
//		// 开始对比
//		if(e <= c && f <= d){
//			// 可以提现
//			LogUtil.i("mt","可以提现");
//			return 1;
//		}
//		return 0;
//	}
	/**验证日期字符串是否是YYYY-MM-DD格式
	 * @param str
	 * @return
	 */
	public static boolean isDataFormat(String str){
		boolean flag=false;
		//String regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
		String regxStr="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1=Pattern.compile(regxStr);
		Matcher isNo=pattern1.matcher(str);
		if(isNo.matches()){
			flag=true;
		}
		return flag;
	}


	public static String getNumFromString(String str){
		String regEx="[^0-9]";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String telnum = m.replaceAll("").trim();
		return telnum;
	}

	/**
	 * 根据手机分辨率从dp转成px
	 *
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率�? px(像素) 的单�? 转成�? dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f) - 15;
	}

	/**
	 * 获取手机状�?�栏高度
	 *
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		java.lang.reflect.Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}


	/**
	 * 判断手机号码*/
	public static boolean isMobileNO(String mobiles){

		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobiles);

		return matcher.matches();

	}

	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";





	/**
	 * URL编码（utf-8）
	 *
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据内容类型判断文件扩展名
	 *
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	/*
	 * 获取当前app的versionCode 
	 * return int
	 */
	public static int getversionCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo("com.gcfwpt.designonline", 0).versionCode;
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return verCode;
	}

	/**
	 * 得到APP的版本号
	 * @param context 上下文
	 * @return
	 */
	public static String getVersionName(Context context){
		PackageManager packageManager=context.getPackageManager();
		try {
			PackageInfo packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
			// 取的是  从0位开始 取4个
			return  packageInfo.versionName.substring(0,5);
			//return  "V"+packageInfo.versionName;
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
			return "1.0.0";
		}
	}

	/**
	 * byte(字节)根据长度转成kb(千字节)和mb(兆字节) 
	 *
	 * @param bytes
	 * @return
	 */
	public static String bytes2kb(long bytes) {
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
				.floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
				.floatValue();
		return (returnValue + "KB");
	}

	public static void hideSoftInput(Context context,Activity activity) {
		InputMethodManager inputMethodManager;
		inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null) {
			View v = activity.getCurrentFocus();
			if (v == null) {
				return;
			}

			inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
//            activity.clearFocus();
		}
	}

	/**
	 * 过滤html
	 * @param html
	 * @param filterTags
	 * @return
	 */
//	public static String trimHtml2Txt(String html, String[] filterTags){
//		html = html.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");//去掉head
//		html = html.replaceAll("\\<!--[\\s\\S]*?-->", "");//去掉注释
//		html = html.replaceAll("\\<![\\s\\S]*?>", "");
//		html = html.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");//去掉样式
//		html = html.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");//去掉js
//		html = html.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");//去掉word标签
//		html = html.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");
//		html = html.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)", "");
//		html = html.replaceAll("\\\r\n|\n|\r", " ");//去掉换行
//		html = html.replaceAll("\\<br[^>]*>(?i)", "\n");
//		List<String> tags = new ArrayList<String>();
//		List<String> s_tags = new ArrayList<String>();
//		List<String> halfTag = Arrays.asList(new String[]{"img", "table", "thead", "th", "tr", "td"});//
//		if(filterTags != null && filterTags.length > 0){
//			for (String tag : filterTags) {
//				tags.add("<"+tag+(halfTag.contains(tag)?"":">"));//开始标签
//				if(!"img".equals(tag)) tags.add("</"+tag+">");//结束标签
//				s_tags.add("#REPLACETAG"+tag+(halfTag.contains(tag)?"":"REPLACETAG#"));//尽量替换为复杂一点的标记,以免与显示文本混合,如：文本中包含#td、#table等
//				if(!"img".equals(tag)) s_tags.add("#REPLACETAG/"+tag+"REPLACETAG#");
//			}
//		}
//		html = ExStringUtils.replaceEach(html, tags.toArray(new String[tags.size()]), s_tags.toArray(new String[s_tags.size()]));
//		html = html.replaceAll("\\</p>(?i)", "\n");
//		html = html.replaceAll("\\<[^>]+>", "");
//		html = ExStringUtils.replaceEach(html,s_tags.toArray(new String[s_tags.size()]),tags.toArray(new String[tags.size()]));
//		html = html.replaceAll("\\ ", " ");
//		return html.trim();
//	}

}
