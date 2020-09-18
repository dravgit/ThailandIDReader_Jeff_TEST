package com.centerm.centermposoverseaservice.Thailand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;

import com.centerm.centermposoversealib.constant.DeviceErrorCode;
import com.centerm.centermposoversealib.thailand.ThiaIdInfoBeen;
import com.centerm.dev.error.DeviceBaseException;
import com.centerm.dev.iccard.ICCardManager;
import com.centerm.dev.util.HexUtil;
import com.centerm.smartpos.aidl.iccard.AidlICCard;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author linpeita@centerm.com
 * @time 2017-03-06
 * @func 泰国身份证读取帮助类（通过IC卡设备发送APDU指令获取数据）
 */
public class ThaIDReader {

    private AidlICCard iccard;

    private String _cmd = "00A4040008";
    private String _thai_id_card = "A000000054480001";
    private String _req_version = "80b00000020004";
    private final Charset _UTF8_CHARSET = Charset.forName("TIS-620");
    //    private List<String> months_eng = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
//    private List<String> months_th = Arrays.asList("ม.ค.", "ก.พ.", "มี.ค.", "เม.ษ.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค.");
    //private List<String> religions = Arrays.asList("ไม่นับถือศาสนา", "พุทธ", "อิสลาม", "คริสต์", "พราหมณ์-ฮินดู", "ซิกข์", "ยิว", "เชน", "โซโรอัสเตอร์", "บาไฮ", "ไม่ระบุ");
    private byte[] photoBytes;
    private boolean readPhotoSuccess = false;
    private ICCardManager icCardManager;

    private ThiaIdInfoBeen been = new ThiaIdInfoBeen();

    public ThaIDReader(Context context, AidlICCard iccard) throws DeviceBaseException {
        this.iccard = iccard;
        icCardManager = ICCardManager.getManager(context);
    }

    /**
     * 检查卡是否可读状态
     *
     * @return
     * @throws RemoteException
     */

