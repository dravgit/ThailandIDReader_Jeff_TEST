// AidlDataZone.aidl
package com.centerm.centermposoversealib.dataZone;

// Declare any non-default types here with import statements

interface AidlDataZone {

    /**
     *  唤醒默认数据区域，可以进行读写
     *  默认区域为Data/CustomZone/zoneName
     */
    boolean openDataZone(String password,String zoneName);

    /**
     * 关闭指定数据区域，无法进行读写
     * 默认区域为Data/CustomZone/zoneName
     */
    boolean lockDataZone(String password,String zoneName);
}
