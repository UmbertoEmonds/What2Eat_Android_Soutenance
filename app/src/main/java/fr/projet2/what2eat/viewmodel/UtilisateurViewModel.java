package fr.projet2.what2eat.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.repositories.UtilisateurRepository;

public class UtilisateurViewModel extends ViewModel {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurViewModel(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public MutableLiveData<Utilisateur> login(String email, String password){
        return this.utilisateurRepository.login(email, password);
    }

    public MutableLiveData<Boolean> verifyToken(String token, int userId){
        return this.utilisateurRepository.verifyToken(token, userId);
    }

    public MutableLiveData<List<Ingredient>> getIngredients(String token, int userId){
        return this.utilisateurRepository.getIngredients(token, userId);
    }

}