    private boolean readyToRead(ResultCallBack callBack) throws RemoteException, InterruptedException {
        iccard.open();
        if (iccard.status() == 1) {
            if (iccard.reset() != null) {
                if (iccard.sendAsync(HexUtil.hexStringToByte(_cmd + _thai_id_card)) != null) {
                    return true;
                } else {
                    callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD, null);
                }
            } else {
                callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_RESET, null);
            }
        } else {
            callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_CARD_STATUS, null);
        }
        return false;
    }

    /**
     * 发送命令获取卡中个人信息
     *
     * @throws RemoteException
     */
    private void sendCommandForInfo() throws RemoteException {
        String version = new String(iccard.sendAsync(HexUtil.hexStringToByte(_req_version)), _UTF8_CHARSET);
        version = version.trim();
        if (version.startsWith("0003")) {
            //cid //offset 4 len:13
            a(sendApdu("80b0000402000d"), 0);
            //full_name //offset 17 len:100
            a(sendApdu("80b00011020064"), 11);
            //ENG Name //offset 117 len:100
            a(sendApdu("80b00075020064"), 12);
            //BirthDate //offset 217 len:29
            a(sendApdu("80b000D902001D"), 13);
            //address  //offset 5497 len:160
            a(sendApdu("80b015790200A0"), 2);
            //issue_expire  //offset 359 len:18
            a(sendApdu("80b00167020012"), 3);
            //issu_center  //offset 246 len:100
            a(sendApdu("80b000F6020064"), 4);
        } else {
            //cid //offset 4 len:13
            a(sendApdu("80b1000402000d"), 0);
            //full_name //offset 17 len:100
            a(sendApdu("80b10011020064"), 11);
            //ENG Name //offset 117 len:100
            a(sendApdu("80b10075020064"), 12);
            //BirthDate //offset 217 len:29
            a(sendApdu("80b100D902001D"), 13);
            //address //offset 4 len:150
            a(sendApdu("80b00004020096"), 2);
            //issue_expire //offset 359 len:18
            a(sendApdu("80b10167020012"), 3);
            //issu_center  //offset 246 len:100
            a(sendApdu("80b100F6020064"), 4);
        }
    }

    private String sendApdu(String apdu) throws RemoteException {
        return new String(iccard.sendAsync(HexUtil.hexStringToByte(apdu)), _UTF8_CHARSET);
    }

    public void readIDCard(ResultCallBack callBack) throws RemoteException, DeviceBaseException {
        try {
            icCardManager.setSignalRate((byte) 2);
            if (readyToRead(callBack)) {
                sendCommandForInfo();
                sendCommandForPhoto();
                if (readPhotoSuccess) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
                    been.setPhoto(bmp);
                    callBack.onResult(DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD, been);
                } else {
                    callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD, null);
                }
            } else {
                callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_CARD_STATUS, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD, null);
        } finally {
            icCardManager.setSignalRate((byte) 0);
        }
    }

    /**
     * 只获取卡中信息
     */
    public void readIDCardInfoOnly(ResultCallBack callBack) throws DeviceBaseException, RemoteException {
        if (callBack != null) {
            try {
                icCardManager.setSignalRate((byte) 2);
                if (readyToRead(callBack)) {
                    sendCommandForInfo();
                    callBack.onResult(DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD, been);
                } else {
                    callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_CARD_STATUS, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD, null);
            } finally {
                icCardManager.setSignalRate((byte) 0);
            }
        }
    }

    /**
     * 只获取卡中照片
     */
    public void readIDCardPhotoOnly(ResultCallBack callBack) throws RemoteException, DeviceBaseException {
        if (callBack != null) {
            try {
                icCardManager.setSignalRate((byte) 2);
                if (readyToRead(callBack)) {
                    sendCommandForPhoto();
                    if (readPhotoSuccess) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
                        callBack.onResult(DeviceErrorCode.THAIDCARD.SUCCESS_READ_IDCARD, bmp);
                    } else {
                        callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD, null);
                    }
                } else {
                    callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_CARD_STATUS, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                callBack.onResult(DeviceErrorCode.THAIDCARD.ERROR_READ_IDCARD, null);
            } finally {
                icCardManager.setSignalRate((byte) 0);
            }
        }
    }

    private byte[] r(byte[] data) {
        if (data == null || data.length < 2) {
            throw new RuntimeException("read IC card error.");
        }
        return Arrays.copyOfRange(data, 0, data.length - 2);
    }

    /**
     * 发送命令获取卡片中的数据
     */
    private void sendCommandForPhoto() {
        try {
            String version = new String(iccard.sendAsync(HexUtil.hexStringToByte(_req_version)), _UTF8_CHARSET);
            version = version.trim();
            String base = "";
            if (version.startsWith("0003")) {
                base = "80B0";
            } else {
                base = "80B1";
            }
            //80B0  0179    0200   02
            //base  offset  fix    length
            byte[] length = (r(iccard.sendAsync(HexUtil.hexStringToByte(base + "0179020002"))));
            int iLength = com.centerm.smartpos.util.HexUtil.bytes2short(length);
            ByteArrayOutputStream out = new ByteArrayOutputStream(iLength);
            int cnt = iLength / 250;
            int lastData = iLength % 250;
            for (int i = 0; i < cnt + 1; i++) {
                int xwd;
                int xof = i * 250 + 379;
                xwd = i == cnt ? lastData : 250;
                if (xwd == 0) {
                    break;
                }
                String sp2 = e(xof >> 8 & 0xff);
                String sp3 = e(xof & 0xff);
                String sp6 = e(xwd & 0xff);
                byte[] _xx = (r(iccard.sendAsync(HexUtil.hexStringToByte(base + sp2 + sp3 + "0200" + sp6))));
                if (_xx != null) {
                    out.write(_xx, 0, _xx.length);
                } else {
                    break;
                }
            }
            photoBytes = out.toByteArray();
            out.close();
            readPhotoSuccess = true;
        } catch (Throwable e) {
            e.printStackTrace();
            photoBytes = null;
            readPhotoSuccess = false;
        }
    }

    private String a(String _val, int _index) {
        String _xx = _val;
        switch (_index) {
            case 0:
                if (_xx != null | _xx.length() != 0) {
                    _xx = _xx.substring(0, _xx.length() - 2);
                    _xx = _xx.trim();
                    been.setCitizenId(_xx);
                }
                break;
            case 11: {
                if (_xx != null | _xx.length() != 0) {
                    _xx = _xx.substring(0, _xx.length() - 2).trim();
                    been.setThaiName(_xx);
                    String[] names = _xx.split("#");
                    been.setThaiTitle(names[0]);
                    been.setThaiFirstName(names[1]);
                    been.setThaiMiddleName(names[2]);
                    been.setThaiLastName(names[3]);
                }
                break;
            }
            case 12: {
                if (_xx != null | _xx.length() != 0) {
                    String engName = _xx.substring(0, _xx.length() - 2).trim();
                    been.setEnglishName(engName);
                    String engNames[] = engName.split("#");
                    been.setEnglishTitle(engNames[0]);
                    been.setEnglishFirstName(engNames[1]);
                    been.setEnglishMiddleName(engNames[2]);
                    been.setEnglishLastName(engNames[3]);
                }
                break;
            }
            case 13: {
                if (_xx != null | _xx.length() != 0) {
                    _xx = _xx.substring(0, _xx.length() - 2).trim();
                    String _year_th = _xx.substring(0, 4);
                    String _month_th = _xx.substring(4, 6);
                    String _day = _xx.substring(6, 8);
                    String sex = _xx.substring(8, 9);
                    been.setBirthDate(_day + _month_th + _year_th);
                    been.setBp1no(_xx.substring(9, _xx.indexOf("/")));
                    if ("1".equals(sex)) {
                        been.setGender("M");
                    } else {
                        been.setGender("F");
                    }
                }
                break;
            }
            case 2:
                if (_xx != null | _xx.length() != 0) {
                    //_xx = _val.replaceAll("#", " ");
                    _xx = _xx.substring(0, _xx.length() - 2);
                    _xx = _xx.trim();
                    been.setAddress(_xx);
                    String data[] = _xx.split("#");
                    been.setHomeNumber(data[0]);
                    been.setMoo(data[1]);
                    been.setTrok(data[2]);
                    been.setSoi(data[3]);
                    been.setRoad(data[4]);
                    been.setSubDistrict(data[5]);
                    been.setDistrict(data[6]);
                    been.setProvince(data[7]);
                }
                break;
            case 3:
                if (_xx != null | _xx.length() != 0) {
                    _xx = _val.replaceAll("#", " ");
                    _xx = _xx.substring(0, _xx.length() - 2);
                    String _year = _xx.substring(0, 4);
                    String _mouth = _xx.substring(4, 6);
                    String _day = _xx.substring(6, 8);
                    been.setCardIssueDate(_day + _mouth + _year);
                    _year = _xx.substring(8, 12);
                    _mouth = _xx.substring(12, 14);
                    _day = _xx.substring(14, 16);
                    been.setCardExpireDate(_day + _mouth + _year);
                }
                break;
            case 4:
                if (_xx != null | _xx.length() != 0) {
                    int _first_space = _val.indexOf("  ");
                    String center = _xx.substring(0, _first_space);
                    center = center.replaceAll("/", " ");
                    been.setCardIssueCenter(center);
                }
                break;
            default:
        }
        return _xx;
    }

    private static String e(int value) {
        //整形转化为16进制字符串，同时看一下长度，长度不是偶数，那么前面补0
        String hex = Integer.toHexString(value);
        hex = hex.length() % 2 == 1 ? "0" + hex : hex;
        return hex.toUpperCase();
    }
}
