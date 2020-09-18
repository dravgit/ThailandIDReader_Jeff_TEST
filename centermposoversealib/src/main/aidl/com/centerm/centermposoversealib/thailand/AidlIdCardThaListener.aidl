package com.centerm.centermposoversealib.thailand;
import com.centerm.centermposoversealib.thailand.ThiaIdInfoBeen;

interface AidlIdCardThaListener{
   /**找到IC卡*/
   void onFindIDCard(in ThiaIdInfoBeen idinfoBean);
   /**超时*/
   void onTimeout();
   /**出错*/
   void onError(int errorCode, String errorMsg);
}