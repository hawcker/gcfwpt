package com.gcfwpt.designonline.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 作者 : sorc
 * @version 创建时间：2012-11-23 上午11:16:48
 * 
 *          类说明
 */
public class FileHashUtil {
	public final static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getMD5(String str) {
		return getStringHash(str, "MD5");
	}

	public static String getFileMD5(String path) {
		return getFileHash(new File(path), "MD5");
	}

	public static String getFileMD5(File file) {
		return getFileHash(file, "MD5");
	}

	public static String getStringHash(String str, String hashType) {
		try {
			final MessageDigest md = MessageDigest.getInstance(hashType);
			md.update(str.getBytes());
			final byte[] byteArray = md.digest();
			return toHexString(byteArray);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	public static String getFileHash(File file, String hashType) {
		InputStream fis = null;
		BufferedInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			final MessageDigest md = MessageDigest.getInstance(hashType);
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = in.read(buffer)) > 0) {
				md.update(buffer, 0, numRead);
			}
			return toHexString(md.digest());
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					ExceptionUtil.handleException(e);
				}
			}
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				ExceptionUtil.handleException(e);
			}
		}
		return null;
	}

	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	// 需要在工作线程使用此方法
	public static String getFileHashByUrl(Context context, String filePath, String fileName, String url) {
		try {
			DiskUtil util = new DiskUtil(context);
			URL url2 = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
			InputStream in = connection.getInputStream();
			util.write2DiskFromInputStream(filePath, fileName,in);
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return url;
	}

	
	public static String getHashCodeByTypeFromJson(String type,String json){
		try {
			JSONArray jsonArray = new JSONArray(json);
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				if (type.equals(object.getString("type"))) {
					String hashCode = object.getString("md5");
					return hashCode;
				}
			}
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
			return null;
		}
		return null;
	}

	// 需要在工作线程使用此方法
	public static String FileToString(final Context context, final File file){
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			while((line = (in.readLine())) != null){  
				out.write(line);
				out.newLine();
			}
			out.flush();  //清空缓存  
			out.close(); 
			in.close();
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return line;
	}
}

