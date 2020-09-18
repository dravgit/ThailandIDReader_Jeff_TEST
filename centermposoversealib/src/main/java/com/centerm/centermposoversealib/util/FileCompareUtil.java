package com.centerm.centermposoversealib.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author wangwenxun@centerm.com
 * @time 2017-05-02
 * @func compare the differece between two file context
 * @func 比较两个文件内容之间的异同
 *
 */

public class FileCompareUtil {
   
	 /** 
	   * 判断两个文件的内容是否相同，文件名要用绝对路径 
	   * @param fileName1 ：文件1的绝对路径 
	   * @param fileName2 ：文件2的绝对路径 
	   * @return 相同返回true，不相同返回false 
	   */  
	public static boolean isSameFile(String fileName1,String fileName2){  
	    FileInputStream fis1 = null;  
	    FileInputStream fis2 = null;  
	    try {  
	        fis1 = new FileInputStream(fileName1);  
	        fis2 = new FileInputStream(fileName2);  
	          
	        int len1 = fis1.available();//返回总的字节数  
	        int len2 = fis2.available();  
	          
	        if (len1 == len2) {//长度相同，则比较具体内容  
	            //建立两个字节缓冲区  
	            byte[] data1 = new byte[len1];  
	            byte[] data2 = new byte[len2];  
	              
	            //分别将两个文件的内容读入缓冲区  
	            fis1.read(data1);  
	            fis2.read(data2);  
	              
	            //依次比较文件中的每一个字节  
	            for (int i=0; i<len1; i++) {  
	                //只要有一个字节不同，两个文件就不一样  
	                if (data1[i] != data2[i]) {    
	                    return false;  
	                }  
	            }  
	            return true;  
	        } else {  
	            //长度不一样，文件肯定不同  
	            return false;  
	        }  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {//关闭文件流，防止内存泄漏  
	        if (fis1 != null) {  
	            try {  
	                fis1.close();  
	            } catch (IOException e) {  
	                //忽略  
	                e.printStackTrace();  
	            }  
	        }  
	        if (fis2 != null) {  
	            try {  
	                fis2.close();  
	            } catch (IOException e) {  
	                //忽略  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	    return false;  
	}
	
}
