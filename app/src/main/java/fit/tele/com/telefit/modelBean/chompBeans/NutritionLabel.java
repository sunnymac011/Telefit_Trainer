
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionLabel {

    @SerializedName("calcium")
    @Expose
    private Calcium calcium;
    @SerializedName("carbohydrates")
    @Expose
    private Carbohydrates carbohydrates;
    @SerializedName("cholesterol")
    @Expose
    private Cholesterol cholesterol;
    @SerializedName("calories")
    @Expose
    private Calories calories;
    @SerializedName("fat")
    @Expose
    private Fat fat;
    @SerializedName("fiber")
    @Expose
    private Fiber fiber;
    @SerializedName("iron")
    @Expose
    private Iron iron;
    @SerializedName("proteins")
    @Expose
    private Proteins proteins;
    @SerializedName("salt")
    @Expose
    private Salt salt;
    @SerializedName("saturated-fat")
    @Expose
    private SaturatedFat saturatedFat;
    @SerializedName("sodium")
    @Expose
    private Sodium sodium;
    @SerializedName("sugars")
    @Expose
    private Sugars sugars;
    @SerializedName("trans-fat")
    @Expose
    private TransFat transFat;
    @SerializedName("vitamin-a")
    @Expose
    private VitaminA vitaminA;
    @SerializedName("vitamin-c")
    @Expose
    private VitaminC vitaminC;

    public Calcium getCalcium() {
        return calcium;
    }

    public void setCalcium(Calcium calcium) {
        this.calcium = calcium;
    }

    public Carbohydrates getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Carbohydrates carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Cholesterol getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Cholesterol cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public Fiber getFiber() {
        return fiber;
    }

    public void setFiber(Fiber fiber) {
        this.fiber = fiber;
    }

    public Iron getIron() {
        return iron;
    }

    public void setIron(Iron iron) {
        this.iron = iron;
    }

    public Proteins getProteins() {
        return proteins;
    }

    public void setProteins(Proteins proteins) {
        this.proteins = proteins;
    }

    public Salt getSalt() {
        return salt;
    }

    public void setSalt(Salt salt) {
        this.salt = salt;
    }

    public SaturatedFat getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(SaturatedFat saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Sodium getSodium() {
        return sodium;
    }

    public void setSodium(Sodium sodium) {
        this.sodium = sodium;
    }

    public Sugars getSugars() {
        return sugars;
    }

    public void setSugars(Sugars sugars) {
        this.sugars = sugars;
    }

    public TransFat getTransFat() {
        return transFat;
    }

    public void setTransFat(TransFat transFat) {
        this.transFat = transFat;
    }

    public VitaminA getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(VitaminA vitaminA) {
        this.vitaminA = vitaminA;
    }

    public VitaminC getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(VitaminC vitaminC) {
        this.vitaminC = vitaminC;
    }

}
