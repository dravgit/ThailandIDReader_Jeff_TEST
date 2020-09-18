package com.centerm.centermposoversealib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.TimeoutException;

/**
 * 用于兼容版本的类（新Jar包-旧服务情况）
 * @time 2017/02/18 17:02 
 * @author liqiaoli@centerm.com
 * @func PinInfo类、CardTransData类、CardLoadLog类
 */
public class CompactUtil {
	//liqiaoli@centerm.com 2017/02/18 21:58 增加常量 
	//新增两个属性
	public final static int PIN_MODE_1 = 1;
	//新增三个属性
	public final static int PIN_MODE_2 = 2;
	//无参数
	public final static int PIN_MODE_3 = 3;
	//新增一个属性
	public final static int TRANSDATA_1 = 1;
	//无新增
	public final static int TRANSDATA_2 = 2;
	//未新增
	public final static int CARDLOADLOG_1 =1;
	//新增3个属性
	public final static int CARDLOADLOG_2 = 2;	
	//读取项目ID路径
	private final String IDPath = "/data/ct/ct_product_info";
	
	private static CompactUtil mInstance;
	private PackageManager mPm;
	private static int mPinInfo = -1;
	private static int mCardLoadLog = -1;
	private static int mTransData = -1;
	
	//2017-07-21  删除ShellMonitor，但又想使用其中的功能去更改权限读取项目ID  wangwenxun@centerm.com
		private final static String SOCKET_ADDRESS = "/tmp/shell_monitor_sock";
		private static final LocalSocketAddress sAddress = new LocalSocketAddress(
				SOCKET_ADDRESS, LocalSocketAddress.Namespace.FILESYSTEM);
		private InputStream in;
		private OutputStream out ;
		private LocalSocket socketfd;
		private boolean readflag = true;
		private byte[] buffer = new byte[1];
		private int timeout = 30000;
		//2017-07-21

	public static CompactUtil instance(Context c) {
		if (mInstance == null) {
			mInstance = new CompactUtil(c);
		}
		return mInstance;
	}

	private CompactUtil(Context c) {
		mPm = c.getPackageManager();
	}

	public String getVerionName() {  //2017-02-23 11:16 由private改为public,方便其他地方调用 wengtao@centerm.com
		try {
			PackageInfo info = mPm.getPackageInfo(
					"com.centerm.smartposservice", 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 判断该版本增加的属性
	 * @author liqiaoli@centerm 
	 * @time 2017/02/18 22:00
	 * @return 
	 *  PIN_MODE_1: PinInfo(新增isShowInputBox、isRandomKeybord)， ,
	 *  PIN_MODE_2: PinInfo(新增isShowInputBox、isRandomKeybord、isV8Mask三个参数)
	 *  PIN_MODE_2: PinInfo(无新增参数)
	 */
	public int chonsePinInfo() {
		if (mPinInfo != -1) {
			return mPinInfo;
		}
		String verName = getVerionName();
		Log.d("ver ", verName);
		String projectId = getProjectID();
		if ((verName.compareTo("3.0.7") == 0)
				|| ((verName.compareTo("3.0.7.1") == 0) && ((projectId != null) && projectId
						.equals("0000C960F_CBCXXTCSI")))) {
			Log.d("ver pin", "2");
			return PIN_MODE_1;
		} else if (verName.compareTo("3.0.7.1_20160831") == 0
				|| ((verName.compareTo("3.0.7.1") == 0))) {
			Log.d("ver pin", "0");
			return PIN_MODE_3;
		}

		Log.d("ver pin", "3");
		return PIN_MODE_2;
	}
	
	/**
	 * 判断该版本增加的属性
	 * @author liqiaoli@centerm 
	 * @time 2017/02/18 22:00
	 * @return 
	 * TRANSDATA_1: EmvTransData(新增isOnlyOffline),
	 * TRANSDATA_2: EmvTransData(无新增)
	 */
	public int choseTransData() {
		if (mTransData != -1) {
			return mTransData;
		}

		String verName = getVerionName();
		Log.d("ver ", verName);
		if ((verName.compareTo("3.0.7.2_20161101") == 0)
				|| (verName.compareTo("3.0.7.3_20161124") == 0)
				||(verName.compareTo("3.0.7.2_20161107") == 0)) {
			Log.d("ver transdata", "1");
			return TRANSDATA_1;
		}

		Log.d("ver transdata", "0");
		return TRANSDATA_2;
	}
	
	/**
	 * 获取项目ID，用于判断特殊版本
	 * @author liqiaoli@centerm.com
	 * @time 2017/02/19 14:48
	 * @return 项目ID
	 */
	private String getProjectID() {
		try {
			int ret = excuteCmd("chmod 777 " + IDPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}//删除shellMoniter改为直接调用
		
		try {
			//ShellmoniterUtil shellMonitor = new ShellmoniterUtil(); 
			//2017-07-21  删除shellmoniter的方法  wangwenxun@centerm.com
			String code = "";
			String ProjectID;
			File file = new File(IDPath);
			if (file == null || !file.exists() || !file.isFile()
					|| !file.canRead()) {
				return null;
			}

			String myCode = (null != code) && (!"".equals(code)) ? code
					: "UTF8";
			if ("GB-2312".equals(code) || "OTHER".equals(code)) {
				myCode = "GBK";
			}
			InputStreamReader input = new InputStreamReader(
					new FileInputStream(file.getPath()), myCode);

			BufferedReader br = new BufferedReader(input);
			StringBuilder builder = new StringBuilder();
			try {
				while ((ProjectID = br.readLine()) != null) {
					builder.append(ProjectID);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			br.close();
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//begin 2017-07-21 后来我们想了一个办法，把shellMonitor中的方法和变量移到这个类中改为私有的  wangwenxun@centerm.com
		private void open() throws Exception {
			// TODO Auto-generated method stub
			Log.d("","开始创建本地socket....");
			socketfd = new LocalSocket();
			socketfd.connect(sAddress);
			in = socketfd.getInputStream();	
			out = socketfd.getOutputStream();
			Log.d("","本地socket创建成功，状态已连接");
		}
		
		private int excuteCmd(String cmd) throws Exception {
			// TODO Auto-generated method stub
			open();
			out.write(cmd.getBytes());
			out.flush();
			long startTime = System.currentTimeMillis();
			while(true)
			{
				Thread.sleep(100);
				long endTime = System.currentTimeMillis();
				if((endTime - startTime)>timeout)
				{
					throw new TimeoutException();
				}
				if(in.available()>0)
				{
					in.read(buffer);
					break;
				}
			}
			close();
			return buffer[0];
		}
		
		private void close() throws Exception {
			// TODO Auto-generated method stub
			in.close();
			out.close();
			socketfd.close();
		}

		private void setTimeout(int timeout) throws Exception {
			// TODO Auto-generated method stub
			this.timeout = timeout;
		}
		private boolean receiving;
		
		private void stopReceiving() throws Exception {
			// TODO Auto-generated method stub
			receiving = false;
		}
		//end 2017-07-21
}
