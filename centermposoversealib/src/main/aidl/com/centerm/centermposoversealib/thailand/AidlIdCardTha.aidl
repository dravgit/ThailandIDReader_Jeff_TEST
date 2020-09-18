package com.centerm.centermposoversealib.thailand;
import com.centerm.centermposoversealib.thailand.AidlIdCardThaListener;
import com.centerm.centermposoversealib.thailand.ThaiInfoListerner;
import com.centerm.centermposoversealib.thailand.ThaiPhotoListerner;

interface AidlIdCardTha{

//    /** 搜索身份证  */
	void searchIDCard(int timeout,AidlIdCardThaListener listener);

    /**仅获取身份证的信息(不包含图片)**/
	void searchIDCardInfo(int timeout,ThaiInfoListerner listener);

    /**仅获取身份证的图片(不包含其他信息)**/
	void searchIDCardPhoto(int timeout,ThaiPhotoListerner listener);

	/**停止搜索*/
    void stopSearch();
}