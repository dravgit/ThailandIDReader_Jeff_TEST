package com.centerm.centermposoversealib.util;

/**
 * 十六进制处理工具类
 * 
 * @author Administrator
 * 
 */
public class HexUtil {

	/**
	 * 功能描述：将16进制的字符串转换为字节数组,例如有16进制字符串"12345678"<br/>
	 * 转换后的结果为：{18, 52 ,86 ,120 };
	 * 
	 * @param hex
	 *            需要转换的16进制字符串
	 * @return 以字节数组返回转换后的结果
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toUpperCase().toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * 功能描述：把字节数组转换为十六进制字符串，例如有字节数组<br/>
	 * byte [] data = new byte[]{18, 52 ,86 ,120 };转换之后的结果为："12 34 56 78"
	 * 
	 * @param bArray
	 *            所要进行转换的数组内容
	 * @return 返回转换后的结果，内容用空格隔开
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		int j = 0; // 此处定义的j用于控制每行输出的数据个�?
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
			j++;
		}
		return sb.toString();
	}

	/**
	 * 十六进制字符转换成十六进制字节
	 * 
	 * @param c
	 *            十六进制字符
	 * @return 返回十六进制字节
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 将16位int转换为长度为2的byte数组
	 * 高位在前，低位在后
	 * @param num
	 *            整数值
	 * @return
	 */
	public static byte[] int2bytes(int num) {
		byte[] b = new byte[4];
		int mask = 0xff;
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (num >>> (24 - i * 8));
		}
		return b;
	}
	
	/**
	 * 16位Int转为长度为4的Byte数组
	 * 低位在前，高位在后
	 * @author liqiaoli@cenerm.com
	 * @time 2017/03/16 14:06
	 * @fun int转byte数组，低位在前
	 * @param num
	 * @return
	 */
	public static byte[] int2byte2Low(int num){
		byte[] b = new byte[4];
		b[0] = (byte)num;
		b[1] = (byte)(num >>> 8);
		b[2] = (byte)(num >>> 16);
		b[3] = (byte)(num >>> 24);
		return b;
	}

	/**
	 * 将长度为2的byte数组转换为16位int
	 * 高位在前，低位在后
	 * @param b
	 *            字节数组
	 * @return
	 */
	public static int bytes2int(byte[] b) {
		// byte[] b=new byte[]{1,2,3,4};
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}
	
	/**
	 * 字节数组转Int
	 * 低位在前，高位在后
	 * @param b
	 * @author liqiaoli@centerm.com
	 * @time 2017/03/16 14:10
	 * @return
	 */
	public static int bytes2intLow(byte[] b){
		return  ((b[0] & 0xff) | (b[1] & 0xff) << 8 | b[2] & 0xff << 16 | b[3] & 0xff << 24);
	}

	/**
	 * 将长度为2的byte数组转换为16位int
	 * 
	 * @param res
	 *            byte[]
	 * @return int
	 * */
	public static int bytes2short(byte[] b) {
		// byte[] b=new byte[]{1,2,3,4};
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 2; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	/**
	 * bcd码转换为字符串
	 * 
	 * @author: Administrator
	 * @date:2014-4-1 下午6:58:23
	 * @param bcds
	 * @return
	 */
	public static String bcd2str(byte[] bcds) {
		if(bcds == null)
			return "";
		
		char[] ascii = "0123456789abcdef".toCharArray();
		byte[] temp = new byte[bcds.length * 2];
		for (int i = 0; i < bcds.length; i++) {
			temp[i * 2] = (byte) ((bcds[i] >> 4) & 0x0f);
			temp[i * 2 + 1] = (byte) (bcds[i] & 0x0f);
		}
		StringBuffer res = new StringBuffer();

		for (int i = 0; i < temp.length; i++) {
			res.append(ascii[temp[i]]);
		}
		return res.toString().toUpperCase();
	}
	
	/**
	 * 将长度为2的byte数组转换为16位int byte数组高位在后，低位在前
	 * 
	 * @param res
	 *            byte[]
	 * @return int
	 * */
	public static short bytes2shortLow(byte[] b) {
		return (short) ((b[0] & 0xff) | (b[1] & 0xff) << 8);
	}

	/**
	 * 将长度为2的byte数组转换为16位int byte数组高位在前，低位在后
	 * 
	 * @param res
	 *            byte[]
	 * @return int
	 * */
	public static short bytes2shortHigh(byte[] b) {
		return (short) ((b[0] & 0xff) << 8 | (b[1] & 0xff));
	}
	
	/**
	 * short转byte[] 低位前，高位后
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] shortToBytes(short s) {
		byte[] shortBuf = new byte[2];
		for (int i = 0; i < 2; i++) {
			int offset = (shortBuf.length - 2 + i) * 8;
			shortBuf[i] = (byte) ((s >>> offset) & 0xff);
		}
		return shortBuf;
	}

	/**
	 * short转byte[] 高位前，低位后
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] shortToBytesHigh(short s) {
		return new byte[] { (byte) ((s >>> 8) & 0xff), (byte) (s & 0xff) };
	}
}
