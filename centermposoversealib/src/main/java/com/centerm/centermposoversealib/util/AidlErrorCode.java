package com.centerm.centermposoversealib.util;

/**
 * 错误码常量类，每个设备单独建立一个内部类存放各自设备的错误码
 * @author Administrator
 *
 */
public class AidlErrorCode {

	/**
	 * 身份证错误码
	 * @author Administrator
	 *
	 */
	public static class ThailIDCard{
		public static int ERROR_DEVICE_IS_BUSY = 0x00;
		public static int ERROR_TIMEOUT = 0x01;
		public static int ERROR_DEV = 0x02;
		public static int ERROR_CANCELED = 0x04;
	}
}
