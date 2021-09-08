package fr.projet2.what2eat.repositories;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

import fr.projet2.what2eat.BuildConfig;
import fr.projet2.what2eat.model.OpenFoodAPI.OpenFoodResponseAPI;
import fr.projet2.what2eat.repositories.services.IngredientService;
import fr.projet2.what2eat.util.RetrofitBuilder;
import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {

    private final MutableLiveData<List<Ingredient>> ingredients;
    private final MutableLiveData<OpenFoodResponseAPI> openFoodResponseAPI;
    private final MutableLiveData<Ingredient> ingredientAdded;

    public IngredientRepository(){
        this.ingredients = new MutableLiveData<>();
        this.openFoodResponseAPI = new MutableLiveData<>();
        this.ingredientAdded = new MutableLiveData<>();
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

    public MutableLiveData<OpenFoodResponseAPI> getIngredientFromBarcode(String barcode){
        IngredientService service = RetrofitBuilder.getInstanceForOpenFoodAPI().create(IngredientService.class);

        service.getIngredientFromBarcode(barcode).enqueue(new Callback<OpenFoodResponseAPI>() {
            @Override
            public void onResponse(Call<OpenFoodResponseAPI> call, Response<OpenFoodResponseAPI> response) {
                if(response.code() == 200){
                    openFoodResponseAPI.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<OpenFoodResponseAPI> call, Throwable t) {
                openFoodResponseAPI.setValue(null);
            }
        });

        return openFoodResponseAPI;
    }

    public MutableLiveData<Ingredient> addIngredient(String token, int userId, Ingredient ingredient){
        IngredientService service = RetrofitBuilder.getInstance(BuildConfig.API_URL).create(IngredientService.class);

        service.addIngredient(token, userId, ingredient).enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                ingredientAdded.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable t) {
                ingredientAdded.setValue(null);
            }
        });

        return ingredientAdded;
    }
}