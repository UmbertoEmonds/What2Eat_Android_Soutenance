package fr.projet2.what2eat.service;

import android.widget.Toast;

import java.util.List;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.activity.FrigoActivity;
import fr.projet2.what2eat.util.RetrofitBuilder;
import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrigoService {

    private final FrigoActivity parent;

    public FrigoService(FrigoActivity parent){
        this.parent = parent;
    }

    public void getIngredients(){

        IFrigoService service = RetrofitBuilder.getInstance().create(IFrigoService.class);

        service.getIngredients().enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                parent.updateUI(response.body());
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                Toast.makeText(parent, parent.getString(R.string.error_get_ingredients), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
