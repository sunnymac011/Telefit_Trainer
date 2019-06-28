
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoutinePlanDetailsBean implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("u_exe_pln_id")
    @Expose
    private String uExePlnId;
    @SerializedName("exe_id")
    @Expose
    private String exeId;
    @SerializedName("sets")
    @Expose
    private String sets;
    @SerializedName("reps")
    @Expose
    private String reps;
    @SerializedName("timebetweenreps")
    @Expose
    private String timebetweenreps;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("exe")
    @Expose
    private RoutineExerciseBean exe;

    protected RoutinePlanDetailsBean(Parcel in) {
        id = in.readString();
        userId = in.readString();
        uExePlnId = in.readString();
        exeId = in.readString();
        sets = in.readString();
        reps = in.readString();
        timebetweenreps = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        exe = in.readParcelable(RoutineExerciseBean.class.getClassLoader());
    }

    public static final Creator<RoutinePlanDetailsBean> CREATOR = new Creator<RoutinePlanDetailsBean>() {
        @Override
        public RoutinePlanDetailsBean createFromParcel(Parcel in) {
            return new RoutinePlanDetailsBean(in);
        }

        @Override
        public RoutinePlanDetailsBean[] newArray(int size) {
            return new RoutinePlanDetailsBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getuExePlnId() {
        return uExePlnId;
    }

    public void setuExePlnId(String uExePlnId) {
        this.uExePlnId = uExePlnId;
    }

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getTimebetweenreps() {
        return timebetweenreps;
    }

    public void setTimebetweenreps(String timebetweenreps) {
        this.timebetweenreps = timebetweenreps;
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

    public RoutineExerciseBean getExe() {
        return exe;
    }

    public void setExe(RoutineExerciseBean exe) {
        this.exe = exe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(uExePlnId);
        dest.writeString(exeId);
        dest.writeString(sets);
        dest.writeString(reps);
        dest.writeString(timebetweenreps);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeParcelable(exe, flags);
    }

    @Override
    public String toString() {
        return "RoutinePlanDetailsBean{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", uExePlnId='" + uExePlnId + '\'' +
                ", exeId='" + exeId + '\'' +
                ", sets='" + sets + '\'' +
                ", reps='" + reps + '\'' +
                ", timebetweenreps='" + timebetweenreps + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", exe=" + exe +
                '}';
    }
}
