package fr.projet2.what2eat.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("name")
    private String nom;

    @SerializedName("code")
    private String code;

    @SerializedName("quantite")
    private int quantite;

    @SerializedName("imageUrl")
    private String imageUrl;

    public Ingredient(String nom, String code, int quantite, String imageUrl) {
        this.nom = nom;
        this.code = code;
        this.quantite = quantite;
        this.imageUrl = imageUrl;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}