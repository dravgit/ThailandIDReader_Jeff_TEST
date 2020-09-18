package com.centerm.centermposoverseaservice.Thailand;

import android.os.RemoteException;

/**
 * @author qiuchunhua@centerm.com
 * @date 2018/11/23 14:39
 */
public interface ResultCallBack {
    void onResult(int code, Object obj) throws RemoteException;
}
