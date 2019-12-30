
package fit.tele.com.trainer.modelBean;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatePostBean extends BaseObservable implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("friend_id")
    @Expose
    private Integer friend_id;
    @SerializedName("is_accept")
    @Expose
    private Integer is_accept;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("profile_pic_url")
    @Expose
    private String profilePic;
    @SerializedName("post_desc")
    @Expose
    private String post_desc;
    @SerializedName("post_img")
    @Expose
    private String post_img;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("social_id")
    @Expose
    private Object socialId;
    @SerializedName("session_token")
    @Expose
    private String sessionToken;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("bdate")
    @Expose
    private String dob;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("device_type")
    @Expose
    private String deviceType;
    @SerializedName("login_by")
    @Expose
    private String loginBy;
    @SerializedName("is_social")
    @Expose
    private String isSocial;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("register_status")
    @Expose
    private Integer registerStatus;
    @SerializedName("is_email_verify")
    @Expose
    private String isEmailVerify;
    @SerializedName("is_delete")
    @Expose
    private Integer isDelete;
    @SerializedName("forgot_otp")
    @Expose
    private Object forgotOtp;

    public CreatePostBean(){

    }

    protected CreatePostBean(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            user_id = null;
        } else {
            user_id = in.readInt();
        }
        if (in.readByte() == 0) {
            friend_id = null;
        } else {
            friend_id = in.readInt();
        }
        if (in.readByte() == 0) {
            is_accept = null;
        } else {
            is_accept = in.readInt();
        }
        name = in.readString();
        lName = in.readString();
        profilePic = in.readString();
        post_desc = in.readString();
        post_img = in.readString();
        email = in.readString();
        sessionToken = in.readString();
        gender = in.readString();
        address = in.readString();
        dob = in.readString();
        height = in.readString();
        weight = in.readString();
        deviceToken = in.readString();
        deviceType = in.readString();
        loginBy = in.readString();
        isSocial = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        if (in.readByte() == 0) {
            registerStatus = null;
        } else {
            registerStatus = in.readInt();
        }
        isEmailVerify = in.readString();
        if (in.readByte() == 0) {
            isDelete = null;
        } else {
            isDelete = in.readInt();
        }
    }

    public static final Creator<CreatePostBean> CREATOR = new Creator<CreatePostBean>() {
        @Override
        public CreatePostBean createFromParcel(Parcel in) {
            return new CreatePostBean(in);
        }

        @Override
        public CreatePostBean[] newArray(int size) {
            return new CreatePostBean[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public Integer getIs_accept() {
        return is_accept;
    }

    public void setIs_accept(Integer is_accept) {
        this.is_accept = is_accept;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getSocialId() {
        return socialId;
    }

    public void setSocialId(Object socialId) {
        this.socialId = socialId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLoginBy() {
        return loginBy;
    }

    public void setLoginBy(String loginBy) {
        this.loginBy = loginBy;
    }

    public String getIsSocial() {
        return isSocial;
    }

    public void setIsSocial(String isSocial) {
        this.isSocial = isSocial;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(Integer registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getIsEmailVerify() {
        return isEmailVerify;
    }

    public void setIsEmailVerify(String isEmailVerify) {
        this.isEmailVerify = isEmailVerify;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Object getForgotOtp() {
        return forgotOtp;
    }

    public void setForgotOtp(Object forgotOtp) {
        this.forgotOtp = forgotOtp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (user_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(user_id);
        }
        if (friend_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(friend_id);
        }
        if (is_accept == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(is_accept);
        }
        parcel.writeString(name);
        parcel.writeString(lName);
        parcel.writeString(profilePic);
        parcel.writeString(post_desc);
        parcel.writeString(post_img);
        parcel.writeString(email);
        parcel.writeString(sessionToken);
        parcel.writeString(gender);
        parcel.writeString(address);
        parcel.writeString(dob);
        parcel.writeString(height);
        parcel.writeString(weight);
        parcel.writeString(deviceToken);
        parcel.writeString(deviceType);
        parcel.writeString(loginBy);
        parcel.writeString(isSocial);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        if (registerStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(registerStatus);
        }
        parcel.writeString(isEmailVerify);
        if (isDelete == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isDelete);
        }
    }
}
