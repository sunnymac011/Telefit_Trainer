
package fit.tele.com.trainer.modelBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PaymentInfoBean implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("details")
    @Expose
    public String details;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("amount")
    @Expose
    public String amount;

    protected PaymentInfoBean(Parcel in) {
        id = in.readString();
        title = in.readString();
        details = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        amount = in.readString();
    }

    public static final Creator<PaymentInfoBean> CREATOR = new Creator<PaymentInfoBean>() {
        @Override
        public PaymentInfoBean createFromParcel(Parcel in) {
            return new PaymentInfoBean(in);
        }

        @Override
        public PaymentInfoBean[] newArray(int size) {
            return new PaymentInfoBean[size];
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(amount);
    }

    @Override
    public String toString() {
        return "PaymentInfoBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
