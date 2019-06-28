
package fit.tele.com.telefit.modelBean.chompBeans;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("ingredients")
    @Expose
    private String ingredients;
    @SerializedName("package_size")
    @Expose
    private String packageSize;
    @SerializedName("serving_size")
    @Expose
    private String servingSize;
    @SerializedName("brands")
    @Expose
    private Brands brands;
    @SerializedName("countries")
    @Expose
    private Countries countries;
    @SerializedName("has_english_ingredients")
    @Expose
    private String hasEnglishIngredients;
    @SerializedName("country_details")
    @Expose
    private CountryDetails countryDetails;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("ingredient_tags")
    @Expose
    private IngredientTags ingredientTags;
    @SerializedName("keywords")
    @Expose
    private Keywords keywords;
    @SerializedName("lifestyle")
    @Expose
    private Lifestyle lifestyle;
    @SerializedName("nutrition_label")
    @Expose
    private NutritionLabel nutritionLabel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public Brands getBrands() {
        return brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public String getHasEnglishIngredients() {
        return hasEnglishIngredients;
    }

    public void setHasEnglishIngredients(String hasEnglishIngredients) {
        this.hasEnglishIngredients = hasEnglishIngredients;
    }

    public CountryDetails getCountryDetails() {
        return countryDetails;
    }

    public void setCountryDetails(CountryDetails countryDetails) {
        this.countryDetails = countryDetails;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public IngredientTags getIngredientTags() {
        return ingredientTags;
    }

    public void setIngredientTags(IngredientTags ingredientTags) {
        this.ingredientTags = ingredientTags;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public NutritionLabel getNutritionLabel() {
        return nutritionLabel;
    }

    public void setNutritionLabel(NutritionLabel nutritionLabel) {
        this.nutritionLabel = nutritionLabel;
    }

    @Override
    public String toString() {
        return "Details{" +
                "name='" + name + '\'' +
                ", barcode='" + barcode + '\'' +
                ", upc='" + upc + '\'' +
                ", brand='" + brand + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", packageSize='" + packageSize + '\'' +
                ", servingSize='" + servingSize + '\'' +
                ", brands=" + brands +
                ", countries=" + countries +
                ", hasEnglishIngredients='" + hasEnglishIngredients + '\'' +
                ", countryDetails=" + countryDetails +
                ", images=" + images +
                ", ingredientTags=" + ingredientTags +
                ", keywords=" + keywords +
                ", lifestyle=" + lifestyle +
                ", nutritionLabel=" + nutritionLabel +
                '}';
    }
}
