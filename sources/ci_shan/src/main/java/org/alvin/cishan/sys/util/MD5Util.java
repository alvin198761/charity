package org.alvin.cishan.sys.util;

import com.google.common.hash.Hashing;

/**
 * @功能说明 md5加密工具类
 * @author gzz
 * @date 2017-10-19
 */
public class MD5Util {
	/**
	 * @功能说明 把字符使用md5加密
	 */
	public static String getMD5(String srt) {
		return Hashing.md5().hashBytes(srt.getBytes()).toString();
	}

}
