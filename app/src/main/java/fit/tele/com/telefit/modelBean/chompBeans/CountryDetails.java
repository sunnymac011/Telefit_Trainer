
package fit.tele.com.telefit.modelBean.chompBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryDetails {

    @SerializedName("english_speaking")
    @Expose
    private String englishSpeaking;
    @SerializedName("non_english_speaking")
    @Expose
    private String nonEnglishSpeaking;

    public String getEnglishSpeaking() {
        return englishSpeaking;
    }

    public void setEnglishSpeaking(String englishSpeaking) {
        this.englishSpeaking = englishSpeaking;
    }

    public String getNonEnglishSpeaking() {
        return nonEnglishSpeaking;
    }

    public void setNonEnglishSpeaking(String nonEnglishSpeaking) {
        this.nonEnglishSpeaking = nonEnglishSpeaking;
    }

}
