package com.gcfwpt.designonline.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpURLConnectionUtil {
	public static InputStream GetInputStreamFromURL(String urlstr){
		HttpURLConnection connection;
		URL url;
		InputStream stream = null;
		try{
			url = new URL(urlstr);
			connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);
			connection.connect();
			stream = connection.getInputStream();
		}catch(Exception e){
			ExceptionUtil.handleException(e);
		}
		return stream;
	}

	/**
	 * 发送https请求
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpsRequest(final String requestUrl,final String requestMethod,final String outputStr) {
		StringBuffer buffer = new StringBuffer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Log.i("okhttp","httpsRequest:");
					URL url = null;
					url = new URL(requestUrl);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					//	conn.setSSLSocketFactory(ssf);

					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setUseCaches(false);
					// 设置请求方式（GET/POST）
					conn.setRequestMethod(requestMethod);
					//conn.setRequestProperty("Content-type", "text/javascript");
					conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
					conn.setRequestProperty("Charset", "UTF-8");
					//		conn.setRequestProperty("Content-Type", "application/json");

					conn.connect();
					//建立输入流，向指向的URL传入参数
					DataOutputStream dos=new DataOutputStream(conn.getOutputStream());
					String param="token="+ URLEncoder.encode(outputStr,"UTF-8");
					dos.writeBytes(param);
					dos.flush();
					dos.close();
					// 当outputStr不为null时向输出流写数据
//			if (null != outputStr) {
//				OutputStream outputStream = conn.getOutputStream();
//				// 注意编码格式
//				outputStream.write(outputStr.getBytes("UTF-8"));
//				outputStream.close();
//			}

					// 从输入流读取返回内容
					int resultCode = conn.getResponseCode();
					if(HttpURLConnection.HTTP_OK == resultCode){
						StringBuffer sb=new StringBuffer();
						String readLine = new String();
						BufferedReader responseReader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
						while((readLine = responseReader.readLine()) != null){
							sb.append(readLine).append("\n");
						}
						responseReader.close();
						System.out.println(sb.toString());
					}

//			InputStream inputStream = conn.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//			String str = null;
//
//			while ((str = bufferedReader.readLine()) != null) {
//				buffer.append(str);
//			}

					// 释放资源
					//bufferedReader.close();
					//inputStreamReader.close();
					//inputStream.close();
					//inputStream = null;
					conn.disconnect();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		//			TrustManager[] tm = { new MyX509TrustManager() };
		//			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		//			sslContext.init(null, tm, new java.security.SecureRandom());
		//			// 从上述SSLContext对象中得到SSLSocketFactory对象
		//			SSLSocketFactory ssf = sslContext.getSocketFactory();



		return buffer.toString();
	}

	/**
	 * 发送https请求
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpsRequestRegister(String requestUrl, String requestMethod, String param) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			//			TrustManager[] tm = { new MyX509TrustManager() };
			//			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			//			sslContext.init(null, tm, new java.security.SecureRandom());
			//			// 从上述SSLContext对象中得到SSLSocketFactory对象
			//			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//	conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			//conn.setRequestProperty("Content-type", "text/javascript");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");
			//		conn.setRequestProperty("Content-Type", "application/json");

			conn.connect();
			//建立输入流，向指向的URL传入参数
			DataOutputStream dos=new DataOutputStream(conn.getOutputStream());
			//String param="token="+ URLEncoder.encode(outputStr,"UTF-8");
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			// 当outputStr不为null时向输出流写数据
//			if (null != outputStr) {
//				OutputStream outputStream = conn.getOutputStream();
//				// 注意编码格式
//				outputStream.write(outputStr.getBytes("UTF-8"));
//				outputStream.close();
//			}

			// 从输入流读取返回内容
			int resultCode = conn.getResponseCode();
			if(HttpURLConnection.HTTP_OK == resultCode){
				StringBuffer sb=new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while((readLine = responseReader.readLine()) != null){
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb.toString());
			}

//			InputStream inputStream = conn.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//			String str = null;
//
//			while ((str = bufferedReader.readLine()) != null) {
//				buffer.append(str);
//			}

			// 释放资源
			//bufferedReader.close();
			//inputStreamReader.close();
			//inputStream.close();
			//inputStream = null;
			conn.disconnect();
		} catch (ConnectException ce) {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}  