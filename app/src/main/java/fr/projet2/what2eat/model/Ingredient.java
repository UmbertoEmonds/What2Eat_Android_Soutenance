package fr.projet2.what2eat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingredient implements Serializable {

    @SerializedName("nom")
    private String nom;

    @SerializedName("codeBarre")
    private String code;

    @SerializedName("quantite")
    private int quantite;

    @SerializedName("unite")
    private String unite;

    @SerializedName("imageUrl")
    private String urlImage;

    @SerializedName("categorie")
    private int categorie;

    public Ingredient(String nom, String code, int quantite, String unite, String urlImage, int categorie) {
        this.nom = nom;
        this.code = code;
        this.quantite = quantite;
        this.unite = unite;
        this.urlImage = urlImage;
        this.categorie = categorie;
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

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
}