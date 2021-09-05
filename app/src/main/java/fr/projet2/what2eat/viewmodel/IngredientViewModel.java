package fr.projet2.what2eat.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.repositories.IngredientRepository;

public class IngredientViewModel extends ViewModel {

    private IngredientRepository ingredientRepository;

    public IngredientViewModel (IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public MutableLiveData<List<Ingredient>> getIngredients(){
        return ingredientRepository.getIngredients();
    }

    public MutableLiveData<Ingredient> getIngredientFromBarcode(String barcode, String token, int userId){
        return ingredientRepository.getIngredientFromBarcode(barcode, token, userId);
    }

}