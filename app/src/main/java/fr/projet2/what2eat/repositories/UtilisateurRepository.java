package fr.projet2.what2eat.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.projet2.what2eat.BuildConfig;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.repositories.services.UtilisateurService;
import fr.projet2.what2eat.util.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilisateurRepository {

    private final MutableLiveData<Utilisateur> utilisateur;
    private final MutableLiveData<Boolean> isValidToken;
    private final MutableLiveData<List<Ingredient>> ingredients;

    public UtilisateurRepository(){
        this.utilisateur = new MutableLiveData<>();
        this.isValidToken = new MutableLiveData<>();
        this.ingredients = new MutableLiveData<>();
    }

    public MutableLiveData<Utilisateur> login(String email, String password){

        UtilisateurService service = RetrofitBuilder.getInstance(BuildConfig.API_URL).create(UtilisateurService.class);

        service.login(email, password).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                utilisateur.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                utilisateur.setValue(null);
            }
        });

        return utilisateur;
    }

    public MutableLiveData<Boolean> verifyToken(String token, int userId){

        UtilisateurService service = RetrofitBuilder.getInstance(BuildConfig.API_URL).create(UtilisateurService.class);

        service.verifyToken(token, userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                isValidToken.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                isValidToken.setValue(false);
            }
        });

        return isValidToken;
    }

    public MutableLiveData<List<Ingredient>> getIngredients(String token, int userId){
        UtilisateurService service = RetrofitBuilder.getInstance(BuildConfig.API_URL).create(UtilisateurService.class);

        service.getIngredients(token, userId).enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                ingredients.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                ingredients.setValue(null);
            }
        });

        return ingredients;
    }

}