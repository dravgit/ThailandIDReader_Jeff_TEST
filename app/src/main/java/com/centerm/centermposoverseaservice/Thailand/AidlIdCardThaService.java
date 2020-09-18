package com.centerm.centermposoverseaservice.Thailand;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import com.centerm.centermposoversealib.constant.DeviceErrorCode;
import com.centerm.centermposoversealib.thailand.AidlIdCardThaListener;
import com.centerm.centermposoversealib.thailand.ThaiInfoListerner;
import com.centerm.centermposoversealib.thailand.ThaiPhotoListerner;
import com.centerm.centermposoversealib.thailand.ThiaIdInfoBeen;
import com.centerm.centermposoverseaservice.R;
import com.centerm.dev.error.DeviceBaseException;
import com.centerm.smartpos.aidl.iccard.AidlICCard;

/**
 * @author wangwenxun
 * @date 2018/10/15
 */
public class AidlIdCardThaService extends com.centerm.centermposoversealib.thailand.AidlIdCardTha.Stub {
    private AidlICCard icCard;
    private Context mContext;
    private volatile int mSearchingPid = 0;
    private volatile boolean stopSearch = false;
    private volatile int timeout = 60 * 1000;

    private static AidlIdCardThaService instance;

    public static AidlIdCardThaService getInstance(Context context) {
        if (instance == null) {
            instance = new AidlIdCardThaService(context);
        }
        return instance;
    }

    private AidlIdCardThaService(Context context) {
        mContext = context.getApplicationContext();
        icCard = ICCardService.getInstance(mContext);
    }

    @Override
    public void searchIDCard(int timeOut, final AidlIdCardThaListener listerner) throws RemoteException {
        if (listerner == null) {
            throw new RemoteException("AidlIdCardListener不能为null");
        }
        if (mSearchingPid != 0) {
            listerner.onError(DeviceErrorCode.THAIDCARD.ERROR_DEVICE_IS_BUSY, mContext.getString(R.string.tha_idcard_device_is_busy));
            return;
        }
        if (icCard == null) {
            listerner.onError(DeviceErrorCode.THAIDCARD.ERROR_ICCARD_DEVICE, mContext.getString(R.string.tha_idcard_iccard_no_exist));
            return;
        }
        timeout = timeOut <= 0 ? 60000 : timeOut;
        mSearchingPid = getCallingPid();
        new BaseWorkThread() {

            @Override
            void onTimeOut() throws RemoteException {
                listerner.onTimeout();
            }

            @Override
            void onReadyToWork() throws RemoteException, DeviceBaseException {
                ThaIDReader reader = new ThaIDReader(mContext, icCard);
                reader.readIDCard(new ResultCallBack() {
                    @Override
                    public void onResult(int code, Object obj) throws RemoteException {
                        Log.i("Chock", "code:" + code);
                        if (code == DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD) {
                            listerner.onFindIDCard(((ThiaIdInfoBeen) obj));
                        } else {
                            listerner.onError(code, getTipsString(code));
                        }
                    }
                });
            }
        }.start();
    }

    @Override
    public void searchIDCardInfo(int timeOut, final ThaiInfoListerner listerner) throws RemoteException {
        if (listerner == null) {
            throw new RemoteException("AidlIdCardListener不能为null");
        }
        if (mSearchingPid != 0) {
            listerner.onResult(DeviceErrorCode.THAIDCARD.ERROR_DEVICE_IS_BUSY, mContext.getString(R.string.tha_idcard_device_is_busy));
            return;
        }
        if (icCard == null) {
            listerner.onResult(DeviceErrorCode.THAIDCARD.ERROR_ICCARD_DEVICE, mContext.getString(R.string.tha_idcard_iccard_no_exist));
            return;
        }
        timeout = timeOut <= 0 ? 60000 : timeOut;
        mSearchingPid = getCallingPid();
        new BaseWorkThread() {

            @Override
            void onTimeOut() throws RemoteException {
                listerner.onResult(DeviceErrorCode.THAIDCARD.ERROR_TIMEOUT, "TIME_OUT");
            }

            @Override
            void onReadyToWork() throws RemoteException, DeviceBaseException {
                ThaIDReader reader = new ThaIDReader(mContext, icCard);
                reader.readIDCardInfoOnly(new ResultCallBack() {
                    @Override
                    public void onResult(int code, Object obj) throws RemoteException {
                        if (code == DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD) {
                            listerner.onResult(code, ((ThiaIdInfoBeen) obj).toJSONString());
                        } else {
                            listerner.onResult(code, getTipsString(code));
                        }
                    }
                });
            }
        }.start();
    }

