package fr.projet2.what2eat.repositories.services;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.Utilisateur;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UtilisateurService {

    @POST("utilisateur/login")
    Call<Utilisateur> login(@Query("email") String email, @Query("pass") String password);

    @POST("utilisateur/verifyToken")
    Call<Boolean> verifyToken(@Query("token") String token, @Query("userId") int userId);

    @GET("utilisateur/frigo")
    Call<List<Ingredient>> getIngredients(@Query("token") String token, @Query("userId") int userId);

    @GET("utilisateur/{id}/")
    Call<Utilisateur> getUtilisateur(@Path("id") int userId, @Query("token") String token);

    @PUT("utilisateur/")// a modifier
    Call<Utilisateur> updateUtilisateur( @Query("token") String token,@Query("idUser") int userId, @Body Utilisateur utilisateur);

    @PUT("utilisateur/")// a modifier
    Call<Utilisateur> updateUtilisateur( @Query("token") String token,@Query("idUser") int userId, @Query("token") String prenom,@Query("token") String nom,@Query("token") String mail);

    @PUT("utilisateur/")// a modifier
    Call<Utilisateur> updateUtilisateur(@Query("token") String token, @Path("id") int userId,@Query("password") String password);
}
