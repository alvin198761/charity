package org.alvin.cishan.sys.util;

import com.google.common.collect.Maps;

import java.util.Map;

public class DictUtils {
	public static String formatPlatform(byte key) {
		Map<Byte, String> map = Maps.newHashMap();
		map.put((byte) 1, "安卓");
		map.put((byte) 2, "IOS");
		map.put((byte) 3, "PC_WEB");
		map.put((byte) 4, "系统");
		map.put((byte) 5, "微信公众号");
		map.put((byte) 6, "APP维护");
		return map.get(key);
	}

	public static String formatStatus(byte key) {
		Map<Byte, String> map = Maps.newHashMap();
		map.put((byte) 1, "已下单");
		map.put((byte) 2, "已支付");
		map.put((byte) 3, "已发货");
		map.put((byte) 4, "已取消");
		return map.get(key);
	}

	public static String formatPayWay(byte key) {
		Map<Byte, String> map = Maps.newHashMap();
		map.put((byte) 1, "现金");
		map.put((byte) 2, "支付宝");
		map.put((byte) 3, "微信");
		map.put((byte) 4, "刷卡");
		map.put((byte) 5, "储值");
		return map.get(key);
	}

	public static String formatBusType(byte key) {
		Map<Byte, String> map = Maps.newHashMap();
		map.put((byte) 11, "前台买定金");
		map.put((byte) 12, "手机买定金");
		map.put((byte) 13, "退定金");
		map.put((byte) 14, "储值卡充值");
		map.put((byte) 15, "储值卡退钱");
		map.put((byte) 16, "app储值卡充值钱");
		map.put((byte) 31, "app买定金");
		map.put((byte) 32, "App购买卡");
		map.put((byte) 33, "App为好友买定金");
		map.put((byte) 34, "App为好友买卡");
		map.put((byte) 21, "开卡");
		map.put((byte) 22, "卡升级");
		map.put((byte) 23, "续卡");
		map.put((byte) 24, "补卡");
		map.put((byte) 25, "转卡");
		map.put((byte) 26, "退卡");
		map.put((byte) 27, "停卡");
		map.put((byte) 28, "延期");
		map.put((byte) 29, "挂失");
		map.put((byte) 41, "租柜");
		map.put((byte) 42, "续柜");
		map.put((byte) 43, "退柜");
		map.put((byte) 44, "转柜");
		map.put((byte) 45, "换柜");
		map.put((byte) 51, "购买私教");
		map.put((byte) 52, "私教转会员");
		map.put((byte) 53, "私教转教练");
		map.put((byte) 54, "购买团体私教");
		map.put((byte) 55, "团课转会员");
		map.put((byte) 56, "App购买私教");
		map.put((byte) 57, "App为好友买私教");
		map.put((byte) 58, "App购买小团课");
		map.put((byte) 59, "App为他人买小团课");
		map.put((byte) 60, "退私教");
		map.put((byte) 61, "退团体私教");
		return map.get(key);
	}
}
