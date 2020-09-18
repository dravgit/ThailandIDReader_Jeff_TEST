package com.centerm.centermposoverseaservice.Thailand;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;

import com.centerm.centermposoversealib.util.HexUtil;
import com.centerm.mid.imp.socketimp.DeviceFactory;
import com.centerm.mid.inf.ICCardDevInf;
import com.centerm.smartpos.aidl.iccard.AidlICCard;
import com.centerm.smartpos.util.LogUtil;

/**
 * 接触式IC卡设备
 */
public class ICCardService extends AidlICCard.Stub implements DeathRecipient {

    /**
     * 接触式IC卡设备
     */
    private ICCardDevInf iccard = null;

    private Context context = null;
    private static ICCardService iccardService = null;

    private DeviceOpenHelper mDeviceOpenHelper = new DeviceOpenHelper() {

        @Override
        protected void openDevice() throws Exception {
            iccard.open();
        }

        @Override
        protected void closeDevice() throws Exception {
            iccard.close();
        }
    };

    private ICCardService(Context context) {
        this.context = context;
        try {
            iccard = DeviceFactory.getICCardDev();
            printLog("初始化接触式ＩＣ卡成功");
        } catch (Exception e) {
            e.printStackTrace();
            printLog("初始化IC卡设备异常");
        }
    }

    public static ICCardService getInstance(Context context) {
        if (null == iccardService) {
            iccardService = new ICCardService(context);
        }
        return iccardService;
    }


    @Override
    public void close() throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        if (null != iccard) {
            boolean isClose = mDeviceOpenHelper.close(getCallingPid());
            if (isClose) {
                printLog("关闭IC卡设备成功");
            } else {
                printLog("关闭IC卡设备异常");
            }
        }
    }

    @Override
    public void halt() throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        if (null != iccard) {
            try {
                iccard.halt();
                printLog("中断IC卡设备成功");
            } catch (Exception e) {
                e.printStackTrace();
                printLog("中断IC卡设备异常");
            }
        }
    }

    @Override
    public void open() throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        if (null != iccard) {
            boolean isOpen = mDeviceOpenHelper.open(getCallingPid());
            if (isOpen) {
                printLog("打开IC卡设备成功");
            } else {
                printLog("打开IC卡设备异常");
            }
        }
    }

    @Override
    public byte[] reset() throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        byte[] retData = null;
        if (null != iccard) {
            try {
                if (!mDeviceOpenHelper.isOpened(getCallingPid())) {
                    return retData;
                }
                retData = iccard.cardReset();
                printLog("IC卡设备复位成功 复位结果:" + HexUtil.bytesToHexString(retData));
            } catch (Exception e) {
                e.printStackTrace();
                printLog("IC卡设备复位异常" + e.getLocalizedMessage());
            }
        }
        return retData;
    }

    @Override
    public byte[] send(byte[] arg0) throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        byte[] retData = null;
        if (null != iccard) {
            try {
                if (!mDeviceOpenHelper.isOpened(getCallingPid())) {
                    return retData;
                }
                retData = iccard.send(arg0);
                printLog("IC卡设备发送同步APDU指令成功，返回结果为:" + HexUtil.bytesToHexString(retData));
            } catch (Exception e) {
                e.printStackTrace();
                printLog("IC卡设备发送同步APDU指令异常，返回结果为:" + e.getLocalizedMessage());
            }
        }
        return retData;
    }

    @Override
    public byte status() throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        byte retVal = 0x00;
        if (null != iccard) {
            try {
                if (!mDeviceOpenHelper.isOpened(getCallingPid())) {
                    return retVal;
                }
                retVal = iccard.status();
                printLog("接触式ＩＣ卡设备status返回结果为" + retVal);
            } catch (Exception e) {
                e.printStackTrace();
                printLog("接触式IC卡设备status异常" + e.getLocalizedMessage());
            }
        }
        return retVal;
    }

    @Override
    public byte[] sendAsync(byte[] arg0) throws RemoteException {
        checkPerssion();  //检查权限 2017/04/10 wengtao@centerm.com
        byte[] retData = null;
        if (null != iccard) {
            try {
                if (!mDeviceOpenHelper.isOpened(getCallingPid())) {
                    return retData;
                }
                retData = iccard.senAsync(arg0);
                printLog("IC卡设备发送异步APDU指令成功，返回结果为:"
                        + HexUtil.bytesToHexString(retData));
            } catch (Exception e) {
                e.printStackTrace();
                printLog("IC卡设备发送异步APDU指令异常，返回结果为:"
                        + e.getLocalizedMessage());
            }
        }
        return retData;
    }

    @Override
    public void binderDied() {
        Intent intent = new Intent();
        context.sendBroadcast(intent);
    }

    /**
     * 控制是否关闭日志输出
     */
    public static boolean logOff = true;

    /**
     * 输出日志
     *
     * @param log 日志
     */
    private void printLog(String log) {
        if (!logOff) {
            LogUtil.print(log);
        }
    }

    /**
     * @func 校验权限
     * @time 2017/04/10
     * @author wengtao@cneterm.com
     */
    private void checkPerssion() {
        //本程序不进行权限校验
//		if (!PermissionCheckUtility.checkPermission(context,
//				"android.permission.CPAYSDK_IC_MODULE")) {
//			LogUtil.print("应用没有权限访问该设备");
//			throw new SecurityException("应用没有权限访问该设备");
//		}
    }

}
