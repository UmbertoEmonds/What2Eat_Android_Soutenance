package fr.projet2.what2eat.model.OpenFoodAPI;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductOpenFood implements Serializable {

    @SerializedName("_id")
    private String barcode;

    @SerializedName("product_name")
    private String name;

    @SerializedName("categories")
    private String categories;

    @SerializedName("image_small_url")
    private String imageSmallUrl;

    @SerializedName("image_thumb_url")
    private String imageThumbUrl;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("product_quantity")
    private String quantity;

    public ProductOpenFood(String barcode, String name, String categories, String imageSmallUrl, String imageThumbUrl, String imageUrl, String quantity) {
        this.barcode = barcode;
        this.name = name;
        this.categories = categories;
        this.imageSmallUrl = imageSmallUrl;
        this.imageThumbUrl = imageThumbUrl;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
