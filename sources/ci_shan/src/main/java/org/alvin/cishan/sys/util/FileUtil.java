package org.alvin.cishan.sys.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author 高振中
 * @功能描述: 文件读写辅助类
 * @date 2017-10-17
 */
public class FileUtil {
//	private static Log logger = LogFactory.getLog(FileUtil.class);

	// 递归删除文件夹
	public static void delDir(File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				delDir(subFile);
			}
		}
		file.delete();
	}

	/**
	 * @功能描述: 以工作空间编码写文本文件
	 */
	public static void writeFile(String path, String context) {
		createDir(path);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
			writer.write(context);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @功能描述: 如果目录不存在创建这个目录
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
			parent.setWritable(true);
			parent.setReadable(true);
			parent.setExecutable(true);
		}
	}

	public static String readFile(String path) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		return new String(bytes, "UTF-8");
	}

}
