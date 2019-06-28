
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlutenFree {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_compatible")
    @Expose
    private String isCompatible;
    @SerializedName("compatibility")
    @Expose
    private String compatibility;
    @SerializedName("grade_confidence")
    @Expose
    private Boolean gradeConfidence;
    @SerializedName("grade_confidence_desc")
    @Expose
    private String gradeConfidenceDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsCompatible() {
        return isCompatible;
    }

    public void setIsCompatible(String isCompatible) {
        this.isCompatible = isCompatible;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public Boolean getGradeConfidence() {
        return gradeConfidence;
    }

    public void setGradeConfidence(Boolean gradeConfidence) {
        this.gradeConfidence = gradeConfidence;
    }

    public String getGradeConfidenceDesc() {
        return gradeConfidenceDesc;
    }

    public void setGradeConfidenceDesc(String gradeConfidenceDesc) {
        this.gradeConfidenceDesc = gradeConfidenceDesc;
    }

}
