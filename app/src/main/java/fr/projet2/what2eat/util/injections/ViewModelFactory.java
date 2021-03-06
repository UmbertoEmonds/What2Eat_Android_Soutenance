package fr.projet2.what2eat.util.injections;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import fr.projet2.what2eat.repositories.IngredientRepository;
import fr.projet2.what2eat.repositories.UtilisateurRepository;
import fr.projet2.what2eat.viewmodel.IngredientViewModel;
import fr.projet2.what2eat.viewmodel.UtilisateurViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == IngredientViewModel.class){
            return (T) new IngredientViewModel(new IngredientRepository());
        }else if (modelClass == UtilisateurViewModel.class){
            return (T) new UtilisateurViewModel(new UtilisateurRepository());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}