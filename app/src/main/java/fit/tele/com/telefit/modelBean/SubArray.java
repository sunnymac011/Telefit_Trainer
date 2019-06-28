
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubArray implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("exe_id")
    @Expose
    private String exeId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("opt_id")
    @Expose
    private String optId;
    @SerializedName("sub_opt_id")
    @Expose
    private String subOptId;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sub_cat_name")
    @Expose
    private String subCatName;
    @SerializedName("sub_cat_option")
    @Expose
    private String subCatOption;

    protected SubArray(Parcel in) {
        id = in.readString();
        exeId = in.readString();
        catId = in.readString();
        subCatId = in.readString();
        optId = in.readString();
        subOptId = in.readString();
        isActive = in.readString();
        isDelete = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        subCatName = in.readString();
        subCatOption = in.readString();
    }

    public static final Creator<SubArray> CREATOR = new Creator<SubArray>() {
        @Override
        public SubArray createFromParcel(Parcel in) {
            return new SubArray(in);
        }

        @Override
        public SubArray[] newArray(int size) {
            return new SubArray[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getSubOptId() {
        return subOptId;
    }

    public void setSubOptId(String subOptId) {
        this.subOptId = subOptId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getSubCatOption() {
        return subCatOption;
    }

    public void setSubCatOption(String subCatOption) {
        this.subCatOption = subCatOption;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(exeId);
        dest.writeString(catId);
        dest.writeString(subCatId);
        dest.writeString(optId);
        dest.writeString(subOptId);
        dest.writeString(isActive);
        dest.writeString(isDelete);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(subCatName);
        dest.writeString(subCatOption);
    }
}
