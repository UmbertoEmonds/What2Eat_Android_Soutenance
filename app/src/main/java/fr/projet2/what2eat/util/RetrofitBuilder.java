package fr.projet2.what2eat.util;


import fr.projet2.what2eat.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}