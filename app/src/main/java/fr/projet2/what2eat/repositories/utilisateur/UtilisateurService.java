package fr.projet2.what2eat.repositories.utilisateur;

import fr.projet2.what2eat.model.Utilisateur;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UtilisateurService {

    @POST("utilisateur/login")
    Call<Utilisateur> login(@Query("email") String email, @Query("pass") String password);

    @POST("utilisateur/verifyToken")
    Call<Boolean> verifyToken(@Query("token") String token, @Query("userId") int userId);

}
