package org.alvin.cishan.sys.util;

import java.util.UUID;

/**
 * @功能描述 UUID生成工具
 * @author 高振中
 * @Date: 下午4:21
 */
public class UUIDGenerator {

	/**
	 * @功能描述 获得一个UUID去掉"-"符号
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获得指定数目的UUID
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}
}
