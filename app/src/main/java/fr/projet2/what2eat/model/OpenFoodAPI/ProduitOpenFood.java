package fr.projet2.what2eat.model.OpenFoodAPI;

import java.io.Serializable;
import java.util.List;

public class ProduitOpenFood implements Serializable {

    private int id;
    private String country;
    private String barcode;
    private Translations display_name_translations;
    private String unit;
    private List<Image> images;

    public ProduitOpenFood(int id, String country, String barcode, Translations display_name_translations, String unit, List<Image> images) {
        this.id = id;
        this.country = country;
        this.barcode = barcode;
        this.display_name_translations = display_name_translations;
        this.unit = unit;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Translations getDisplay_name_translations() {
        return display_name_translations;
    }

    public void setDisplay_name_translations(Translations display_name_translations) {
        this.display_name_translations = display_name_translations;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
