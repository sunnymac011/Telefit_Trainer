
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countries {

    @SerializedName("united-states")
    @Expose
    private String unitedStates;

    public String getUnitedStates() {
        return unitedStates;
    }

    public void setUnitedStates(String unitedStates) {
        this.unitedStates = unitedStates;
    }

}
