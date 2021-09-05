package fr.projet2.what2eat.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import fr.projet2.what2eat.BuildConfig;
import fr.projet2.what2eat.model.OpenFoodAPI.OpenFoodResponse;
import fr.projet2.what2eat.model.OpenFoodAPI.ProduitOpenFood;
import fr.projet2.what2eat.repositories.services.OpenFoodService;
import fr.projet2.what2eat.util.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenFoodRepository {

    private final MutableLiveData<List<ProduitOpenFood>> produits;

    public OpenFoodRepository() {
        this.produits = new MutableLiveData<>();
    }

    public MutableLiveData<List<ProduitOpenFood>> getProduits(String barcode){

        OpenFoodService service = RetrofitBuilder.getInstanceForOpenFoodAPI().create(OpenFoodService.class);

        List<String> exludes = new ArrayList<>();

        exludes.add("nutrients");
        exludes.add("name_translations");
        exludes.add("ingredients_translations");
        exludes.add("status");
        exludes.add("quantity");
        exludes.add("alcohol_by_volume");
        exludes.add("portion_unit");
        exludes.add("portion_quantity");
        exludes.add("hundred_unit");
        exludes.add("created_at");
        exludes.add("updated_at");

        service.getProduits( exludes, barcode).enqueue(new Callback<OpenFoodResponse>() {
            @Override
            public void onResponse(Call<OpenFoodResponse> call, Response<OpenFoodResponse> response) {
                if(response.body() != null){
                    produits.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<OpenFoodResponse> call, Throwable t) {
                produits.setValue(null);
            }
        });

        return produits;
    }

}
