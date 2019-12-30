
package fit.tele.com.trainer.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExercisesBean implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("exe_title")
    @Expose
    private String exeTitle;
    @SerializedName("exe_desc")
    @Expose
    private String exeDesc;
    @SerializedName("exe_video")
    @Expose
    private Object exeVideo;
    @SerializedName("exe_video_cover_img")
    @Expose
    private Object exeVideoCoverImg;
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
    @SerializedName("exe_instructions")
    @Expose
    private String exeInstructions;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("exeoption")
    @Expose
    private List<Exeoption> exeoption = null;
    @SerializedName("exevideo")
    @Expose
    private Exevideo exevideo;

    protected ExercisesBean(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            catId = null;
        } else {
            catId = in.readInt();
        }
        exeTitle = in.readString();
        exeDesc = in.readString();
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
        exeInstructions = in.readString();
        catName = in.readString();
        exeoption = in.createTypedArrayList(Exeoption.CREATOR);
        exevideo = in.readParcelable(Exevideo.class.getClassLoader());
    }

    public static final Creator<ExercisesBean> CREATOR = new Creator<ExercisesBean>() {
        @Override
        public ExercisesBean createFromParcel(Parcel in) {
            return new ExercisesBean(in);
        }

        @Override
        public ExercisesBean[] newArray(int size) {
            return new ExercisesBean[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getExeTitle() {
        return exeTitle;
    }

    public void setExeTitle(String exeTitle) {
        this.exeTitle = exeTitle;
    }

    public String getExeDesc() {
        return exeDesc;
    }

    public void setExeDesc(String exeDesc) {
        this.exeDesc = exeDesc;
    }

    public Object getExeVideo() {
        return exeVideo;
    }

    public void setExeVideo(Object exeVideo) {
        this.exeVideo = exeVideo;
    }

    public Object getExeVideoCoverImg() {
        return exeVideoCoverImg;
    }

    public void setExeVideoCoverImg(Object exeVideoCoverImg) {
        this.exeVideoCoverImg = exeVideoCoverImg;
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

    public String getExeInstructions() {
        return exeInstructions;
    }

    public void setExeInstructions(String exeInstructions) {
        this.exeInstructions = exeInstructions;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Exeoption> getExeoption() {
        return exeoption;
    }

    public void setExeoption(List<Exeoption> exeoption) {
        this.exeoption = exeoption;
    }

    public Exevideo getExevideo() {
        return exevideo;
    }

    public void setExevideo(Exevideo exevideo) {
        this.exevideo = exevideo;
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
        if (catId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(catId);
        }
        parcel.writeString(exeTitle);
        parcel.writeString(exeDesc);
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
        parcel.writeString(exeInstructions);
        parcel.writeString(catName);
        parcel.writeTypedList(exeoption);
        parcel.writeParcelable(exevideo, i);
    }

    @Override
    public String toString() {
        return "ExercisesBean{" +
                "id=" + id +
                ", catId=" + catId +
                ", exeTitle='" + exeTitle + '\'' +
                ", exeDesc='" + exeDesc + '\'' +
                ", exeVideo=" + exeVideo +
                ", exeVideoCoverImg=" + exeVideoCoverImg +
                ", isActive=" + isActive +
                ", isDelete=" + isDelete +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", exeInstructions='" + exeInstructions + '\'' +
                ", catName='" + catName + '\'' +
                ", exeoption=" + exeoption +
                ", exevideo=" + exevideo +
                '}';
    }
}
