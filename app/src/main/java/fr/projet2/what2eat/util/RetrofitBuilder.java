package fr.projet2.what2eat.util;


import java.io.IOException;

import fr.projet2.what2eat.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static Retrofit retrofit;
    public static Retrofit retrofitOpenFood;

    public static Retrofit getInstance(String BASE_API_URL){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getInstanceForOpenFoodAPI(){

        if(retrofitOpenFood == null){
            retrofitOpenFood = new Retrofit.Builder()
                    .baseUrl(BuildConfig.OPEN_FOOD_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitOpenFood;
    }

}