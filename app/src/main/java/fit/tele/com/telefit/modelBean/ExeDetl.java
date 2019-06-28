
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExeDetl implements Parcelable {

    @SerializedName("exeid")
    @Expose
    private String exeid;
    @SerializedName("sets")
    @Expose
    private String sets;
    @SerializedName("reps")
    @Expose
    private String reps;
    @SerializedName("timebetweenreps")
    @Expose
    private String timebetweenreps;

    public ExeDetl() {
    }

    protected ExeDetl(Parcel in) {
        exeid = in.readString();
        sets = in.readString();
        reps = in.readString();
        timebetweenreps = in.readString();
    }

    public static final Creator<ExeDetl> CREATOR = new Creator<ExeDetl>() {
        @Override
        public ExeDetl createFromParcel(Parcel in) {
            return new ExeDetl(in);
        }

        @Override
        public ExeDetl[] newArray(int size) {
            return new ExeDetl[size];
        }
    };

    public String getExeid() {
        return exeid;
    }

    public void setExeid(String exeid) {
        this.exeid = exeid;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exeid);
        dest.writeString(sets);
        dest.writeString(reps);
        dest.writeString(timebetweenreps);
    }

    @Override
    public String toString() {
        return "ExeDetl{" +
                "exeid='" + exeid + '\'' +
                ", sets='" + sets + '\'' +
                ", reps='" + reps + '\'' +
                ", timebetweenreps='" + timebetweenreps + '\'' +
                '}';
    }
}
