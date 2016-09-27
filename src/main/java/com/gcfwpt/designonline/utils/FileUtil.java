package com.gcfwpt.designonline.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import android.content.Context;

public class FileUtil {
	/**
	 * File文件转String
	 */
	public static String jsonFileToString(Context context,String filePath,String fileName){
		String file_string = null;
		try {
			FileInputStream fis=new FileInputStream(new File(new DiskUtil(context).getDISKPATH() + filePath + File.separator + fileName));
			/**
			 * 因为要转成String，而String存在于内存中，
			 * 所以为了把输入流通过输出流输出到内存，就要使用ByteArrayOutputStream
			 */
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[4 * 1024];
			int len=0;
			while((len=fis.read(buf)) != -1){
				baos.write(buf,0,len);
			}
			byte[] data = baos.toByteArray();
			/**
			 * 这样就得到了文件的字节数组行式，下面要做的就是把字节数组转成String，
			 * 不能使用new String(byte[] b)的方法，而必须使用BASE64进行操作
			 */
			file_string= new String(data);
			fis.close();
			baos.close();
//			/**
//			 * 得到的file即为文件的String形式，当然如果要将得到的String还原为文件，逆向进行操作就行
//			 * //也要使用Base64进行解码，将String转成byte[]  
//			 * byte[] b=Base64.decode(minPhoto);  
//			 * 通过文件输出流，将字节输出到photo.jpg  
//			 * FileOutputStream fos=new FileOutputStream("c:/photo.jpg");
//			 * fos.write(b);
//			 * fos.close();
//			 */
			
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return file_string;
	}
}
