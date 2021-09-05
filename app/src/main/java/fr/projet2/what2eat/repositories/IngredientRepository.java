package fr.projet2.what2eat.repositories;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

import fr.projet2.what2eat.BuildConfig;
import fr.projet2.what2eat.repositories.services.IngredientService;
import fr.projet2.what2eat.util.RetrofitBuilder;
import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {

    private final MutableLiveData<List<Ingredient>> ingredients;
    private final MutableLiveData<Ingredient> ingredient;

    public IngredientRepository(){
        this.ingredients = new MutableLiveData<>();
        this.ingredient = new MutableLiveData<>();
    }

    public MutableLiveData<List<Ingredient>> getIngredients(){

        IngredientService service = RetrofitBuilder.getInstance(BuildConfig.API_URL).create(IngredientService.class);

        service.getIngredients().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if(response.code() == 200){
                    ingredients.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                ingredients.postValue(null);
            }
        });

        return ingredients;
    }

    public MutableLiveData<Ingredient> getIngredientFromBarcode(String barcode, String token, int userId){
        IngredientService service = RetrofitBuilder.getInstance(BuildConfig.API_URL).create(IngredientService.class);

        service.getIngredientFromBarcode(barcode, token, userId).enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                if(response.code() == 200){
                    ingredient.setValue(response.body());
                }else {
                    ingredient.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable t) {
                ingredient.setValue(null);
            }
        });
        return ingredient;
    }
}