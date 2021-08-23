package fr.projet2.what2eat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Utilisateur implements Serializable {

    @SerializedName("idUtilisateur")
    private int id;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("naissance")
    private String dateNaissance;

    @SerializedName("mail")
    private String mail;

    @SerializedName("imageUrl")
    private String urlImage;

    @SerializedName("token")
    private String token;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    public Utilisateur(int id, String nom, String prenom, String dateNaissance, String mail, String urlImage, String token, List<Ingredient> ingredients) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.urlImage = urlImage;
        this.token = token;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getMail() {
        return mail;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getToken() {
        return token;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
