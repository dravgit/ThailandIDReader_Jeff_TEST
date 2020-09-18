package com.centerm.centermposoversealib.constant;

/**
 * 设备错误码
 * 
 * @author Tianxiaobo
 * 
 */
public interface DeviceErrorCode {
	/**
	 * 磁条卡设备错误码
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface DEVICE_MAGCARD {
		/** 设备打开成功 */
		public static final int DEVICE_OPEN_OK = 0x0000;
		/** 设备未打开 */
		public static final int DEVICE_NOT_OPEN = 0x0001;
		/** 设备繁忙 */
		public static final int DEVICE_BUSY = 0x0002;
		/** 刷卡超时 */
		public static final int DEVICE_TIMEOUT = 0x0003;
		/** 刷卡失败 */
		public static final int DEVICE_SWIPE_FAIL = 0x0004;
		/** 刷卡异常 */
		public static final int DEVICE_SWIPE_EXCEPTION = 0x0005;
		/** 刷卡成功 */
		public static final int DEVICE_SWIPE_SUCCESS = 0x0006;
		/**设置超时时间错误**/
		public static final int DEVICE_TIMEOUT_INVALID = 0x0007;
	}

	/**
	 * 打印机设备错误码
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface DEVICE_PRINTER {
		/** 打印机操作OK */
		public static final int DEVICE_OK = 0x0000;
		/** 打开打印机OK */
		public static final int DEVICE_OPEN_OK = 0x1001;
		/** 打印机未打开 */
		public static final int DEVICE_NOT_OPEN = 0x1002;
		/** 打印机设备被占用 */
		public static final int DEVICE_BUSY = 0x1003;
		/** 打印机无响应 */
		public static final int DEVICE_NORESPONSE = 0x1004;
		/** 打印数据错误 */
		public static final int DEVICE_PRINT_DATA_ERROR = 0x1005;
		/** 打印机高温 */
		public static final int DEVICE_PRINTER_OVER_HEATER = 0x1006;
		/** 打印头高温 */
		public static final int DEVICE_PRINTER_HEAD_OVER_HEIGH = 0x1007;
		/** 打印机缺纸 */
		public static final int DEVICE_PRINTER_OUT_OF_PAPER = 0x1008;
		/** 打印机命令错误 */
		public static final int DEVICE_PRINTER_CMD_ERROR = 0x1009;
		/** 打印机内存不足 */
		public static final int DEVICE_PRINTER_LOWMEMORY = 0x1010;
		/** 打印机未知异常 */
		public static final int DEVICE_PRINTER_UNKNOW_EXCEPTION = 0x1011;
		/** 打印监听器为空 */
		public static final int DEVICE_PRINTER_LISTENER_NULL = 0x1012;
		/**打印机低电量*/
		public static final int DEVICE_PRINTER_LOW_POWER = 0x1013;
	}

	/**
	 * 密码键盘错误码
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface DEVICE_PINPAD {
		/** 密码键盘状态正常 */
		public static final int DEVICE_OK = 0x0000;
		/** 打开密码键盘OK */
		public static final int DEVICE_OPEN_OK = 0x2001;
		/** 密码键盘未打开 */
		public static final int DEVICE_NOT_OPEN = 0x2002;
		/** 密码键盘设备被占用 */
		public static final int DEVICE_BUSY = 0x2003;
		/** MAC计算错误 */
		public static final int DEVICE_MAC_ERROR = 0x2004;
		/** 数据加密异常 */
		public static final int DEVICE_DATA_ENCRYPT_ERROR = 0x2005;
		/** 发散工作密钥失败错误码 */
		public static final int DEVICE_DISPERSEWKEY_ERROR = 0x2006;
		/** 发散主密钥失败错误码 */
		public static final int DEVICE_DISPERSEMKEY_ERROR = 0x2007;
		/** 输入PIN错误错误码 */
		public static final int DEVICE_INPUT_PIN_ERROR = 0x2008;
		/** 输入PIN错误错误码 */
		public static final int DEVICE_INPUT_PIN_CANCLE = 0x200B;
		/** 输入PIN错误错误码 */
		public static final int DEVICE_INPUT_PIN_TIMEOUT_ERROR = 0x200a;
		/** 密码键盘显示信息错误 */
		public static final int DEVICE_DISPLAY_ERROR = 0x2009;
	}

	/***
	 * PBOC设备错误
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface DEVICE_PBOC {
		/** 设备OK */
		public static final int DEVICE_OK = 0x3000;
		/** 设备打开成功 */
		public static final int DEVICE_OPEN_OK = 0x3001;
		/** 设备未打开 */
		public static final int DEVICE_NOT_OPEN = 0x3002;
		/** 设备繁忙 */
		public static final int DEVICE_BUSY = 0x3003;
		/** 检卡超时 */
		public static final int DEVICE_SEARCH_CARD_TIME_OUT = 0x3004;
		/** 检卡错误 */
		public static final int DEVICE_SEARCH_CARD_ERROR = 0x3005;
		/** EMV应答错误，可以为各种异常原因 */
		public static final int DEVICE_STARTEMVPRO_ERROR = 0x3006;
		/** EMV结束执行异常 */
		public static final int DEVICE_ENDEMV_ERROR = 0x3007;
		/** EMV执行过程异常*/
		public static final int DEVICE_PBOC_ERROR = 0x3008;
	}
	
	/**
     * 客显操作错误码
     * @author Administrator
     *
     */
    public  interface CUSTOMERDISPLAY{
    	/**设备未打开*/
    	public static final int ERROR_DEV_IS_NOT_OPEN = -1;
    	/**显示错误*/
    	public static final int ERROR_DEV_DISPLAY = -2;
    	/**清屏*/
    	public static final int ERROR_DEV_CLEAR = -3;
    	/**LED灯控制*/
    	public static final int ERROR_DEV_LED_CTRL = -4;
    }
    
    /**
     * Mifare卡操作设备
     * @author Administrator
     *
     */
    public interface MIFARE{
    	public static final int ERROR_KEY_TYPE = -1;//密钥类型错误
    	public static final int ERROR_KEY_LENTH = -2;//密钥长度错误
    	public static final int ERROR_RESET_DATA = -3;//卡片复位信息错误
    	public static final int ERROR_UNKNOWN = -4;//未知错误
    	public static final int ERROR_AUTH_FAIL = -5;//认证失败
    	public static final int ERROR_READ_FAIL = -6;//读数据失败
    	public static final int ERROR_WRITE_FAIL = -7;//写数据失败
    	public static final int ERROR_SELECT_FAIL = -8;//选择块失败
    	public static final int ERROR_ADD_FAIL = -9;//加值失败
    	public static final int ERROR_REDUCE_FAIL = -10;//减值失败
    	public static final int ERROR_DELIVERY_FAIL = -11;//传值失败
    	public static final int ERROR_DEVICE_NOTOPEN = -12;//设备未打开
    }
    
	/**
	 * modem设备错误码
	 * @author Administrator
	 *
	 */
	public interface MODEM{
		//设备未打开
		public static int ERROR_DEV_NOTOPEN = -3;
		public static int ERROR_DEV = -1;//设备异常
		public static int ERROR_RECEIVEDATA = -2;
	}
	
	/**
	 * 身份证模块错误码
	 * @author Tianxiaobo
	 *
	 */
	public interface IDCARD{
		/**设备繁忙*/
		public static int ERROR_DEVICE_IS_BUSY = 0x00;
		/**设备超时*/
		public static int ERROR_TIMEOUT = 0x01;
		/**设备错误*/
		public static int ERROR_DEV = 0x02;
		/**操作被取消*/
		public static int ERROR_CANCELED = 0x04;
		
		/** 以下为rf卡设备读身份证错误码 */
		/** 非接触异常 */
		public static int NFCERROR = -1;
		/** 网络异常 */
		public static int SOCKETERROR = -2;
		/** 读身份证证异常 */
		public static int READERROR = -3;
		/** POS设备读证异常 */
		public static int DEVICEERROR = -4;
		/** 程序异常 */
		public static int APPERROR = -5;
		/** 未检测到身份证 */
		public static int CARDERROR = -6;
		/** 客户端不存在 */
		public static int CLINETERROR = -7;
		/** 程序状态异常 */
		public static int STATESERROR = -8;
		/** 当前客户端已欠费、请充值 */
		public static int OUTMONEYERROR = -9;
		/** 客户端验证失败 */
		public static int VERFIYERROR = -10;
		/** 服务器资源获取失败 */
		public static int RESOURCEERROR = -11;
		/** 未检测到SIM卡 */
		public static int NOSIMERROR = -12;
		/** 已经注册 */
		public static int ALREADYREGISTER = 11;
		/** 注册成功 */
		public static int REGISTEROK = 12;
		/** 注册失败 */
		public static int REGISTERFAIL = 13;
	}
	
	/**
	 * @author wangwenxun@centerm.com
	 * @time 2017/05/02 16:29
	 * @func 升腾库授权激活常见错误码
	 */
	public interface QUICKSCAN{
		public static int LIVE_SUCCESS = 0x01;
		/**so库不存在*/
		public static int ERROR_SO_FILE_NOT_EXIST = 0x02;
		/**授权文件未生成*/
		public static int ERROR_AUTH_FILE_NOT_EXIST = 0x03;
		/**系统参数错误*/
		public static int ERROR_SYSTEM_CONFIG_IS_WRONG = 0x04;
		/**授权文件未复制到工程路劲下*/
		public static int ERROR_AUTH_FILE_NOT_COPY = 0x05;
		/**授权文件与生成的授权文件不符*/
		public static int ERROR_AUTH_FILE_NOT_SAME = 0x06;
	}
	
	public interface NFCIDCARD {
		/** 接收数据超时 */
		public static int RECEIVE_TIMEOUT = -1;
		/** 授读卡失败 */
		public static int READ_FAILED = -2;
		/** 没有找到服务器 */
		public static int NOT_FOUND_SERVER = -3;
		/** 服务器忙 */
		public static int SERVER_BUSY = -4;
		/** 设备不支持NFC */
		public static int NOT_SUPPORT_NFC = -5;
		/** 无网络 */
		public static int NETWORK_NOT_AVAILABLE = -6;
		/** 其它异常 */
		public static int ERROR_OTHER = -7;
	}
	
	/**
	 * @author linpeita@centerm.com
	 * @time 2017/03/06 10:55
	 * @func 泰国身份证常见错误码
	 */
	public interface THAIDCARD{
		/**读取身份证成功*/
		public static int SUCCESS_READ_IDCARD = 0x00;
		/**设备繁忙*/
		public static int ERROR_DEVICE_IS_BUSY = 0x01;
		/**设备超时*/
		public static int ERROR_TIMEOUT = 0x02;
		/**操作被取消*/
		public static int ERROR_CANCELED = 0x03;
		/** 未获取到IC卡设备 */
		public static int ERROR_ICCARD_DEVICE = -1;
		/** 未检测到身份证 */
		public static int ERROR_CARD_STATUS = -2;
		/** IC卡复位失败 */
		public static int ERROR_RESET = -3;
		/** 读身份证证异常 */
		public static int ERROR_READ_IDCARD = -4;
		/** 程序异常 */
		public static int ERROR_APP = -5;
	}
}
