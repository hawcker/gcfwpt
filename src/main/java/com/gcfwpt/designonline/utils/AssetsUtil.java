package com.gcfwpt.designonline.utils;

import java.io.File;
import java.io.InputStream;

import android.content.Context;

public class AssetsUtil {
	/** 
	 * 外部类用不到
	 * 判断assets下是否存在某文件（判断完后，会关闭IO流) 
	 * 
	 * @param assetPath 
	 * @return 
	 */  
	private static boolean isAssetExist(Context context, String... filename) {  
		InputStream is = null;  
		try {
			for (String filenamestring : filename) {
				is = context.getAssets().open(filenamestring);  
			}
			return is != null;  
		} catch (Exception e) {  
			return false;  
		} finally {
			try {
				if(is != null) is.close();  
			} catch (Exception e) {  
				ExceptionUtil.handleException(e);
			}
		}
	}

	/**
	 * 复制assets中的文件到sd卡
	 * @param filepath  如 "navs"
	 * @param fileName  如 "nav_configs.xml" 或者 "url.json"
	 */

	public static boolean copyFile2SDCardFromAssets(Context context,String filepath,String... filename){
		DiskUtil diskUtil = new DiskUtil(context);
		InputStream in;
		File file;
		// 检查assets下面是否有文件
		if (isAssetExist(context, filename)) {
			try {
				for (String filenamestring : filename) {
					in = context.getResources().getAssets().open(filenamestring);
					file = diskUtil.write2DiskFromInputStream(filepath, filenamestring, in);
					if (file != null) {
						return true;
					}
				}
			} catch (Exception e) {
				ExceptionUtil.handleException(e);
				return false;
			}
		}
		return false;
	}
}
