package cc.fozone.javascript.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * IO工具类
 * @author jimmy song
 *
 */
public final class IOUtils {
	/**
	 * 读取文件，最终形成字符串
	 * @param path 文件地址
	 * @return 返回字符串
	 * @throws IOException 
	 */
	public static String readAsText(String path) throws IOException {
		String result = null;
		File file = new File(path);
		StringBuffer buf = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int data = -1;
			char[] hash = new char[1024];
			while((data = reader.read(hash)) != -1) {
				String text = new String(hash,0,data);
				buf.append(text);
				text = null;
			}
			reader.close();
			result = buf.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = null;
			throw e;
		} finally {
			buf = null;
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
				} finally {
					reader = null;
				}
			}
		}
		return result;
	}
}
