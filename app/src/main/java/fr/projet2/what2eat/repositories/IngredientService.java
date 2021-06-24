package fr.projet2.what2eat.repositories;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IngredientService {

    @GET("/ingredients")
    Call<List<Ingredient>> getIngredients();

}