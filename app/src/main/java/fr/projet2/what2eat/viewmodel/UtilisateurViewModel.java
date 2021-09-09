package fr.projet2.what2eat.viewmodel;

import androidx.lifecycle.LiveData;
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
    public MutableLiveData<Utilisateur> getUtilisateur(String token, int userId){
        return this.utilisateurRepository.getUtilisateur(token, userId);
    }

    public MutableLiveData<Utilisateur> updateUtilisateur(String token,Utilisateur utilisateur) {
        return this.utilisateurRepository.updateUtilisateur(token, utilisateur);
    }

    public  MutableLiveData<Utilisateur> updatePassword(String token, int userId, String password) {
        return this.utilisateurRepository.updatePassword(token,userId,password);
    }

    public MutableLiveData<Utilisateur> updateUtilisateur(String token, int userId, String prenom, String nom, String mail) {
        return this.utilisateurRepository.updateUtilisateur(token,userId,prenom,nom,mail);
    }
}
