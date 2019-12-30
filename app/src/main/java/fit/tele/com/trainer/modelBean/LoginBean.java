
package fit.tele.com.trainer.modelBean;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBean extends BaseObservable implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("profile_pic_url")
    @Expose
    private String profilePic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("social_id")
    @Expose
    private String socialId;
    @SerializedName("session_token")
    @Expose
    private String sessionToken;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("company_name")
    @Expose
    private String company_name;
    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("phone_type")
    @Expose
    private String phone_type;
    @SerializedName("bdate")
    @Expose
    private String dob;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("height_type")
    @Expose
    private String heightType;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("weight_type")
    @Expose
    private String weightType;
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
    private String registerStatus;
    @SerializedName("is_email_verify")
    @Expose
    private String isEmailVerify;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;
    @SerializedName("forgot_otp")
    @Expose
    private String forgotOtp;
    @SerializedName("Is_friend_share")
    @Expose
    private String Is_friend_share;
    @SerializedName("Is_trainer_share")
    @Expose
    private String Is_trainer_share;
    @SerializedName("Is_facebook_share")
    @Expose
    private String Is_facebook_share;
    @SerializedName("Is_twiter_share")
    @Expose
    private String Is_twiter_share;
    @SerializedName("Is_instagram_share")
    @Expose
    private String Is_instagram_share;
    @SerializedName("Is_snapchat_share")
    @Expose
    private String Is_snapchat_share;
    @SerializedName("notification_alert")
    @Expose
    private String notificationAlert;
    @SerializedName("request_email")
    @Expose
    private String requestEmail;
    @SerializedName("request_approved")
    @Expose
    private String requestApproved;
    @SerializedName("encourage_notification")
    @Expose
    private String encourageNotification;
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("maintain_weight")
    @Expose
    private String maintainWeight;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("Is_subscribe")
    @Expose
    private String isSubscribe;

    protected LoginBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        lName = in.readString();
        profilePic = in.readString();
        email = in.readString();
        socialId = in.readString();
        sessionToken = in.readString();
        gender = in.readString();
        address = in.readString();
        address1 = in.readString();
        address2 = in.readString();
        city = in.readString();
        state = in.readString();
        zipcode = in.readString();
        company_name = in.readString();
        certification = in.readString();
        phone_number = in.readString();
        phone_type = in.readString();
        dob = in.readString();
        height = in.readString();
        heightType = in.readString();
        weight = in.readString();
        weightType = in.readString();
        deviceToken = in.readString();
        deviceType = in.readString();
        loginBy = in.readString();
        isSocial = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        registerStatus = in.readString();
        isEmailVerify = in.readString();
        isDelete = in.readString();
        forgotOtp = in.readString();
        Is_friend_share = in.readString();
        Is_trainer_share = in.readString();
        Is_facebook_share = in.readString();
        Is_twiter_share = in.readString();
        Is_instagram_share = in.readString();
        Is_snapchat_share = in.readString();
        notificationAlert = in.readString();
        requestEmail = in.readString();
        requestApproved = in.readString();
        encourageNotification = in.readString();
        activity = in.readString();
        maintainWeight = in.readString();
        description = in.readString();
        isSubscribe = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
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

    public String getHeightType() {
        return heightType;
    }

    public void setHeightType(String heightType) {
        this.heightType = heightType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
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

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getIsEmailVerify() {
        return isEmailVerify;
    }

    public void setIsEmailVerify(String isEmailVerify) {
        this.isEmailVerify = isEmailVerify;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getForgotOtp() {
        return forgotOtp;
    }

    public void setForgotOtp(String forgotOtp) {
        this.forgotOtp = forgotOtp;
    }

    public String getIs_friend_share() {
        return Is_friend_share;
    }

    public void setIs_friend_share(String is_friend_share) {
        Is_friend_share = is_friend_share;
    }

    public String getIs_trainer_share() {
        return Is_trainer_share;
    }

    public void setIs_trainer_share(String is_trainer_share) {
        Is_trainer_share = is_trainer_share;
    }

    public String getIs_facebook_share() {
        return Is_facebook_share;
    }

    public void setIs_facebook_share(String is_facebook_share) {
        Is_facebook_share = is_facebook_share;
    }

    public String getIs_twiter_share() {
        return Is_twiter_share;
    }

    public void setIs_twiter_share(String is_twiter_share) {
        Is_twiter_share = is_twiter_share;
    }

    public String getIs_instagram_share() {
        return Is_instagram_share;
    }

    public void setIs_instagram_share(String is_instagram_share) {
        Is_instagram_share = is_instagram_share;
    }

    public String getIs_snapchat_share() {
        return Is_snapchat_share;
    }

    public void setIs_snapchat_share(String is_snapchat_share) {
        Is_snapchat_share = is_snapchat_share;
    }

    public String getNotificationAlert() {
        return notificationAlert;
    }

    public void setNotificationAlert(String notificationAlert) {
        this.notificationAlert = notificationAlert;
    }

    public String getRequestEmail() {
        return requestEmail;
    }

    public void setRequestEmail(String requestEmail) {
        this.requestEmail = requestEmail;
    }

    public String getRequestApproved() {
        return requestApproved;
    }

    public void setRequestApproved(String requestApproved) {
        this.requestApproved = requestApproved;
    }

    public String getEncourageNotification() {
        return encourageNotification;
    }

    public void setEncourageNotification(String encourageNotification) {
        this.encourageNotification = encourageNotification;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getMaintainWeight() {
        return maintainWeight;
    }

    public void setMaintainWeight(String maintainWeight) {
        this.maintainWeight = maintainWeight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(lName);
        dest.writeString(profilePic);
        dest.writeString(email);
        dest.writeString(socialId);
        dest.writeString(sessionToken);
        dest.writeString(gender);
        dest.writeString(address);
        dest.writeString(address1);
        dest.writeString(address2);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipcode);
        dest.writeString(company_name);
        dest.writeString(certification);
        dest.writeString(phone_number);
        dest.writeString(phone_type);
        dest.writeString(dob);
        dest.writeString(height);
        dest.writeString(heightType);
        dest.writeString(weight);
        dest.writeString(weightType);
        dest.writeString(deviceToken);
        dest.writeString(deviceType);
        dest.writeString(loginBy);
        dest.writeString(isSocial);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(registerStatus);
        dest.writeString(isEmailVerify);
        dest.writeString(isDelete);
        dest.writeString(forgotOtp);
        dest.writeString(Is_friend_share);
        dest.writeString(Is_trainer_share);
        dest.writeString(Is_facebook_share);
        dest.writeString(Is_twiter_share);
        dest.writeString(Is_instagram_share);
        dest.writeString(Is_snapchat_share);
        dest.writeString(notificationAlert);
        dest.writeString(requestEmail);
        dest.writeString(requestApproved);
        dest.writeString(encourageNotification);
        dest.writeString(activity);
        dest.writeString(maintainWeight);
        dest.writeString(description);
        dest.writeString(isSubscribe);
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lName='" + lName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", email='" + email + '\'' +
                ", socialId='" + socialId + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", company_name='" + company_name + '\'' +
                ", certification='" + certification + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", phone_type='" + phone_type + '\'' +
                ", dob='" + dob + '\'' +
                ", height='" + height + '\'' +
                ", heightType='" + heightType + '\'' +
                ", weight='" + weight + '\'' +
                ", weightType='" + weightType + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", loginBy='" + loginBy + '\'' +
                ", isSocial='" + isSocial + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", registerStatus='" + registerStatus + '\'' +
                ", isEmailVerify='" + isEmailVerify + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", forgotOtp='" + forgotOtp + '\'' +
                ", Is_friend_share='" + Is_friend_share + '\'' +
                ", Is_trainer_share='" + Is_trainer_share + '\'' +
                ", Is_facebook_share='" + Is_facebook_share + '\'' +
                ", Is_twiter_share='" + Is_twiter_share + '\'' +
                ", Is_instagram_share='" + Is_instagram_share + '\'' +
                ", Is_snapchat_share='" + Is_snapchat_share + '\'' +
                ", notificationAlert='" + notificationAlert + '\'' +
                ", requestEmail='" + requestEmail + '\'' +
                ", requestApproved='" + requestApproved + '\'' +
                ", encourageNotification='" + encourageNotification + '\'' +
                ", activity='" + activity + '\'' +
                ", maintainWeight='" + maintainWeight + '\'' +
                ", description='" + description + '\'' +
                ", isSubscribe='" + isSubscribe + '\'' +
                '}';
    }
}