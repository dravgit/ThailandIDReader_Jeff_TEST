package com.centerm.centermposoversealib.util;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PropertiesUtil {
    
    private static final String TAG = "PropertiesUtil";
    /**
     * 
     * @param className 需要反射类的名字 
     * @return
     * @throws ClassNotFoundException
     */
    private static Class creatClassObject(String className) throws ClassNotFoundException{
        Class cls = Class.forName(className);
        return cls;
    }
    /**
     * 得到系统属性key值对应的value值
     * @param cls 反射类
     * @param key 系统属性key值
     * @return
     */
    public static String getSystemProperties(String key){
        String value = null;
        try {
            Class cls = creatClassObject("android.os.SystemProperties");
            Method hideMethod = cls.getMethod("get",String.class);
            Object object = cls.newInstance();
            value = (String) hideMethod.invoke(object, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    /**
     * 查看反射类的 所有方法和常量
     * @param cls
     */
    static public void printMethod(Class cls){
        Log.d(TAG, "--------printMethod-------");
         Method[] hideMethod = cls.getMethods();
         //取得所有方法
         int i = 0;
         for ( i=0 ; i <hideMethod.length; i++) {
             Log.d(TAG, hideMethod[i].getName().toString());
         }
         //取得所有常量
          Field[] allFields = cls.getFields();  
          for (i = 0; i < allFields.length; i++) {  
             Log.d(TAG, allFields[i].getName());  
        }  
    }
}

