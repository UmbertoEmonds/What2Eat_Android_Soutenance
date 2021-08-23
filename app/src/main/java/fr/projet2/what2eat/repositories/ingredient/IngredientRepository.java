package fr.projet2.what2eat.repositories.ingredient;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import fr.projet2.what2eat.util.RetrofitBuilder;
import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientRepository {

    private final MutableLiveData<List<Ingredient>> ingredients;

    public IngredientRepository(){
        this.ingredients = new MutableLiveData<>();
    }

    public MutableLiveData<List<Ingredient>> getIngredients(){

        IngredientService service = RetrofitBuilder.getInstance().create(IngredientService.class);

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
}