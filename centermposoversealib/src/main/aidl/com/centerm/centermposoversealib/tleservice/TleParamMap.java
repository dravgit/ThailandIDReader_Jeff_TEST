package com.centerm.centermposoversealib.tleservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/**
 * @author wangwenxun
 * @date 2018/5/10
 */
public class TleParamMap  implements Parcelable {
    /** 自定义拓展参数 */
    private HashMap<String, Object> paramMap = new HashMap<String, Object>();

    public HashMap<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(HashMap<String, Object> paramMap) {
        this.paramMap = paramMap;
    }


    public TleParamMap(){}

    public TleParamMap(Parcel in) {
        super();
        paramMap = in.readHashMap(HashMap.class.getClassLoader());
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(paramMap);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TleParamMap> CREATOR = new Creator<TleParamMap>() {
        @Override
        public TleParamMap createFromParcel(Parcel in) {
            TleParamMap tleParamMap = new TleParamMap(in);
            return tleParamMap;
        }

        @Override
        public TleParamMap[] newArray(int size) {
            return new TleParamMap[size];
        }
    };
}
