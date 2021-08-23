package fr.projet2.what2eat.repositories.utilisateur;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.util.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilisateurRepository {

    private final MutableLiveData<Utilisateur> utilisateur;
    private final MutableLiveData<Boolean> isValidToken;

    public UtilisateurRepository(){
        this.utilisateur = new MutableLiveData<>();
        this.isValidToken = new MutableLiveData<>();
    }

    public MutableLiveData<Utilisateur> login(String email, String password){

        UtilisateurService service = RetrofitBuilder.getInstance().create(UtilisateurService.class);

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

        UtilisateurService service = RetrofitBuilder.getInstance().create(UtilisateurService.class);

        service.verifyToken(token, userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                isValidToken.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                isValidToken.postValue(false);
            }
        });

        return isValidToken;
    }

}