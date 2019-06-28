
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Front {

    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("display")
    @Expose
    private String display;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
