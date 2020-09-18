package com.centerm.centermposoversealib.tleservice;
import com.centerm.centermposoversealib.tleservice.TleParamMap;

interface AidlTleService{
	boolean check();
	boolean RKIdownload();
	String tleDecrption(String encryptedStr);
	String tleEncryption(String plainStr);
	String tleFuncton(String functionName,in TleParamMap tleParamMap);
}