package com.gcfwpt.designonline.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageUtil {

	private static String byt;

	/**
	 * 加载本地图片
	 * http://bbs.3gstdy.com
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream in = new FileInputStream(url);
			return BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
			return null;
		}
	}

	/**
	 * drawableToBitamp
	 */
	private void drawableToBitamp(Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap bitmap = bd.getBitmap();
	}

	/**
	 * 从服务器取图片
	 *http://bbs.3gstdy.com
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return bitmap;
	}

	/**
	 * 通过HttpClient方式从服务器取图片
	 * @param url
	 * @return
	 */
	//public static Bitmap getHttpBitmapByHttpClient(String url) {
//		Bitmap bitmap = null;
//		try {
//			DefaultHttpClient client = BaseHttpClient.getInstance();
//			HttpGet httpGet = new HttpGet(url);
//			HttpResponse httpResponse = client.execute(httpGet);
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				byte[] data = EntityUtils.toByteArray(httpResponse.getEntity());
//				bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//				return bitmap;
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			ExceptionUtil.handleException(e);
//		}
	//return bitmap;
	//}
	/**
	 * 把图片路径转换为二进制/storage/sdcard1/Android
	 */
	public static String bitmap2Bytes(String filePath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;// 设置成了true,不占用内存，只获取bitmap宽高
		BitmapFactory.decodeFile(filePath, opts);
		opts.inSampleSize = computeSampleSize(opts, -1, 1024 * 800);
		opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		opts.inDither = false;
		opts.inPurgeable = true;
		opts.inTempStorage = new byte[16 * 1024];
		FileInputStream is = null;
		Bitmap bmp = null;
		ByteArrayOutputStream baos = null;
		byte[] bytes = null;
		try {
			is = new FileInputStream(filePath);
			bmp = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
			double scale = getScaling(opts.outWidth * opts.outHeight,1024 * 600);
			Bitmap bmp2 = Bitmap.createScaledBitmap(bmp,(int) (opts.outWidth * scale),(int) (opts.outHeight * scale), true);
			bmp.recycle();
			baos = new ByteArrayOutputStream();
			bmp2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			bmp2.recycle();
			bytes = baos.toByteArray();
			byt = Base64.encodeToString(bytes, Base64.DEFAULT);
			return byt;
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		} finally {
			try {
				is.close();
				baos.close();
			} catch (Exception e) {
				ExceptionUtil.handleException(e);
			}
			System.gc();
		}
		return byt;
	}

	private static double getScaling(int src, int des) {
		/**
		 * 48 目标尺寸÷原尺寸 sqrt开方，得出宽高百分比 49
		 */
		double scale = Math.sqrt((double) des / (double) src);
		return scale;
	}

	private static int computeSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	public static Uri FilePathToUri(Context context, String path) {

		Log.d("TAG", "filePath is " + path);
		if (path != null) {
			path = Uri.decode(path);
			Log.d("TAG", "path2 is " + path);
			ContentResolver cr = context.getContentResolver();
			StringBuffer buff = new StringBuffer();
			buff.append("(")
					.append(MediaStore.Images.ImageColumns.DATA)
					.append("=")
					.append("'" + path + "'")
					.append(")");
			Cursor cur = cr.query(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					new String[]{MediaStore.Images.ImageColumns._ID},
					buff.toString(), null, null);
			int index = 0;
			for (cur.moveToFirst(); !cur.isAfterLast(); cur
					.moveToNext()) {
				index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
				// set _id value
				index = cur.getInt(index);
			}
			if (index == 0) {
				//do nothing
			} else {
				Uri uri_temp = Uri
						.parse("content://media/external/images/media/"
								+ index);
				Log.d("TAG", "uri_temp is " + uri_temp);
				if (uri_temp != null) {
					return uri_temp;
				}
			}

		}
		return null;
	}
}
