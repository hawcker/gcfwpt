package com.gcfwpt.designonline.utils;

import com.gcfwpt.designonline.MApplication;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	public static void handleException(Exception e) {
		if (MApplication.isRelease == false) {
			e.printStackTrace();
		} else {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			String eInfo = stringWriter.toString();
		}
	}
}
