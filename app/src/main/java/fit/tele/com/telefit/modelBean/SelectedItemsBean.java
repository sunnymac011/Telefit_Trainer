
package fit.tele.com.telefit.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedItemsBean implements Parcelable {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("sub_cat_id")
    @Expose
    private List<SubCatId> subCatId = null;

    public SelectedItemsBean() {
    }

    protected SelectedItemsBean(Parcel in) {
        catId = in.readString();
        subCatId = in.createTypedArrayList(SubCatId.CREATOR);
    }

    public static final Creator<SelectedItemsBean> CREATOR = new Creator<SelectedItemsBean>() {
        @Override
        public SelectedItemsBean createFromParcel(Parcel in) {
            return new SelectedItemsBean(in);
        }

        @Override
        public SelectedItemsBean[] newArray(int size) {
            return new SelectedItemsBean[size];
        }
    };

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public List<SubCatId> getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(List<SubCatId> subCatId) {
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

    @Override
    public String toString() {
        return "SelectedItemsBean{" +
                "catId='" + catId + '\'' +
                ", subCatId=" + subCatId +
                '}';
    }
}
