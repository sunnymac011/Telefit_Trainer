
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoArrayBean implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("exe_id")
    @Expose
    private String exeId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("video_id")
    @Expose
    private String videoId;
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
    @SerializedName("temp_delete")
    @Expose
    private String tempDelete;
    @SerializedName("video_title")
    @Expose
    private String videoTitle;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("video_cover_img")
    @Expose
    private String videoCoverImg;
    @SerializedName("exe_image_url")
    @Expose
    private String exeImageUrl;
    @SerializedName("exe_video_url")
    @Expose
    private String exeVideoUrl;

    protected VideoArrayBean(Parcel in) {
        id = in.readString();
        exeId = in.readString();
        catId = in.readString();
        videoId = in.readString();
        isActive = in.readString();
        isDelete = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        tempDelete = in.readString();
        videoTitle = in.readString();
        video = in.readString();
        videoCoverImg = in.readString();
        exeImageUrl = in.readString();
        exeVideoUrl = in.readString();
    }

    public static final Creator<VideoArrayBean> CREATOR = new Creator<VideoArrayBean>() {
        @Override
        public VideoArrayBean createFromParcel(Parcel in) {
            return new VideoArrayBean(in);
        }

        @Override
        public VideoArrayBean[] newArray(int size) {
            return new VideoArrayBean[size];
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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
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

    public String getTempDelete() {
        return tempDelete;
    }

    public void setTempDelete(String tempDelete) {
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

    public String getExeImageUrl() {
        return exeImageUrl;
    }

    public void setExeImageUrl(String exeImageUrl) {
        this.exeImageUrl = exeImageUrl;
    }

    public String getExeVideoUrl() {
        return exeVideoUrl;
    }

    public void setExeVideoUrl(String exeVideoUrl) {
        this.exeVideoUrl = exeVideoUrl;
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
        dest.writeString(videoId);
        dest.writeString(isActive);
        dest.writeString(isDelete);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(tempDelete);
        dest.writeString(videoTitle);
        dest.writeString(video);
        dest.writeString(videoCoverImg);
        dest.writeString(exeImageUrl);
        dest.writeString(exeVideoUrl);
    }

    @Override
    public String toString() {
        return "VideoArrayBean{" +
                "id='" + id + '\'' +
                ", exeId='" + exeId + '\'' +
                ", catId='" + catId + '\'' +
                ", videoId='" + videoId + '\'' +
                ", isActive='" + isActive + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", tempDelete='" + tempDelete + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", video='" + video + '\'' +
                ", videoCoverImg='" + videoCoverImg + '\'' +
                ", exeImageUrl='" + exeImageUrl + '\'' +
                ", exeVideoUrl='" + exeVideoUrl + '\'' +
                '}';
    }
}
