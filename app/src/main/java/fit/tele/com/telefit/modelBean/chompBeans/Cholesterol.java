
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cholesterol {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("measurement")
    @Expose
    private String measurement;
    @SerializedName("per_100g")
    @Expose
    private String per100g;
    @SerializedName("per_serving")
    @Expose
    private String perServing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getPer100g() {
        return per100g;
    }

    public void setPer100g(String per100g) {
        this.per100g = per100g;
    }

    public String getPerServing() {
        return perServing;
    }

    public void setPerServing(String perServing) {
        this.perServing = perServing;
    }

}
