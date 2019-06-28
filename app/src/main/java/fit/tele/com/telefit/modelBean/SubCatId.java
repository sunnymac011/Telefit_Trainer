
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCatId implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("opt_sub")
    @Expose
    private List<String> optSub = null;

    public SubCatId() {
    }

    protected SubCatId(Parcel in) {
        id = in.readString();
        optSub = in.createStringArrayList();
    }

    public static final Creator<SubCatId> CREATOR = new Creator<SubCatId>() {
        @Override
        public SubCatId createFromParcel(Parcel in) {
            return new SubCatId(in);
        }

        @Override
        public SubCatId[] newArray(int size) {
            return new SubCatId[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getOptSub() {
        return optSub;
    }

    public void setOptSub(List<String> optSub) {
        this.optSub = optSub;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeStringList(optSub);
    }

    @Override
    public String toString() {
        return "SubCatId{" +
                "id='" + id + '\'' +
                ", optSub=" + optSub +
                '}';
    }
}
