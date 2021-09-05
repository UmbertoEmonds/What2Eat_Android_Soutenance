package fr.projet2.what2eat.model.OpenFoodAPI;

import java.io.Serializable;
import java.util.List;

public class Image implements Serializable {

    private List<String> categories;
    private String thumb;
    private String medium;
    private String large;
    private String xlarge;

    public Image(List<String> categories, String thumb, String medium, String large, String xlarge) {
        this.categories = categories;
        this.thumb = thumb;
        this.medium = medium;
        this.large = large;
        this.xlarge = xlarge;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }
}
