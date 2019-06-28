
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YogaApiBean implements Parcelable {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("sub_cat_id")
    @Expose
    private ArrayList<YogaSubCatId> subCatId = null;

    public YogaApiBean() {
    }

    protected YogaApiBean(Parcel in) {
        catId = in.readString();
        subCatId = in.createTypedArrayList(YogaSubCatId.CREATOR);
    }

    public static final Creator<YogaApiBean> CREATOR = new Creator<YogaApiBean>() {
        @Override
        public YogaApiBean createFromParcel(Parcel in) {
            return new YogaApiBean(in);
        }

        @Override
        public YogaApiBean[] newArray(int size) {
            return new YogaApiBean[size];
        }
    };

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public ArrayList<YogaSubCatId> getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(ArrayList<YogaSubCatId> subCatId) {
        this.subCatId = subCatId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(catId);
        dest.writeTypedList(subCatId);
    }
}
