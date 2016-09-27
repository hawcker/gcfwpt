package com.gcfwpt.designonline.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Log;

public class DownloadUtil {  
	public static URL url = null;

	/** 
	 * 将下载下来的文件变为字符串输出
	 * 根据URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容  
	 * 1.创建一个URL对象 
	 * 2.通过URL对象，创建一个HttpURLConnection对象  
	 * 3.得到InputStram  
	 * 4.从InputStream当中读取数据 
	 * @param urlStr 
	 * @return String
	 */  
	public String download2String(String urlstr) {  
		StringBuffer sb = new StringBuffer();  
		String line = null;  
		BufferedReader buffer = null;  
		try {  
			// 创建一个URL对象  
			url = new URL(urlstr);  
			// 创建一个Http连接  
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();  
			// 使用IO流读取数据
			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "utf8")); // 防止中文出现乱码
			while ((line = buffer.readLine()) != null) {  
				sb.append(line);  
			}  
		} catch (Exception e) {  
			ExceptionUtil.handleException(e);
		} finally {  
			try {  
				buffer.close();  
			} catch (Exception e) {  
				ExceptionUtil.handleException(e);
			}
		}
		return sb.toString();
	}  

	/** 
	 * 字符流下载
	 *  
	 * @param urlstr  要下载文件的URI地址 
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml
	 * @return 该函数返回整型：-1代表下载失败，0代表下载成功，1代表文件已经存在 
	 */  
	@SuppressWarnings("static-access")
	public int downloadByBufferedReader(Context context,String urlstr, String filepath, String fileName) {  
		InputStream inputstream = null;  
		BufferedReader buffer = null;  
		try {  
			DiskUtil diskUtil = new DiskUtil(context);  
			if (diskUtil.isFileExitOnDisk(filepath, fileName)) {  
				return 1;
			} else {  
				// 获取URI中的字节流
				inputstream = getInputStreamFromUrl(urlstr);  
				// 把字节流转换成字符流
				buffer = new BufferedReader(new InputStreamReader(inputstream,"UTF-8")); // 防止中文出现乱码   UTF-8  
				File resultFile = diskUtil.write2DiskFromBufferedReader(filepath,fileName,buffer);  
				if (resultFile == null){
					return -1;
				}
			}  

		} catch (Exception e) {  
			ExceptionUtil.handleException(e);
			//return -1;
		} finally {  
			try {  
				if(buffer != null)  
					buffer.close();  
			} catch (Exception e) {  
				ExceptionUtil.handleException(e);
			}  
		}  
		return 0;
	}  

	/** 
	 * 字节流下载
	 *  
	 * @param urlstr 
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml
	 * @return 该函数返回整型：-1代表下载失败，0代表下载成功，1代表文件已经存在 
	 */  
	public int downloadByInputStream(Context context,String urlstr, String filepath, String fileName) {  
		InputStream inputstream = null; 
		Log.i("syso","downloadByInputStream------------------------"+context);
		try {  
			DiskUtil diskUtil = new DiskUtil(context);  
			Log.i("syso","222222222222222------------------------"+diskUtil);
			
			inputstream = HttpURLConnectionUtil.GetInputStreamFromURL(urlstr); 
			Log.i("syso","aaaaaaaaaaaaaaaaaaaaa------------------------"+inputstream.available());
			File resultFile = diskUtil.write2DiskFromInputStream(filepath, fileName,inputstream);  
			if (resultFile == null) {  
				return -1;  
			}

		} catch (Exception e) {  
			Log.i("syso","downloadByInputStream-------------Exception-----------"+e.getMessage());
			ExceptionUtil.handleException(e);
			return -1;
		} finally {  
			try {  
				inputstream.close();
			} catch (Exception e) {  
				ExceptionUtil.handleException(e);
			}  
		}
		return 0;  
	}  

	/** 
	 * 根据URL得到输入流 
	 *  
	 * @param urlstr 
	 * @return 
	 * @throws MalformedURLException 
	 * @throws IOException 
	 */  
	public InputStream getInputStreamFromUrl(String urlstr) throws IOException {
		InputStream inputStream = null;
		try {
			Log.i("syso","+++++++++++++++++++++++++++"+urlstr);
			url = new URL(urlstr); 
			Log.i("syso","url+++++++++++++++++++++++++++"+url); 
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();  
			Log.i("syso","urlConn+++++++++++++++++++++++++++"+urlConn);
			  
			Log.i("syso","urlConn+++++++++++++++23333++++++++++++"+urlConn);
			inputStream = urlConn.getInputStream();  
			Log.i("syso","inputStream+++++++++++++++++++++++++++"+inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("syso","++++++++++Exception+++++++++++++++++"+e.getMessage());
			ExceptionUtil.handleException(e);
		}
		return inputStream;
	}  
}
