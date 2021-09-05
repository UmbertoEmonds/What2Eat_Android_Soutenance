package fr.projet2.what2eat.repositories.services;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IngredientService {

    @GET("ingredients")
    Call<List<Ingredient>> getIngredients();

    @GET("ingredient")
    Call<Ingredient> getIngredientFromBarcode(@Query("barcode") String barcode, @Query("token") String token, @Query("userId") int userId);

}