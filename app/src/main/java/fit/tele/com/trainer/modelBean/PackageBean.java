
package fit.tele.com.trainer.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageBean implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("validity")
    @Expose
    private String validity;

    protected PackageBean(Parcel in) {
        id = in.readString();
        title = in.readString();
        details = in.readString();
        price = in.readString();
        validity = in.readString();
    }

    public static final Creator<PackageBean> CREATOR = new Creator<PackageBean>() {
        @Override
        public PackageBean createFromParcel(Parcel in) {
            return new PackageBean(in);
        }

        @Override
        public PackageBean[] newArray(int size) {
            return new PackageBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(details);
        dest.writeString(price);
        dest.writeString(validity);
    }

    @Override
    public String toString() {
        return "PackageBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", price='" + price + '\'' +
                ", validity='" + validity + '\'' +
                '}';
    }
}
