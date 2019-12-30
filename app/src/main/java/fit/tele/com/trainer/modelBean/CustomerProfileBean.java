package fit.tele.com.trainer.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fit.tele.com.trainer.base.BaseActivity;

public class CustomerProfileBean implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("cust_id")
    @Expose
    public String custId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("l_name")
    @Expose
    public String lName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("weight")
    @Expose
    public String weight;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("bdate")
    @Expose
    public String bdate;
    @SerializedName("avatar")
    @Expose
    public String avatar;
    @SerializedName("profile_pic_url")
    @Expose
    public String profilePicUrl;

    protected CustomerProfileBean(Parcel in) {
        id = in.readString();
        custId = in.readString();
        name = in.readString();
        lName = in.readString();
        gender = in.readString();
        height = in.readString();
        weight = in.readString();
        address = in.readString();
        bdate = in.readString();
        avatar = in.readString();
        profilePicUrl = in.readString();
    }

    public static final Creator<CustomerProfileBean> CREATOR = new Creator<CustomerProfileBean>() {
        @Override
        public CustomerProfileBean createFromParcel(Parcel in) {
            return new CustomerProfileBean(in);
        }

        @Override
        public CustomerProfileBean[] newArray(int size) {
            return new CustomerProfileBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(custId);
        dest.writeString(name);
        dest.writeString(lName);
        dest.writeString(gender);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(address);
        dest.writeString(bdate);
        dest.writeString(avatar);
        dest.writeString(profilePicUrl);
    }

    @Override
    public String toString() {
        return "CustomerProfileBean{" +
                "id='" + id + '\'' +
                ", custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", lName='" + lName + '\'' +
                ", gender='" + gender + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", address='" + address + '\'' +
                ", bdate='" + bdate + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }
}
