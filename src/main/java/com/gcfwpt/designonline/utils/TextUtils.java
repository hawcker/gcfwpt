package com.gcfwpt.designonline.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import com.gcfwpt.designonline.R;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View.OnClickListener;
import android.widget.TextView;


/**
 * @fileName TextUtils.java
 * @package com.immomo.momo.android.util
 * @description 文本工具�?
 * @author 任东�?
 * @email 86930007@qq.com
 * @version 1.0
 */
public class TextUtils {
	/**
	 * 添加下划�?
	 * 
	 * @param context
	 *            上下�?
	 * @param textView
	 *            添加下划线的TextView
	 * @param start
	 *            添加下划线开始的位置
	 * @param end
	 *            添加下划线结束的位置
	 */
	public static void addUnderlineText(final Context context,
			final TextView textView, final int start, final int end) {
		textView.setFocusable(true);
		textView.setClickable(true);
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(
				textView.getText().toString().trim());
		spannableStringBuilder.setSpan(new UnderlineSpan(), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(spannableStringBuilder);
	}

	/**
	 * 获取括号中的国家区号
	 * 
	 * @param text
	 *            带有括号的国家区�?
	 * @param defaultText
	 *            默认的国家区�?(在获取错误时返回该�??)
	 * @return
	 */
	public static String getCountryCodeBracketsInfo(String text,
			String defaultText) {
		if (text.contains("(") && text.contains(")")) {
			int leftBrackets = text.indexOf("(");
			int rightBrackets = text.lastIndexOf(")");
			if (leftBrackets < rightBrackets) {
				return "+" + text.substring(leftBrackets + 1, rightBrackets);
			}
		}
		if (defaultText != null) {
			return defaultText;
		} else {
			return text;
		}
	}

	/**
	 * 添加超链�?
	 * 
	 * @param textView
	 *            超链接的TextView
	 * @param start
	 *            超链接开始的位置
	 * @param end
	 *            超链接结束的位置
	 * @param listener
	 *            超链接的单击监听事件
	 */
	public static void addHyperlinks(final TextView textView,
			final int start, final int end, final OnClickListener listener) {

		String text = textView.getText().toString().trim();
		SpannableString sp = new SpannableString(text);
		sp.setSpan(new IntentSpan(listener), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(new ForegroundColorSpan(textView.getContext().getResources()
				.getColor(R.color.black)), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(sp);
		textView.setMovementMethod(LinkMovementMethod.getInstance());

	}

	/**
	 * 根据月日获取星座
	 * 
	 * @param month
	 *            �?
	 * @param day
	 *            �?
	 * @return
	 */
	public static String getConstellation(int month, int day) {
		if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
			return "水瓶�?";
		} else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
			return "双鱼�?";
		} else if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
			return "白羊�?";
		} else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
			return "金牛�?";
		} else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
			return "双子�?";
		} else if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) {
			return "巨蟹�?";
		} else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
			return "狮子�?";
		} else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
			return "处女�?";
		} else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
			return "天秤�?";
		} else if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) {
			return "天蝎�?";
		} else if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) {
			return "射手�?";
		} else if ((((month != 12) || (day < 22)))
				&& (((month != 1) || (day > 19)))) {
			return "魔蝎�?";
		}
		return "";
	}

	/**
	 * 根据年月日获取年�?
	 * 
	 * @param year
	 *            �?
	 * @param month
	 *            �?
	 * @param day
	 *            �?
	 * @return
	 */
	public static int getAge(int year, int month, int day) {
		int age = 0;
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.YEAR) == year) {
			if (calendar.get(Calendar.MONTH) == month) {
				if (calendar.get(Calendar.DAY_OF_MONTH) >= day) {
					age = calendar.get(Calendar.YEAR) - year + 1;
				} else {
					age = calendar.get(Calendar.YEAR) - year;
				}
			} else if (calendar.get(Calendar.MONTH) > month) {
				age = calendar.get(Calendar.YEAR) - year + 1;
			} else {
				age = calendar.get(Calendar.YEAR) - year;
			}
		} else {
			age = calendar.get(Calendar.YEAR) - year;
		}
		if (age < 0) {
			return 0;
		}
		return age;
	}

	/**
	 * 获取Assets中的json文本
	 * 
	 * @param context
	 *            上下�?
	 * @param name
	 *            文本名称
	 * @return
	 */
	public static String getJson(Context context, String name) {
		if (name != null) {
			String path = "json/" + name;
			InputStream is = null;
			try {
				is = context.getAssets().open(path);
				return readTextFile(is);
			} catch (IOException e) {
				return null;
			} finally {
				try {
					if (is != null) {
						is.close();
						is = null;
					}
				} catch (IOException e) {

				}
			}
		}
		return null;
	}

	/**
	 * 从输入流中获取文�?
	 * 
	 * @param inputStream
	 *            文本输入�?
	 * @return
	 */
	public static String readTextFile(InputStream inputStream) {
		String readedStr = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				readedStr += tmp;
			}
			br.close();
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

		return readedStr;
	}

}
