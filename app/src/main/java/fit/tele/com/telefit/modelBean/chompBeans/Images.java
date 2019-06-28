
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("front")
    @Expose
    private Front front;
    @SerializedName("nutrition")
    @Expose
    private Nutrition nutrition;
    @SerializedName("ingredients")
    @Expose
    private Ingredients ingredients;

    public Front getFront() {
        return front;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

}
