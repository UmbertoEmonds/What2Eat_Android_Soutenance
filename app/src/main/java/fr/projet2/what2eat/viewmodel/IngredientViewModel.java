package fr.projet2.what2eat.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.OpenFoodAPI.OpenFoodResponseAPI;
import fr.projet2.what2eat.repositories.IngredientRepository;

public class IngredientViewModel extends ViewModel {

    private IngredientRepository ingredientRepository;

    public IngredientViewModel (IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public MutableLiveData<List<Ingredient>> getIngredients(){
        return ingredientRepository.getIngredients();
    }

    public MutableLiveData<OpenFoodResponseAPI> getIngredientFromBarcode(String barcode){
        return ingredientRepository.getIngredientFromBarcode(barcode);
    }

    public MutableLiveData<Ingredient> addIngredient(String token, int userId, Ingredient ingredient){
        return ingredientRepository.addIngredient(token, userId, ingredient);
    }

}