package org.alvin.cishan.sys.util;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SqlUtil {

	/**
	 * @方法说明:数据库中执行的SQL语句
	 */
	public static String showSql(String sql, Object[] obj) {
		String param;
		for (int j = 0; null != obj && j < obj.length; j++) {
			param = "null";
			if (null != obj[j]) {
				String cname = obj[j].getClass().getName();
				if (cname.contains("Date") || cname.contains("Timestamp")) {
					param = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj[j]) + "'";
				} else if (cname.contains("String")) {
					param = "'" + (String) obj[j] + "'";
				} else {
					param = obj[j].toString();
				}
			}
			sql = sql.replaceFirst("[?]", param);
		}
		return sql;
	}

	/**
	 * @方法说明:把组数拼接成IN语句(字符型)
	 */
	// @Deprecated
	public static String ArrayToIn(String ids) {// 字符IN字符窜
		String[] arr = ids.split(",");
		return " IN ('" + Joiner.on("','").join(arr) + "')";
	}

	/**
	 * @方法说明:把组数拼接成IN语句
	 */
	public static String ArrayToIn(Long ids[]) {
		return " IN ('" + Joiner.on("','").join(ids) + "')";
	}

	/**
	 * @方法说明:把组数拼接成IN语句
	 */
	public static String ArrayToIn(Integer ids[]) {
		return " IN ('" + Joiner.on("','").join(ids) + "')";
	}

	/**
	 * @方法说明:把List拼接成IN语句(数值型)
	 */
	public static String ArrayToIn(List<?> ids) {// 数值IN字符窜
		return " ('" + Joiner.on("','").join(ids) + "')";
	}

	/**
	 * @方法说明:用于批操作显示SQL
	 */
	public static <T> String showSql(String sql, T t) {
		try {
			Method method;
			String paramValue;
			List<String> paramNames = praseParams(sql);
			for (String paramName : paramNames) {
				paramValue = "有情况";
				method = t.getClass().getDeclaredMethod("get" + firstUpper(paramName.substring(1)));
				String className = method.getReturnType().getName();
				if (className.contains("Integer")) {
					paramValue = Integer.toString((Integer) method.invoke(t));
				} else if (className.contains("Date")) {
					paramValue = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(method.invoke(t)) + "'";
				} else if (className.contains("String")) {
					paramValue = "'" + (String) method.invoke(t) + "'";
				} else if (className.contains("Long")) {
					paramValue = Long.toString((Long) method.invoke(t));
				} else if (className.contains("Float")) {
					paramValue = Float.toString((Float) method.invoke(t));
				} else if (className.contains("Boolean")) {
					paramValue = Boolean.toString((Boolean) method.invoke(t));
				} else if (className.contains("BigDecimal")) {
					paramValue = (String) method.invoke(t);
				} else {
					paramValue = "'" + (String) method.invoke(t) + "'";
				}
				sql = sql.replace(paramName, paramValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	private static List<String> praseParams(String sql) {
		char[] arr = sql.toCharArray();
		Set<ParamPos> posList = new HashSet<>();
		List<String> nameList = new ArrayList<>();
		ParamPos pos;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ':') {
				pos = new ParamPos();
				pos.setStart(i);
				for (int j = i; j < arr.length; j++) {
					if (arr[j] == ' ' || arr[j] == ',' || arr[j] == ')') {
						pos.setEnd(j);
						break;
					}
				}
				posList.add(pos);
			}
		}
		StringBuffer name;
		for (ParamPos paramPos : posList) {
			name = new StringBuffer();
			for (int i = paramPos.getStart(); i < paramPos.getEnd(); i++) {
				name.append(arr[i]);
			}
			nameList.add(name.toString());
		}
		return nameList;
	}

	private static String firstUpper(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
	}

	/**
	 * 数据库加密用的key，临时这么放着，我在想用配置加密的办法
	 *
	 * @return
	 */
	public static final String mysqlKey = ".dl701.";

	public static String mysql_aes_decrypt(String content) {
		if(Strings.isNullOrEmpty(content)){
			return null;
		}
		try {
			return MysqlAES2JavaUtil.mysql_aes_decrypt(content, mysqlKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String mysql_aes_encrypt_sql(String content) {
		if(Strings.isNullOrEmpty(content)){
			return null;
		}
		try {
			return "'" + MysqlAES2JavaUtil.mysql_aes_encrypt(content, mysqlKey) + "'";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "''";
	}

	public static String mysql_aes_encrypt_value(String content) {
		if(Strings.isNullOrEmpty(content)){
			return null;
		}
		try {
			return MysqlAES2JavaUtil.mysql_aes_encrypt(content, mysqlKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

//	/**
//	 * mysql 加密
//	 *
//	 * @return
//	 */
//	@Deprecated
//	public static String getAES_ENCRYPT() {
//		return "?";
////		return " Hex(AES_ENCRYPT(?,'" + mysqlKey + "'))";
//	}
//
//	public static String getAES_ENCRYPT(String param) {
//		return " Hex(AES_ENCRYPT(" + param + ",'" + mysqlKey + "'))";
//	}
//
//	/**
//	 * mysql 解密
//	 *
//	 * @return
//	 */
//	public static String getAES_DECRYPT() {
//		return getAES_DECRYPT("?");
//	}
//
	/**
	 * 查询解密
	 *
	 * @param param
	 * @return
	 */
	public static String getAES_DECRYPT(String param) {
		return " AES_DECRYPT(UNHEX(" + param + "),'" + mysqlKey + "')";
	}

}

@Getter
@Setter
class ParamPos {
	private Integer start;
	private Integer end;
}