package fr.projet2.what2eat.repositories.services;

import java.util.List;

import fr.projet2.what2eat.model.OpenFoodAPI.OpenFoodResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface OpenFoodService {

    @GET("products")
    Call<OpenFoodResponse> getProduits(@Query("excludes")List<String> excludes, @Query("barcodes") String barcode);

}
