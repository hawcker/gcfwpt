package com.gcfwpt.designonline.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

import android.content.Context;
import android.os.Environment;

/**
 * 磁盘路径 此类使用前必须初始化对象
 * 有可能用户不存在SD卡  因此需要保持到手机内存中 必须实例化
 * @author Zhoucan
 * @since  2015.6.25
 * @modify 2015.7.7 11.30
 */
public class DiskUtil {

	public String DISKPATH;

	public DiskUtil(Context context){
		DISKPATH = initDiskPath(context);
	}

	/**
	 * 外部类用不到
	 * 必须初始化磁盘路径
	 * @param context
	 * @return DISKPATH
	 */
	public String initDiskPath(Context context) {
		if (!isExitSDCard()) {
			DISKPATH = context.getCacheDir().getPath() + File.separator;
		} else {
			DISKPATH = context.getExternalCacheDir().getPath() + File.separator;
		}
		return DISKPATH;
	}

	public String getDISKPATH(){
		if (DISKPATH != null) {
			return DISKPATH;
		}
		return null;
	}

	/**
	 * 外部类用不到
	 * 检查sd卡是否插入  外部类用不到
	 */

	private static boolean isExitSDCard(){
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable();
	}

	/** 
	 * 外部类用不到
	 * 创建目录
	 * @param filepath  如navs
	 */  
	public File createDiskDir(String filepath){ 
		File dir = new File(DISKPATH + filepath + File.separator);  
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 外部类用不到
	 * 创建文件到磁盘中
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml
	 */

	public File getOrCreateFile2Disk(String filepath,String fileName){
		File file = new File(createDiskDir(filepath),fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return file;
	}

	/** 
	 * 将一个InputStream字节流写入到磁盘中
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml 或者 "url.json"
	 * @param input 
	 * @return 返回磁盘中的名称为FileName文件File对象
	 */  
	public File write2DiskFromInputStream(String filepath, String fileName, InputStream input){  
		File file = null;
		OutputStream output = null;
		try{
			// 检测文件是否存在磁盘
			if (isFileExitOnDisk(filepath, fileName)) {
				// 如果文件存在 则给File赋值
				String endswith = fileName.substring(fileName.lastIndexOf("."));
				File[] files = findFileByFileType(filepath, endswith);
				if(files != null && files.length > 0){  
					for(int i=0; i<files.length; i++){  
						if(fileName.equals(files[i].getName())){  
							file = files[i];  
						}
					}  
				}
			} else {
				// 文件不存在，则创造这个文件
				file = getOrCreateFile2Disk(filepath,fileName); //根据传入的文件名创建  
				output = new FileOutputStream(file);
				byte[] buffer = new byte[4 * 1024]; //每次读取4K  
				int num = 0; //需要根据读取的字节大小写入文件  
				while((num = (input.read(buffer))) != -1){
					output.write(buffer, 0, num);
				}  
				output.flush(); //清空缓存
				input.close();
				output.close();
			}
		}catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return file;
	}

	/** 
	 * 将配置文件覆盖到磁盘中
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml 或者 "url.json"
	 * @param input 
	 * @return 返回磁盘中的名称为FileName文件File对象
	 */  
	public File write2DiskFromInputStreamAndCover(String filepath, String fileName, InputStream input){  
		File file = null;
		OutputStream output = null;
		try{
			// 文件不存在，则创造这个文件
			file = getOrCreateFile2Disk(filepath,fileName); //根据传入的文件名创建  
			output = new FileOutputStream(file);
			byte[] buffer = new byte[4 * 1024]; //每次读取4K  
			int num = 0; //需要根据读取的字节大小写入文件  
			while((num = (input.read(buffer))) != -1){
				output.write(buffer, 0, num);
			}  
			output.flush(); //清空缓存
			input.close();
			output.close();
		}catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return file;
	}

	/** 
	 * 把传入的字符流写入到磁盘中 
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml
	 * @param input 
	 * @return 返回磁盘中的名称为FileName文件File对象
	 */  
	@SuppressWarnings("resource")
	public File write2DiskFromBufferedReader(String filepath, String fileName, final BufferedReader input){  
		File file = null;
		try {
			// 检测文件是否存在磁盘
			if (isFileExitOnDisk(filepath, fileName)) {
				// 如果文件存在 则给File赋值
				File[] files = findFileByFileType(filepath, ".xml");  
				if(files != null && files.length > 0){  
					for(int i=0; i<files.length; i++){  
						if(fileName.equals(files[i].getName())){  
							file = files[i];  
						}
					}  
				}
			} else {
				// 文件不存在，则创造这个文件
				file = getOrCreateFile2Disk(filepath,fileName); //根据传入的文件名创建  
				final BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
				new Thread(){
					@Override
					public void run() {
						String line = null;  
						try {
							while((line = (input.readLine())) != null){  
								bufw.write(line);  
								bufw.newLine();  
							}
							bufw.flush();  //清空缓存  
							bufw.close(); 
							input.close();
						} catch (Exception e) {
							ExceptionUtil.handleException(e);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return file;  
	}  

	/**
	 * 外部类用不到
	 * 判断文件是否存在磁盘上面
	 * @param filepath  如navs
	 * @param FileName  如navs_configs.xml
	 */

	public boolean isFileExitOnDisk(String filepath, String fileName){
		File file = new File(DISKPATH + filepath + File.separator + fileName);
		return file.exists();
	}

	/**
	 * 外部类用不到
	 * 查找某目录下面的所有filetype类型的文件 
	 * @param filepath  如navs
	 * @param fileType  如 .xml   .png   .jpg
	 */

	public File[] findFileByFileType(String filepath, final String fileType){
		File dir = new File(DISKPATH + filepath + File.separator);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles(new FilenameFilter(){
				public boolean accept(File dir, String filename) {
					return (filename.endsWith(fileType));
				}
			});
			Arrays.sort(files, new Comparator<File>() {  
				@Override  
				public int compare(File str1, File str2) {  
					// returns:
					// case 0   if the strings are equal, 
					// case a negative integer(一个负整数) if this string is before the specified string, 
					// case a positive integer(一个正整数) if this string is after the specified string.
					return str2.getName().compareTo(str1.getName());  
				}  
			});
			return files;
		}
		return null;
	}
}