    @Override
    public void searchIDCardPhoto(int timeOut, final ThaiPhotoListerner listerner) throws RemoteException {
        if (listerner == null) {
            throw new RemoteException("AidlIdCardListener不能为null");
        }
        if (mSearchingPid != 0) {
            listerner.onResult(DeviceErrorCode.THAIDCARD.ERROR_DEVICE_IS_BUSY, null);
            return;
        }
        if (icCard == null) {
            listerner.onResult(DeviceErrorCode.THAIDCARD.ERROR_ICCARD_DEVICE, null);
            return;
        }
        timeout = timeOut <= 0 ? 60000 : timeOut;
        mSearchingPid = getCallingPid();
        new BaseWorkThread() {
            @Override
            void onTimeOut() throws RemoteException {
                listerner.onResult(DeviceErrorCode.THAIDCARD.ERROR_TIMEOUT, null);
            }

            @Override
            void onReadyToWork() throws RemoteException, DeviceBaseException {
                ThaIDReader reader = new ThaIDReader(mContext, icCard);
                reader.readIDCardPhotoOnly(new ResultCallBack() {
                    @Override
                    public void onResult(int code, Object obj) throws RemoteException {
                        if (code == DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD) {
                            listerner.onResult(code, (Bitmap) obj);
                        } else {
                            listerner.onResult(code, null);
                        }
                    }
                });
            }
        }.start();
    }

    @Override
    public void stopSearch() throws RemoteException {
        if (mSearchingPid == getCallingPid()) {
            close();
        }
    }

    private void close() {
        mSearchingPid = 0;
        try {
            if (icCard != null) {
                icCard.close();
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        stopSearch = true;
    }

    private String getTipsString(int code) {
        switch (code) {
            case DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD:
                return "";
            case DeviceErrorCode.THAIDCARD.ERROR_CARD_STATUS:
                return mContext.getString(R.string.tha_idcard_undetected);
            case DeviceErrorCode.THAIDCARD.ERROR_RESET:
                return mContext.getString(R.string.tha_idcard_reset_failed);
            case DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD:
                return mContext.getString(R.string.tha_idcard_read_failed);
            case DeviceErrorCode.THAIDCARD.ERROR_APP:
                return mContext.getString(R.string.tha_idcard_app_exception);
            default:
                return mContext.getString(R.string.tha_idcard_unknown_return_code);
        }
    }

    abstract class BaseWorkThread extends Thread {
        private long time;

        private BaseWorkThread() {
            time = SystemClock.uptimeMillis();
        }

        @Override
        public void run() {
            try {
                icCard.open();
                stopSearch = false;
                while (!stopSearch && icCard.status() != 1) {
                    if (SystemClock.uptimeMillis() - time > timeout) {
                        if (mSearchingPid != 0) {
                            onTimeOut();
                        }
                        close();
                        return;
                    }
                    Thread.sleep(50);
                }
                if (!stopSearch) {
                    onReadyToWork();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            close();
        }

        /**
         * 等待卡片就位超时
         *
         * @throws RemoteException 远程异常
         */
        abstract void onTimeOut() throws RemoteException;

        /**
         * 可以下一步處理卡片數據了
         *
         * @throws RemoteException 远程异常
         */
        abstract void onReadyToWork() throws RemoteException, DeviceBaseException;
    }
}