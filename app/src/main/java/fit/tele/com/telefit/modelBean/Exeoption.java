
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exeoption implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("exe_id")
    @Expose
    private Integer exeId;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("sub_cat_id")
    @Expose
    private Integer subCatId;
    @SerializedName("opt_id")
    @Expose
    private Integer optId;
    @SerializedName("sub_opt_id")
    @Expose
    private Integer subOptId;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("is_delete")
    @Expose
    private Integer isDelete;
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

    protected Exeoption(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            exeId = null;
        } else {
            exeId = in.readInt();
        }
        if (in.readByte() == 0) {
            catId = null;
        } else {
            catId = in.readInt();
        }
        if (in.readByte() == 0) {
            subCatId = null;
        } else {
            subCatId = in.readInt();
        }
        if (in.readByte() == 0) {
            optId = null;
        } else {
            optId = in.readInt();
        }
        if (in.readByte() == 0) {
            subOptId = null;
        } else {
            subOptId = in.readInt();
        }
        if (in.readByte() == 0) {
            isActive = null;
        } else {
            isActive = in.readInt();
        }
        if (in.readByte() == 0) {
            isDelete = null;
        } else {
            isDelete = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        subCatName = in.readString();
        subCatOption = in.readString();
    }

    public static final Creator<Exeoption> CREATOR = new Creator<Exeoption>() {
        @Override
        public Exeoption createFromParcel(Parcel in) {
            return new Exeoption(in);
        }

        @Override
        public Exeoption[] newArray(int size) {
            return new Exeoption[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExeId() {
        return exeId;
    }

    public void setExeId(Integer exeId) {
        this.exeId = exeId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
        this.subCatId = subCatId;
    }

    public Integer getOptId() {
        return optId;
    }

    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    public Integer getSubOptId() {
        return subOptId;
    }

    public void setSubOptId(Integer subOptId) {
        this.subOptId = subOptId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
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
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (exeId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(exeId);
        }
        if (catId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(catId);
        }
        if (subCatId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(subCatId);
        }
        if (optId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(optId);
        }
        if (subOptId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(subOptId);
        }
        if (isActive == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isActive);
        }
        if (isDelete == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isDelete);
        }
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeString(subCatName);
        parcel.writeString(subCatOption);
    }
}
