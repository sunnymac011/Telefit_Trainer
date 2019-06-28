
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exevideo implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("exe_id")
    @Expose
    private Integer exeId;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("video_id")
    @Expose
    private Integer videoId;
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
    @SerializedName("temp_delete")
    @Expose
    private Integer tempDelete;
    @SerializedName("video_title")
    @Expose
    private String videoTitle;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("video_cover_img")
    @Expose
    private String videoCoverImg;
    @SerializedName("video_coverimg_url")
    @Expose
    private String videoCoverimgUrl;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    protected Exevideo(Parcel in) {
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
            videoId = null;
        } else {
            videoId = in.readInt();
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
        if (in.readByte() == 0) {
            tempDelete = null;
        } else {
            tempDelete = in.readInt();
        }
        videoTitle = in.readString();
        video = in.readString();
        videoCoverImg = in.readString();
        videoCoverimgUrl = in.readString();
        videoUrl = in.readString();
    }

    public static final Creator<Exevideo> CREATOR = new Creator<Exevideo>() {
        @Override
        public Exevideo createFromParcel(Parcel in) {
            return new Exevideo(in);
        }

        @Override
        public Exevideo[] newArray(int size) {
            return new Exevideo[size];
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

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
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

    public Integer getTempDelete() {
        return tempDelete;
    }

    public void setTempDelete(Integer tempDelete) {
        this.tempDelete = tempDelete;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoCoverImg() {
        return videoCoverImg;
    }

    public void setVideoCoverImg(String videoCoverImg) {
        this.videoCoverImg = videoCoverImg;
    }

    public String getVideoCoverimgUrl() {
        return videoCoverimgUrl;
    }

    public void setVideoCoverimgUrl(String videoCoverimgUrl) {
        this.videoCoverimgUrl = videoCoverimgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
        if (videoId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(videoId);
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
        if (tempDelete == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(tempDelete);
        }
        parcel.writeString(videoTitle);
        parcel.writeString(video);
        parcel.writeString(videoCoverImg);
        parcel.writeString(videoCoverimgUrl);
        parcel.writeString(videoUrl);
    }

    @Override
    public String toString() {
        return "Exevideo{" +
                "id=" + id +
                ", exeId=" + exeId +
                ", catId=" + catId +
                ", videoId=" + videoId +
                ", isActive=" + isActive +
                ", isDelete=" + isDelete +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", tempDelete=" + tempDelete +
                ", videoTitle='" + videoTitle + '\'' +
                ", video='" + video + '\'' +
                ", videoCoverImg='" + videoCoverImg + '\'' +
                ", videoCoverimgUrl='" + videoCoverimgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
