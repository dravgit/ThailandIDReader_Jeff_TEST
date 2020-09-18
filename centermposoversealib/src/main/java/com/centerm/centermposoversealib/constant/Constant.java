package com.centerm.centermposoversealib.constant;
/**
 * 存储设备的常量类
 * 
 * @author Tianxiaobo
 * 
 */
public interface Constant {
	/**
	 * 设备类型常量
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface DEVICE_TYPE {
		public static int OFFSET = 0x2000;
		/** 逻辑设备: PBOC ，该设备对应 IPboc 接口 */
		public static final int DEVICE_TYPE_PBOC = OFFSET + 1;
		/** 逻辑设备: magcard ，该设备对应 IMagCardDev 接口 */
		public static final int DEVICE_TYPE_MAGCARD = OFFSET + 2;
		/** 逻辑设备: 凭条打印机 */
		public static final int DEVICE_TYPE_PRINTERDEV = OFFSET + 3;
		/** 逻辑设备:接触式IC卡设备 */
		public static final int DEVICE_TYPE_ICCARD = OFFSET + 4;
		/** 逻辑设备:密码键盘接口 */
		public static final int DEVICE_TYPE_PINPAD = OFFSET + 5;
		/** 逻辑设备:外接密码键盘接口 */
		public static final int DEVICE_TYPE_EXPINPAD = OFFSET + 6;
		/** 非接卡设备 */
		public static final int DEVICE_TYPE_RFCARD = OFFSET + 7;
		/** 串口设备1 */
		public static final int DEVICE_TYPE_SERIALPORT1 = OFFSET + 8;
		/** 串口设备2 */
		public static final int DEVICE_TYPE_SERIALPORT2 = OFFSET + 9;
		/*** 逻辑设备:输入法操作接口 */
		public static final int DEVICE_TYPE_INPUTMETHOD = OFFSET + 10;
		/** 系统设备 */
		public static final int DEVICE_TYPE_SYS = OFFSET + 11;
		/** 钱箱设备 */
		public static final int DEVICE_TYPE_CASHBOX = OFFSET + 12;
		/** 客显示设备 */
		public static final int DEVICE_TYPE_CUSTOMDISPLAY = OFFSET + 13;
		/** M1卡设备 */
		public static final int DEVICE_TYPE_MIFARE = OFFSET + 14;
		/** MODEM设备 */
		public static final int DEVICE_TYPE_MODEM = OFFSET + 15;
		/** PSAM卡设备1 */
		public static final int DEVICE_TYPE_PSAM1 = OFFSET + 16;
		/** PSAM卡设备2 */
		public static final int DEVICE_TYPE_PSAM2 = OFFSET + 17;
		/** PSAM卡设备3 */
		public static final int DEVICE_TYPE_PSAM3 = OFFSET + 18;
		/** 辅屏控制接口 */
		public static final int DEVICE_TYPE_OLED = OFFSET + 19;
		/** 身份证设备 */
		public static final int DEVICE_TYPE_IDCARD = OFFSET + 20;
		/** 逻辑设备：PBOC新接口， 该设备对应EMVBinder 接口    Add By Zmz */
		public static final int DEVICE_TYPE_PBOC2 = OFFSET + 21;
		/** 逻辑设备:C1010密码键盘接口 */
		public static final int DEVICE_TYPE_METAL = OFFSET + 22;
		/** 立式IC卡设备 */
		public static final int DEVICE_TYPE_VERTICAL_ICCARD = OFFSET + 23;
		/** LCD设备 */
		public static final int DEVICE_TYPE_LCD = OFFSET + 24;
		/** 安全模块 */
		public static final int DEVICE_TYPE_HSM = OFFSET + 25;
		/** MEM4442卡设备 */
		public static final int DEVICE_TYPE_MEM4442 = OFFSET + 26;
		/** MEM102卡 */
		public static final int DEVICE_TYPE_MEM102 = OFFSET + 27;
		/** MEM1608卡 */
		public static final int DEVICE_TYPE_MEM1608 = OFFSET + 28;
		/** MEMAT24C02卡 */
		public static final int DEVICE_TYPE_MEMAT24C02 = OFFSET + 29;
		/** 版本读取 */
		public static final int DEVICE_TYPE_VERSION_HELPER = OFFSET + 30;
		/** 一维码模组 */
		public static final int DEVICE_TYPE_BARCODE = OFFSET + 31;
		/** 立式MEM4442卡设备 */
		public static final int DEVICE_TYPE_VERTICAL_MEM4442 = OFFSET + 32;
		/** 立式MEM102卡 */
		public static final int DEVICE_TYPE_VERTICAL_MEM102 = OFFSET + 33;
		/** 立式MEM1608卡 */
		public static final int DEVICE_TYPE_VERTICAL_MEM1608 = OFFSET + 34;
		/** 立式MEMAT24C02卡 */
		public static final int DEVICE_TYPE_VERTICAL_MEMAT24C02 = OFFSET + 35;
		/** 语音播放设备 */
		public static final int DEVICE_TYPE_SOUNDPLAYER = OFFSET + 36;  //2017/02/28 14:29 增加语音播放设备 wengtao@centerm.com
	    /** 非主线程EMV设备*/
		public static final int DEVICE_TYPE_PBOC3 = OFFSET + 37; // 2017-04-17 增加非主线程执行的EMV设备 wangwenxun@centerm.com 
		/** 二维码扫码设备 */
		public static final int DEVICE_TYPE_QUICKSCAN = OFFSET + 38; //2017-04-17 为了进行so库激活而增加扫码设备 wangwenxun@centerm.com
		/** NFC身份证设备 */
		public static final int DEVICE_TYPE_IDCARD2 = OFFSET + 39; //2017-05-18 wengtao@centerm.com
		/** 4428卡设备 */
		public static final int DEVICE_TYPE_MEM4428 = OFFSET + 40;
		/** 立式4428设备 */
		public static final int DEVICE_TYPE_VERTICAL_MEM4428 = OFFSET + 41;
	    /** 屏幕签名设备*/
		public static final int DEVICE_TYPE_SIGN = OFFSET + 42;
		/** 非接身份证设备*/
		public static final int DEVICE_TYPE_RF_IDCARD = OFFSET + 43;
		/** wifi探针设备 */
		public static final int DEVICE_TYPE_WIFIPROBE = OFFSET + 44;
		/** 泰国身份证设备 */
		public static final int DEVICE_TYPE_THAILAND_IDCARD = OFFSET + 45;//2018-03-06 泰国身份证设备 linpeita@centerm.com

	}


	public interface OVERSEA_DEVICE_CODE{
		public static int OFFSETS = 0x4000;
		public static final int OVERSEA_DEVICE_TYPE_DATAZONE = OFFSETS + 1;
		public static final int OVERSEA_DEVICE_TYPE_THAILAND_ID = OFFSETS +2;

	}

	/**
	 * 设备错误码常量
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface DEVICE_ERR_CODE {
		/** 保留，表示无错误 */
		public static final int ERROR_CODE_OK = 0;
		/** 未定义的错误，msg 参数给出具体的错误信息。 */
		public static final int ERROR_CODE_UNDEINE_ERROR = -1;
		/** 打开设备失败，设备被占用 */
		public static final int ERROR_CODE_DEVICE_OCCUPIED = -2;
		/** 设备不存在 */
		public static final int ERROR_CODE_DEVICE_NOT_EXIST = -3;
		/** 设备未打开 */
		public static final int ERROR_CODE_DEVICE_CLOSED = -4;
	}

	/**
	 * 打印机打印排列
	 * @author Administrator
	 *
	 */
	public interface ALIGN{
		/**左对齐*/
		public static final int LEFT = 0x00;
		/**居中*/
		public static final int CENTER = 0x01;
		/**右对其*/
		public static final int RIGHT = 0x02;
	}
	
	/**
	 * 打印机灰度设置
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface COLOR_DEPTH {
		/** 1级灰度设置 */
		public static final int DEPTH_1 = 0x00;
		/** 2级灰度设置 */
		public static final int DEPTH_2 = 0x01;
		/** 3级灰度设置 */
		public static final int DEPTH_3 = 0x02;
		/** 4级灰度设置 */
		public static final int DEPTH_4 = 0x03;
	}

	/**
	 * 输入法常量
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface INPUT_METHOD_MANAGER {
		public static final int INPUTMETHOD_TYPE_123 = 0;
		public static final int INPUTMETHOD_TYPE_123_ABC = 4;
		public static final int INPUTMETHOD_TYPE_123_ABC_CAP = 16;
		public static final int INPUTMETHOD_TYPE_123_ABC_NOSYM = 5;
		public static final int INPUTMETHOD_TYPE_123_ABC_NOSYM_CAP = 17;
		public static final int INPUTMETHOD_TYPE_ABC = 3;
		public static final int INPUTMETHOD_TYPE_ABC_123 = 8;
		public static final int INPUTMETHOD_TYPE_ABC_123_CAP = 19;
		public static final int INPUTMETHOD_TYPE_ABC_123_NOSYM = 9;
		public static final int INPUTMETHOD_TYPE_ABC_123_NOSYM_CAP = 20;
		public static final int INPUTMETHOD_TYPE_ABC_CAP = 15;
		public static final int INPUTMETHOD_TYPE_ABC_NOSYM = 7;
		public static final int INPUTMETHOD_TYPE_ABC_NOSYM_CAP = 18;
		public static final int INPUTMETHOD_TYPE_HANDWRITING = 2;
		public static final int INPUTMETHOD_TYPE_HANDWRITING_NOSYM = 10;
		public static final int INPUTMETHOD_TYPE_HANDWRITING_PINYIN_123 = 21;
		public static final int INPUTMETHOD_TYPE_PINYIN = 1;
		public static final int INPUTMETHOD_TYPE_PINYIN_HANDWRITING = 11;
		public static final int INPUTMETHOD_TYPE_PINYIN_HANDWRITING_123 = 13;
		public static final int INPUTMETHOD_TYPE_PINYIN_HANDWRITING_123_NOSYM = 14;
		public static final int INPUTMETHOD_TYPE_PINYIN_HANDWRITING_NOSYM = 12;
		public static final int INPUTMETHOD_TYPE_PINYIN_NOSYM = 6;
	}

	//去除多余定义
	/*// PBOC交易类型定义
	public interface PBOC_TRANS_TYPE {
		*//** 消费 *//*
		public static final byte TRANSTYPE_CONSUME = 0x01;
		*//** 服务 *//*
		public static final byte TRANSTYPE_SERVICE = 0x02;
		*//** 现金 *//*
		public static final byte TRANSTYPE_CASH = 0x03;
		*//** 返现 *//*
		public static final byte TRANSTYPE_CASH_BACK = 0x04;
		*//** 查询 *//*
		public static final byte TRANSTYPE_QUERY = 0x05;
		*//** 转账 *//*
		public static final byte TRANSTYPE_TRANSFER = 0x06;
		*//** 管理 *//*
		public static final byte TRANSTYPE_MANAGEMENT = 0x07;
		*//** 存款 *//*
		public static final byte TRANSTYPE_DEPOSIT = 0x08;
		*//** 支付 *//*
		public static final byte TRANSTYPE_PAYMENT = 0x09;
		*//** 预授权 *//*
		public static final byte TRANSTYPE_PRE_AUTHORIZATION = 0x0A;
		*//** 指定账户圈存 *//*
		public static final byte TRANSTYPE_ACCOUNT_LOAD = 0x0B;
		*//** 非指定账户圈存 *//*
		public static final byte TRANSTYPE_NON_ACCOUNT_LOAD = 0x0C;
		*//** 现金圈存 *//*
		public static final byte TRANSTYPE_CASH_LOAD = 0x0D;
		*//** 现金充值撤销 *//*
		public static final byte TRANSTYPE_CASH_REVOKED = 0x0E;
	}*/

	/**
	 * LED灯类型
	 * 
	 * @author Tianxiaobo
	 * 
	 */
	public interface LEDTYPE {
		public final static int LED_OFF = 0;
		/** 单价 */
		public final static int LED_PRICE = 1;
		/** 总计 */
		public final static int LED_TOTAL = 2;
		/** 收款 */
		public final static int LED_CHARGE = 3;
		/** 找零 */
		public final static int LED_BALANCE = 4;
	}

	public interface PSAM_DEV_ID {
		public final static byte ID_1 = (byte) 0x01;
		public final static byte ID_2 = (byte) 0x02;
		public final static byte ID_3 = (byte) 0x03;
	}

	public interface PBOC{
	    /**pboc流程每一步返回的结果*/
	    public static final String result = "result";
	    /**pboc流程每一步返回结果附带信息*/
		public static final String info = "info";
	}
	
	public interface HSM_OBJECT{
		public static final int HSM_OBJECT_TYPE_private_key = 0;
		public static final int HSM_OBJECT_TYPE_public_key = 1;
		public static final int HSM_OBJECT_TYPE_cert = 2;
	}
	
	public interface HSM_NATIVE{
		public static final int HSM_OBJECT_DATA_TYPE_pem = 0;
		public static final int HSM_OBJECT_DATA_TYPE_der = 1;
		public static final int HSM_OBJECT_DATA_TYPE_p7b = 2;
		public static final int HSM_OBJECT_DATA_TYPE_pfx = 3;
	}
	
	/**
	 * @author wengtao@centerm.com
	 * @time 2017/03/09 16:29
	 * @func 语音播放类型常量
	 */
	public interface VOICE_TYPE {
		public static final int SWIPE_CARD = 0;
		public static final int INSERT_CARD = 1;
		public static final int WAVE_CARD = 2;
		public static final int SWIPE_WAVE_CARD = 3;
		public static final int SWIPE_INSERT_CARD = 4;
		public static final int SWIPE_INSERT_WAVE_CARD = 5;
		public static final int TRANSACTION_DONT_LEAVE_CARD = 6;
		public static final int TRANSACTION_OK_LEAVE_CARD = 7;
		public static final int INPUT_AMOUNT = 8;
		public static final int INPUT_PASSWORD = 9;
		public static final int PASSWORD_LENGTH_NO_ENOUGH = 10;
		public static final int CONFIRM_CARDNO_AMOUNT = 11;
		public static final int TRANSACTION_WAIT_PLEASE = 12;
		public static final int PRINTTING_WAIT_PLEASE = 13;
		public static final int PASSWORD_WRONG_RETRANSACTION = 14;
		public static final int PRINTER_LACK_PAPER = 15;
		public static final int LOW_POWER_NO_PRINT = 16;
		public static final int TRANSACTION_SUCCESS = 17;
		public static final int COMMUNICATION_BREAKDOWN = 18;
		public static final int SIGN_PLEASE = 19;
		public static final int BALANCE_PLEASE = 20;
		public static final int AUTOMATIC_SUCCESS = 21;
		public static final int ACTIVATION_SUCCESS = 22;
		public static final int NUM_ZERO = 23;
		public static final int NUM_ONE = 24;
		public static final int NUM_TWO = 25;
		public static final int NUM_THREE = 26;
		public static final int NUM_FOUR = 27;
		public static final int NUM_FIVE = 28;
		public static final int NUM_SIX = 29;
		public static final int NUM_SEVEN = 30;
		public static final int NUM_EIGHT = 31;
		public static final int NUM_NINE = 32;
		public static final int NUM_TEN = 33;
		public static final int NUM_HUNDRED = 34;
		public static final int NUM_THOUSAND = 35;
		public static final int NUM_TEN_THOUSAND = 36;
		public static final int NUM_TEN_MILLION = 37;
		public static final int NUM_ZHAO = 38;
		public static final int POINT = 39;
		public static final int YUAN = 40;
		public static final int JIAO = 41;
		public static final int FEN = 42;
	}
	
	public interface BarCodeFormat{
		public static final int AZTEC = 0;
		public static final int CODABAR = 1;
		public static final int CODE_128 = 2;
		public static final int CODE_39 = 3;
		public static final int CODE_93 = 4;
		public static final int DATA_MATRIX = 5;
		public static final int EAN_13 = 6;
		public static final int EAN_8 = 7;
		public static final int ITF = 8;
		public static final int PDF_417 = 9;
		public static final int QR_CODE = 10;
		public static final int UPC_A = 11;
		public static final int UPC_E = 12;
	}
}
