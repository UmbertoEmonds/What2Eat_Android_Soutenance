package fr.projet2.what2eat.repositories.services;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.OpenFoodAPI.OpenFoodResponseAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IngredientService {

    @GET("ingredients")
    Call<List<Ingredient>> getIngredients();

    @GET("product/{barcode}")
    Call<OpenFoodResponseAPI> getIngredientFromBarcode(@Path("barcode") String barcode);

    @POST("ingredient")
    Call<Ingredient> addIngredient(@Query("token") String token, @Query("idUser") int userId, @Body Ingredient ingredient);

}