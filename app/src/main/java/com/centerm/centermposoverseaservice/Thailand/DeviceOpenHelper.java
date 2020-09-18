package com.centerm.centermposoverseaservice.Thailand;

import java.util.ArrayList;
import java.util.List;

public abstract class DeviceOpenHelper {
	private List<Integer> mOpenedPids = new ArrayList<Integer>();
	private boolean mIsOpened = false;

	public boolean open(int pid){
		if(!mOpenedPids.contains(pid)){
			if(mIsOpened){
				mOpenedPids.add(pid);
			}else{
				try {
					openDevice();
					mIsOpened = true;
					mOpenedPids.add(pid);
				} catch (Exception e) {
					e.printStackTrace();
					if(e.toString().trim().equals("com.centerm.mid.exception.CentermApiException$PINPadException")){
                    	return true;
                    }else{
    					return false;
                    }
				}
			}
		}
		return true;
	}

	public boolean close(int pid) {
		if(mOpenedPids.contains(pid)){
			if(mOpenedPids.size() == 1){
				try {
					closeDevice();
					mIsOpened = false;
					mOpenedPids.remove(Integer.valueOf(pid));
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}else{
				mOpenedPids.remove(Integer.valueOf(pid));
			}
		}
		return true;
	}

	public boolean isOpened(int pid){
		return mOpenedPids.contains(pid);
	}

	public void closeAll(){
		if(mOpenedPids.size() > 0){
			mOpenedPids.clear();
		}
		if(mIsOpened){
			try {
				mIsOpened = false;
				closeDevice();
				//mIsOpened = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract void openDevice() throws Exception;
	protected abstract void closeDevice() throws Exception;
}
