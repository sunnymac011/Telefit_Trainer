
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lifestyle {

    @SerializedName("vegan")
    @Expose
    private Vegan vegan;
    @SerializedName("vegetarian")
    @Expose
    private Vegetarian vegetarian;
    @SerializedName("gluten-free")
    @Expose
    private GlutenFree glutenFree;

    public Vegan getVegan() {
        return vegan;
    }

    public void setVegan(Vegan vegan) {
        this.vegan = vegan;
    }

    public Vegetarian getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Vegetarian vegetarian) {
        this.vegetarian = vegetarian;
    }

    public GlutenFree getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(GlutenFree glutenFree) {
        this.glutenFree = glutenFree;
    }

}
