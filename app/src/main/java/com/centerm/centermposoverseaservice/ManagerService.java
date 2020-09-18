package com.centerm.centermposoverseaservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import com.centerm.smartpos.util.LogUtil;


/**
 * @author wangwenxun@centerm.com
 * @date 2018/4/3
 */

public class ManagerService extends Service implements Runnable {
    private HandlerThread asyncThread = null;
    private Handler asyncHandler = null;
    private DeviceManager manager = null;

    @Override
    public void onCreate() {
        LogUtil.print("CentermPosOverseaSerive--wwx-->>onCreate");
        super.onCreate();
    }


    /**
     * 获取Looper对象
     */
    public Looper getAsyncLooper() {
        if (asyncHandler == null) {
            asyncThread = new HandlerThread("CENTERM_POS_OVERSEA_SERVICE");
            asyncThread.start();
        }
        return asyncThread.getLooper();
    }


    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.print("CentermPosOverseaSerive--wwx-->>onBinder");
        asyncHandler = new Handler();
        manager = DeviceManager.getInstance(this.getApplicationContext());
        return manager;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.print("CentermPosOverseaSerive--wwx-->>onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onLowMemory() {
        LogUtil.print("CentermPosOverseaSerive--wwx-->>onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        LogUtil.print("CentermPosOverseaSerive--wwx-->onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.print("CentermPosOverseaSerive--wwx-->>onUnbind");
        asyncHandler = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogUtil.print("CentermPosOverseaSerive--wwx-->>onDestroy");
        super.onDestroy();
    }


    @Override
    public void run() {

    }
}
