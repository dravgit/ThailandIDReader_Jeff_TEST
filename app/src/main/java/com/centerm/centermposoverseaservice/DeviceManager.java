package com.centerm.centermposoverseaservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;

import com.centerm.centermposoverseaservice.Thailand.AidlIdCardThaService;
import com.centerm.mid.imp.socketimp.DeviceFactory;
import com.centerm.smartpos.aidl.sys.AidlDeviceManager;
import com.centerm.smartpos.util.LogUtil;

import java.util.HashMap;

/**
 * @author wangwenxun@centerm.com
 * @time 2018/04/13
 */
public class DeviceManager extends AidlDeviceManager.Stub implements
		DeathRecipient {
	// 服务管理者
	private static DeviceManager manager = null;
	// 上下文对象
	private Context context = null;
	//　服务缓存器
	private HashMap<Integer, IBinder> serviceCache = null;

	private DeviceManager(Context context) {
		this.context = context;
		serviceCache = new HashMap<Integer, IBinder>();
		DeviceFactory.setCtx(context);
	}

	@SuppressLint("UseSparseArrays")
	public static DeviceManager getInstance(Context context) {
		if (null == manager) {
			manager = new DeviceManager(context);
		}
		return manager;
	}


	@Override
	public IBinder getDevice(int deviceType) throws RemoteException {
 		LogUtil.print(" getDevice(String deviceName)方法运行了");
		LogUtil.print("deviceType是" + deviceType);
		IBinder binder = null;
		if (serviceCache.containsKey(deviceType)) {
			// 当前缓存中有该服务，直接返回
			return serviceCache.get(deviceType);
		}
		/**
		 * 在此处实例化具体所需的服务，并塞入缓存中。到时候如果缓存中有就直接调用。缓存中如果没有就新建一个服务并重新塞入缓存中
		 */
		if (deviceType == com.centerm.centermposoversealib.constant.Constant.OVERSEA_DEVICE_CODE.OVERSEA_DEVICE_TYPE_THAILAND_ID){
		    binder = (IBinder) AidlIdCardThaService.getInstance(this.context);
        }
 		serviceCache.put(deviceType, binder);
		return binder;
	}

	@Override
	public void destoryDeivce(int device) throws RemoteException {
		if (null != serviceCache.get(device)) {
			serviceCache.remove(device);
		}
	}

	@Override
	public void binderDied() {
		// 服务挂掉发送广播
	}

	@Override
	public String getVersion() throws RemoteException {
		// TODO Auto-generated method stub
		return "2.0.0";
	}
}

