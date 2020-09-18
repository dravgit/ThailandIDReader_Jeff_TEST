package com.centerm.centermposoversealib.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utility {
	private static final String TAG = "Utility";
	
	/**
	 * 根据二三磁道数据获取卡号数据
	 * @param trackData
	 * @return
	 */
	public static String getCardNo(byte[] trackData){
		String trackDataStr = new String(trackData);
		Log.e(TAG, "trackData:"+trackDataStr);
		
		//以"="分割
		String cardno = trackDataStr.split("=")[0];
		return cardno;
	}
	/**
	 * 字符串替换函数
	 * @param from 要替换的字符
	 * @param to 要替换成的目标字符
	 * @param source 要替换的字符串
	 * @return 替换后的字符串
	 */
	public static String str_replace(String from,String to,String source) {
	    return source.replace(from, to);
	}
	
	
	
	public static String enctryCardNo(String cardno) throws Exception{
		Log.v("lakala", "正在转换卡号["+cardno+"]");
		if(cardno==null||cardno.equals("")||cardno.length()<9){
			throw new IllegalArgumentException("卡号格式不对");
		}
		
		char[] tempchar = new char[cardno.length()];
		
		for(int i=0;i<cardno.length();i++){
			if(i>3&&i<cardno.length()-4){
				tempchar[i]='*';
			}else{
				tempchar[i] = cardno.charAt(i);
			}
		}
		return new String(tempchar);
	}
	
	public static String byteToString(byte[] data){
		if(data == null){
			return "null";
		}
		StringBuffer str = new StringBuffer("");
		for(int i=0;i<data.length;i++){
			str.append(HexUtil.bcd2str(new byte[]{data[i]})+",");
		}
		return str.toString();
	}
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();    
		
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}
	public static byte[] bitmapToByte(Bitmap bmp)
	{
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, bout);
		byte[] data = bout.toByteArray();
		return data;
	}
	
	/**
	 * 获取IPv4地址
	 * @return IPv4地址，若不存在，返回null
	 */
	public static String getLocalIpAddress() {   
        try {
        	for (Enumeration<NetworkInterface> en = NetworkInterface  
        			.getNetworkInterfaces(); en.hasMoreElements();) {  
        		NetworkInterface intf = en.nextElement();  
        		if (intf.getName().toLowerCase().equals("eth0") || intf.getName().toLowerCase().equals("wlan0")|| intf.getName().toLowerCase().equals("ppp0")){   
        			for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
        				InetAddress inetAddress = enumIpAddr.nextElement();  
        				if (!inetAddress.isLoopbackAddress()) {  
        					String ipaddress = inetAddress.getHostAddress().toString();  
        					if(!ipaddress.contains("::")){//ipV6的地址  
        						return ipaddress;  
        					}  
        				}  
        			}  
        		} else {  
        			continue;  
        		}  
        	}  
        } catch (SocketException ex) {   
            Log.e("WifiPrefer IpAddress", ex.toString());
        }   
        return null;   
    } 
	


    
    /**
     * 系统版本号
     * @return
     */
    public static String getSystemInfo()
	{
        return Build.BRAND; 
	}
    
	/**
	 * 判断U盘是否挂载
	 * @return 已经挂载true 未挂载false
	 * @throws IOException
	 */
	public static boolean isUsbMounted() throws IOException
	{
		File file = new File("/proc/mounts");
			BufferedReader freader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = freader.readLine())!=null)
			{
				if(line.contains("/mnt/usbhost1 vfat rw")|| line.contains("/mnt/usb_storage vfat rw") ){
					return true;
				}
			}
		return false;
	}
	
	/**
	 * 获取本地MAC地址
	 */      
	public static String getLocalMacAddress(){  
		String result = "";        
		String Mac = "";         
		result = callCmd(new String[]{"busybox","ifconfig","eth0"},"HWaddr");    
		//如果返回的result == null，则说明网络不可取      
		if(result==null){          return "MAC地址不存在";         }        
		//对该行数据进行解析         //例如：eth0      Link encap:Ethernet  HWaddr 00:16:E8:3E:DF:67    
		if(result.length()>0 && result.contains("HWaddr")==true){        
			Mac = result.substring(result.indexOf("HWaddr")+6, result.length()-1);      
			if(Mac.length()>1){      
				Mac = Mac.replaceAll(" ", "");        
				result = "";         
			}         
		}      
		return Mac;    
	}    
	

	
	
	private static String callCmd(String[] cmd,String filter) {       
		String result = "";         
		String line = "";          
		try {        
			Process proc = Runtime.getRuntime().exec(cmd);           
			InputStreamReader is = new InputStreamReader(proc.getInputStream());       
			BufferedReader br = new BufferedReader (is);                  
			//执行命令cmd，只取结果中含有filter的这一行         
			while ((line = br.readLine ()) != null ) {         
				//result += line;              Log.i("test","line: "+line);             }              
				if(line.contains(filter))
				{
					result = line;      
//					Log.e("test","result: "+result);     
				}
			}   
		}catch(Exception e) {   
			e.printStackTrace();      
		}          
		return result;   
	}
	
	/** 
     * 收集设备参数信息 
     * @param ctx 
     */  
    private static Map<String, String> collectDeviceInfo(Context ctx) {  
    	Map<String, String> infos = new HashMap<String, String>();  
        try {  
        	
            PackageManager pm = ctx.getPackageManager();  
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);  
            if (pi != null) {  
                String versionName = pi.versionName == null ? "null" : pi.versionName;  
                String versionCode = pi.versionCode + "";  
                infos.put("versionName", versionName);  
                infos.put("versionCode", versionCode);  
            }  
        } catch (NameNotFoundException e) {  
            Log.e(TAG, "an error occured when collect package info", e);  
        }  
        Field[] fields = Build.class.getDeclaredFields();  
        for (Field field : fields) {  
            try {  
                field.setAccessible(true);  
                infos.put(field.getName(), field.get(null).toString());  
                Log.d(TAG, field.getName() + " : " + field.get(null));  
            } catch (Exception e) {  
                Log.e(TAG, "an error occured when collect crash info", e);  
            }  
        }  
        return infos;
    }  
	
	/** 
     * 保存错误信息到文件中 
     *  
     * @param ex 
     * @return  返回文件名称,便于将文件传送到服务器 
     */  
    public static String saveCrashInfo2File(Context context,Throwable ex,String crashFilePath) {  
    	Map<String, String> infos = collectDeviceInfo(context);
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
        StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, String> entry : infos.entrySet()) {  
            String key = entry.getKey();  
            String value = entry.getValue();  
            sb.append(key + "=" + value + "\n");  
        }  
          
        Writer writer = new StringWriter();  
        PrintWriter printWriter = new PrintWriter(writer);  
        ex.printStackTrace(printWriter);  
        Throwable cause = ex.getCause();  
        while (cause != null) {  
            cause.printStackTrace(printWriter);  
            cause = cause.getCause();  
        }  
        printWriter.close();  
        String result = writer.toString();  
        sb.append(result);  
        try {  
            long timestamp = System.currentTimeMillis();  
            String time = formatter.format(new Date());  
            String fileName = "crash-" + time + "-" + timestamp + ".log";  
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
                String path = crashFilePath;  
                File dir = new File(path);  
                if (!dir.exists()) {  
                    dir.mkdirs();  
                }  
                FileOutputStream fos = new FileOutputStream(path + fileName);  
                fos.write(sb.toString().getBytes());  
                fos.close();  
            }  
            return fileName;  
        } catch (Exception e) {  
            Log.e(TAG, "an error occured while writing file...", e);  
        }  
        return null;  
    }

    /**
     *
	 * @param context
     * @return
     */
	public static boolean checkApkExist(Context context) {
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo("com.centerm.frame",
							PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}
	

	
	 public static boolean isEmpty(String str){
	        if(str == null ||"".equals(str)){
	            return true;
	        } else {
	            return false;
	        }

	    }


	    public static boolean isQ60() {
	        String modelVer = Build.MODEL;
	        if (modelVer.toUpperCase().contains("Q60")) {
	            return true;
	        }
	        return false;
	    }

	    public static boolean isK9() {
	        String modelVer = Build.MODEL;
	        if (modelVer.toUpperCase().contains("K9")) {
	            return true;
	        }
	        return false;
	    }

	    public static boolean isC960F() {
	        String modelVer = Build.MODEL;
	        if (modelVer.toUpperCase().contains("C960F")) {
	            return true;
	        }
	        return false;
	    }
}
