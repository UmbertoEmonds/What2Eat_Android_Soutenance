package fr.projet2.what2eat.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.repositories.utilisateur.UtilisateurRepository;

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

}
