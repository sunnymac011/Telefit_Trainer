
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brands {

    @SerializedName("gardein")
    @Expose
    private String gardein;
    @SerializedName("garden-protein-international-inc")
    @Expose
    private String gardenProteinInternationalInc;

    public String getGardein() {
        return gardein;
    }

    public void setGardein(String gardein) {
        this.gardein = gardein;
    }

    public String getGardenProteinInternationalInc() {
        return gardenProteinInternationalInc;
    }

    public void setGardenProteinInternationalInc(String gardenProteinInternationalInc) {
        this.gardenProteinInternationalInc = gardenProteinInternationalInc;
    }

}
