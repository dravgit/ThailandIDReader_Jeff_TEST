package com.centerm.centermposoversealib.thailand;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author qiuchunhua@centerm.com
 * @date 2018/11/22 19:03
 */
public class ThiaIdInfoBeen implements Parcelable {
    private String CitizenId;
    private String ThaiName;
    private String ThaiTitle;
    private String ThaiFirstName;
    private String ThaiMiddleName;
    private String ThaiLastName;
    private String EnglishName;
    private String EnglishTitle;
    private String EnglishFirstName;
    private String EnglishMiddleName;
    private String EnglishLastName;
    private String BirthDate;
    private String Gender;
    private String Address;
    private String HomeNumber;
    private String Moo;
    private String Trok;
    private String Soi;
    private String Road;
    private String SubDistrict;
    private String District;
    private String Province;
    private String CardIssueDate;
    private String CardExpireDate;
    private String CardIssueCenter;
    private String Bp1no;
    private Bitmap photo;
    private boolean hasPhoto = false;

    public ThiaIdInfoBeen() {
    }

    public String toJSONString() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("CitizenId", CitizenId);
            obj.put("ThaiName", ThaiName);
            obj.put("ThaiTitle", ThaiTitle);
            obj.put("ThaiFirstName", ThaiFirstName);
            obj.put("ThaiMiddleName", ThaiMiddleName);
            obj.put("ThaiLastName", ThaiLastName);
            obj.put("EnglishName", EnglishName);
            obj.put("EnglishTitle", EnglishTitle);
            obj.put("EnglishFirstName", EnglishFirstName);
            obj.put("EnglishMiddleName", EnglishMiddleName);
            obj.put("EnglishLastName", EnglishLastName);
            obj.put("BirthDate", BirthDate);
            obj.put("Gender", Gender);
            obj.put("Address", Address);
            obj.put("HomeNumber", HomeNumber);
            obj.put("Moo", Moo);
            obj.put("Trok", Trok);
            obj.put("Soi", Soi);
            obj.put("Road", Road);
            obj.put("SubDistrict", SubDistrict);
            obj.put("District", District);
            obj.put("Province", Province);
            obj.put("CardIssueDate", CardIssueDate);
            obj.put("CardExpireDate", CardExpireDate);
            obj.put("CardIssueCenter", CardIssueCenter);
            obj.put("Bp1no", Bp1no);
            return obj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected ThiaIdInfoBeen(Parcel in) {
        CitizenId = in.readString();
        ThaiName = in.readString();
        ThaiTitle = in.readString();
        ThaiFirstName = in.readString();
        ThaiMiddleName = in.readString();
        ThaiLastName = in.readString();
        EnglishName = in.readString();
        EnglishTitle = in.readString();
        EnglishFirstName = in.readString();
        EnglishMiddleName = in.readString();
        EnglishLastName = in.readString();
        BirthDate = in.readString();
        Gender = in.readString();
        Address = in.readString();
        HomeNumber = in.readString();
        Moo = in.readString();
        Trok = in.readString();
        Soi = in.readString();
        Road = in.readString();
        SubDistrict = in.readString();
        District = in.readString();
        Province = in.readString();
        CardIssueDate = in.readString();
        CardExpireDate = in.readString();
        CardIssueCenter = in.readString();
        Bp1no = in.readString();
        hasPhoto = (Boolean) in.readValue(Boolean.class.getClassLoader());
        if (hasPhoto) {
            photo = Bitmap.CREATOR.createFromParcel(in);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CitizenId);
        dest.writeString(ThaiName);
        dest.writeString(ThaiTitle);
        dest.writeString(ThaiFirstName);
        dest.writeString(ThaiMiddleName);
        dest.writeString(ThaiLastName);
        dest.writeString(EnglishName);
        dest.writeString(EnglishTitle);
        dest.writeString(EnglishFirstName);
        dest.writeString(EnglishMiddleName);
        dest.writeString(EnglishLastName);
        dest.writeString(BirthDate);
        dest.writeString(Gender);
        dest.writeString(Address);
        dest.writeString(HomeNumber);
        dest.writeString(Moo);
        dest.writeString(Trok);
        dest.writeString(Soi);
        dest.writeString(Road);
        dest.writeString(SubDistrict);
        dest.writeString(District);
        dest.writeString(Province);
        dest.writeString(CardIssueDate);
        dest.writeString(CardExpireDate);
        dest.writeString(CardIssueCenter);
        dest.writeString(Bp1no);
        if (photo != null) {
            hasPhoto = true;
            dest.writeValue(hasPhoto);
            photo.writeToParcel(dest, flags);
        } else {
            hasPhoto = false;
            dest.writeValue(hasPhoto);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ThiaIdInfoBeen> CREATOR = new Creator<ThiaIdInfoBeen>() {
        @Override
        public ThiaIdInfoBeen createFromParcel(Parcel in) {
            return new ThiaIdInfoBeen(in);
        }

        @Override
        public ThiaIdInfoBeen[] newArray(int size) {
            return new ThiaIdInfoBeen[size];
        }
    };

    public String getCitizenId() {
        return CitizenId;
    }

    public void setCitizenId(String citizenId) {
        CitizenId = citizenId;
    }

    public String getThaiName() {
        return ThaiName;
    }

    public void setThaiName(String thaiName) {
        ThaiName = thaiName;
    }

    public String getThaiTitle() {
        return ThaiTitle;
    }

    public void setThaiTitle(String thaiTitle) {
        ThaiTitle = thaiTitle;
    }

    public String getThaiFirstName() {
        return ThaiFirstName;
    }

    public void setThaiFirstName(String thaiFirstName) {
        ThaiFirstName = thaiFirstName;
    }

    public String getThaiMiddleName() {
        return ThaiMiddleName;
    }

    public void setThaiMiddleName(String thaiMiddleName) {
        ThaiMiddleName = thaiMiddleName;
    }

    public String getThaiLastName() {
        return ThaiLastName;
    }

    public void setThaiLastName(String thaiLastName) {
        ThaiLastName = thaiLastName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getEnglishTitle() {
        return EnglishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        EnglishTitle = englishTitle;
    }

    public String getEnglishFirstName() {
        return EnglishFirstName;
    }

    public void setEnglishFirstName(String englishFirstName) {
        EnglishFirstName = englishFirstName;
    }

    public String getEnglishMiddleName() {
        return EnglishMiddleName;
    }

    public void setEnglishMiddleName(String englishMiddleName) {
        EnglishMiddleName = englishMiddleName;
    }

    public String getEnglishLastName() {
        return EnglishLastName;
    }

    public void setEnglishLastName(String englishLastName) {
        EnglishLastName = englishLastName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getHomeNumber() {
        return HomeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        HomeNumber = homeNumber;
    }

    public String getMoo() {
        return Moo;
    }

    public void setMoo(String moo) {
        Moo = moo;
    }

    public String getTrok() {
        return Trok;
    }

    public void setTrok(String trok) {
        Trok = trok;
    }

    public String getSoi() {
        return Soi;
    }

    public void setSoi(String soi) {
        Soi = soi;
    }

    public String getRoad() {
        return Road;
    }

    public void setRoad(String road) {
        Road = road;
    }

    public String getSubDistrict() {
        return SubDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        SubDistrict = subDistrict;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCardIssueDate() {
        return CardIssueDate;
    }

    public void setCardIssueDate(String cardIssueDate) {
        CardIssueDate = cardIssueDate;
    }

    public String getCardExpireDate() {
        return CardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        CardExpireDate = cardExpireDate;
    }

    public String getCardIssueCenter() {
        return CardIssueCenter;
    }

    public void setCardIssueCenter(String cardIssueCenter) {
        CardIssueCenter = cardIssueCenter;
    }

    public String getBp1no() {
        return Bp1no;
    }

    public void setBp1no(String bp1no) {
        Bp1no = bp1no;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
