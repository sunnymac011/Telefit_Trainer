
package fit.tele.com.trainer.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YogaSubCatId implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    public YogaSubCatId() {
    }

    protected YogaSubCatId(Parcel in) {
        id = in.readString();
    }

    public static final Creator<YogaSubCatId> CREATOR = new Creator<YogaSubCatId>() {
        @Override
        public YogaSubCatId createFromParcel(Parcel in) {
            return new YogaSubCatId(in);
        }

        @Override
        public YogaSubCatId[] newArray(int size) {
            return new YogaSubCatId[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }
}
