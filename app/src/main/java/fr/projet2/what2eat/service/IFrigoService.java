package fr.projet2.what2eat.service;

import java.util.List;

import fr.projet2.what2eat.model.Ingredient;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IFrigoService {

    @GET("/ingredients")
    Call<List<Ingredient>> getIngredients();

}
