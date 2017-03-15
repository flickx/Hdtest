package com.ftoul.util.base64;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileBase64Util {

	/**
	 * 将文件转换为BASE64加密字符串.
	 * 
	 * @param filePath
	 *            文件路径.
	 * @return
	 */
	@SuppressWarnings("restriction")
	public String convertFileToString(String filePath) {
		String result = null;
		InputStream input = null;
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			input = new FileInputStream(filePath);
			byte[] bytes = new byte[input.available()];
			input.read(bytes);
			result = encoder.encodeBuffer(bytes).trim();
			System.out.println("将图片转换为BASE64加密字符串成功！");
		} catch (IOException e) {
			System.out.println("将图片转换为 BASE64加密字符串失败: " + e);
		} finally {
			try {
				if (input != null) {
					input.close();
					input = null;
				}
			} catch (Exception e) {
				System.out.println("关闭文件流发生异常: " + e);
			}
		}
		return result;
	}
	
	/**
	 * 将BASE64字符串解密成文件
	 * @param base64String
	 * @param filePath
	 */
	@SuppressWarnings("restriction")
	public void convertByteToFile(String base64String, String filePath) {
		byte[] bytes = null;
		BufferedOutputStream bufferedOutput = null;
		File file = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			bytes = decoder.decodeBuffer(base64String);
			file = new File(filePath);
		    OutputStream output = new FileOutputStream(file);
		    bufferedOutput = new BufferedOutputStream(output);
		    bufferedOutput.write(bytes);
			System.out.println("将BASE64加密字符串转换为文件成功！");
		} catch (IOException e) {
			System.out.println("将BASE64加密字符串转换为文件失败: " + e);
		} finally {
			try {
				if (bufferedOutput != null) {
					bufferedOutput.close();
					bufferedOutput = null;
				}
			} catch (Exception e) {
				System.out.println("关闭文件流发生异常: " + e);
			}
		}
	}
	
}
