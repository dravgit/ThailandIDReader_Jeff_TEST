package com.centerm.centermposoversealib.thailand;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author linpeita@centerm.com
 */
public class IdInfoThaBean implements Parcelable {
    /**
     * 身份证ID
     */
    private String idCard;
    /**
     * 泰文名称
     */
    private String thaiName;
    /**
     * 英文名称
     */
    private String engFirstName;
    /**
     * 英文姓氏
     */
    private String engLastName;
    /**
     * 出生日期（英文）
     */
    private String birthEng;
    /**
     * 出生日期（泰文）
     */
    private String birthTh;
    /**
     * 地址
     */
    private String address;
    /**
     * 发行日期（英文）
     */
    private String issueEng;
    /**
     * 发行日期（泰文）
     */
    private String issueTh;
    /**
     * 截止日期（英文）
     */
    private String expireEng;
    /**
     * 截止日期（泰文）
     */
    private String expireTh;
    /**
     * 宗教
     */
    private String religion;
    /**
     * 照片
     */
    private Bitmap photo;
    /**
     * 是否有传递照片
     */
    private boolean hasPhoto = false;

    public IdInfoThaBean() {

    }

    private IdInfoThaBean(Parcel in) {
        idCard = in.readString();
        thaiName = in.readString();
        engFirstName = in.readString();
        engLastName = in.readString();
        birthEng = in.readString();
        birthTh = in.readString();
        address = in.readString();
        issueEng = in.readString();
        issueTh = in.readString();
        expireEng = in.readString();
        expireTh = in.readString();
        religion = in.readString();
        hasPhoto = (Boolean) in.readValue(Boolean.class.getClassLoader());
        if (hasPhoto) {
            photo = Bitmap.CREATOR.createFromParcel(in);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idCard);
        dest.writeString(thaiName);
        dest.writeString(engFirstName);
        dest.writeString(engLastName);
        dest.writeString(birthEng);
        dest.writeString(birthTh);
        dest.writeString(address);
        dest.writeString(issueEng);
        dest.writeString(issueTh);
        dest.writeString(expireEng);
        dest.writeString(expireTh);
        dest.writeString(religion);
        if (photo != null) {
            hasPhoto = true;
            dest.writeValue(hasPhoto);
            photo.writeToParcel(dest, flags);
        }else{
            hasPhoto = false;
            dest.writeValue(hasPhoto);
        }
    }

    public static final Creator<IdInfoThaBean> CREATOR = new Creator<IdInfoThaBean>() {

        @Override
        public IdInfoThaBean createFromParcel(Parcel source) {
            return new IdInfoThaBean(source);
        }

        @Override
        public IdInfoThaBean[] newArray(int size) {
            return new IdInfoThaBean[size];
        }
    };

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getThaiName() {
        return thaiName;
    }

    public void setThaiName(String thaiName) {
        this.thaiName = thaiName;
    }

    public String getEngFirstName() {
        return engFirstName;
    }

    public void setEngFirstName(String engFirstName) {
        this.engFirstName = engFirstName;
    }

    public String getEngLastName() {
        return engLastName;
    }

    public void setEngLastName(String engLastName) {
        this.engLastName = engLastName;
    }

    public String getBirthEng() {
        return birthEng;
    }

    public void setBirthEng(String birthEng) {
        this.birthEng = birthEng;
    }

    public String getBirthTh() {
        return birthTh;
    }

    public void setBirthTh(String birthTh) {
        this.birthTh = birthTh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssueEng() {
        return issueEng;
    }

    public void setIssueEng(String issueEng) {
        this.issueEng = issueEng;
    }

    public String getIssueTh() {
        return issueTh;
    }

    public void setIssueTh(String issueTh) {
        this.issueTh = issueTh;
    }

    public String getExpireEng() {
        return expireEng;
    }

    public void setExpireEng(String expireEng) {
        this.expireEng = expireEng;
    }

    public String getExpireTh() {
        return expireTh;
    }

    public void setExpireTh(String expireTh) {
        this.expireTh = expireTh;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

}
