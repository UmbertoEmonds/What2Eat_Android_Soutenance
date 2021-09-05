package fr.projet2.what2eat.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.projet2.what2eat.model.OpenFoodAPI.ProduitOpenFood;
import fr.projet2.what2eat.repositories.OpenFoodRepository;

public class IngredientOpenFoodViewModel extends ViewModel {

    private OpenFoodRepository openFoodRepository;

    public IngredientOpenFoodViewModel(OpenFoodRepository openFoodRepository) {
        this.openFoodRepository = openFoodRepository;
    }

    public MutableLiveData<List<ProduitOpenFood>> getProduits(String barcode){
        return openFoodRepository.getProduits(barcode);
    }
}
