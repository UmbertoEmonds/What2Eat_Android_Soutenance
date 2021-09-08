package fr.projet2.what2eat.model.OpenFoodAPI;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OpenFoodResponseAPI implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("product")
    private ProductOpenFood product;

    public OpenFoodResponseAPI(String code, ProductOpenFood product) {
        this.code = code;
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProductOpenFood getProduct() {
        return product;
    }

    public void setProduct(ProductOpenFood product) {
        this.product = product;
    }
}