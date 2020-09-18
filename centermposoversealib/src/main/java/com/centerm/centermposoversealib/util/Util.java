package com.centerm.centermposoversealib.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * 公共类
 * 
 * @author Tianxiaobo
 * 
 */
public class Util {

	// 字符串判空操作
	public static boolean isEmptyString(String str) {
		if ("".equals(str) || null == str) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 格式化日期，如：“131031”转化成“2013/10/31”
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		String year = date.substring(0, 2);
		String month = date.substring(2, 4);
		String day = date.substring(4, 6);

		return "20" + year + "/" + month + "/" + day;
	}

	/**
	 * 格式化时间，如:"011119"转化成“01:11:19”
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(String time) {
		String hour = time.substring(0, 2);
		String min = time.substring(2, 4);
		String sec = time.substring(4, 6);

		return hour + ":" + min + ":" + sec;
	}

	/**
	 * 金额反格式化，例如把 0000000001250转化成12.50
	 * 
	 * @param mount
	 *            金额，一般从报文中获取
	 * @return 格式化后的金额，带小数点显示
	 */
	public static String unformatMount(String mount) {
		if ("".equals(mount) || mount == null) {
			return "0.00";
		}
		double money = (double) (Long.parseLong(mount) * 0.01);
		if (money > 0) {
			DecimalFormat df = new DecimalFormat("##0.00");
			return df.format(money);

		} else {
			return "0.00";
		}
	}

	/**
	 * 金额格式化，例如把12.50转化成0000000001250
	 * 
	 * @param mount
	 *            金额
	 * @return 格式化后的金额
	 */
	public static String formatMount(String mount) {
		if ("".equals(mount) || mount == null) {
			return "000000000000";
		}
		mount = mount.replace(".", "");
		return addZeroForNum(mount, 12);
	}

	/**
	 * 字符串左补零操作
	 * 
	 * @param str
	 *            原字符串
	 * @param strLength
	 *            补位后的长度
	 * @return 补位后的字符串
	 * @createtor：Administrator
	 */
	private static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	public static Map<String, String> getTransTypeMap() {
		return new HashMap<String, String>() {
			private static final long serialVersionUID = 4872136019190031228L;
			{
				put("31", "余额查询");
				put("00", "消费");
				put("03", "预授权");
				put("60", "指定账户圈存");
				put("62", "非指定账户圈存");
				put("63", "现金充值");
				put("17", "现金充值撤销");
				put("28", "脱机退货");
			}
		};
	}
}
