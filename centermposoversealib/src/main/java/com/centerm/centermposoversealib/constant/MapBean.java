package com.centerm.centermposoversealib.constant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class MapBean implements Parcelable {
	public HashMap<String, Object> paramMap = new HashMap<String, Object>();

	public HashMap<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeMap(paramMap);
	}

	public MapBean(Parcel source) {
		paramMap = source.readHashMap(HashMap.class.getClassLoader());
	}

	public static final Creator<MapBean> CREATOR = new Creator<MapBean>() {

		@Override
		public MapBean createFromParcel(Parcel source) {
			MapBean MapBean = new MapBean(source);
			return MapBean;
		}

		@Override
		public MapBean[] newArray(int size) {
			return new MapBean[size];
		}
	};

	public MapBean() {
	}

}
