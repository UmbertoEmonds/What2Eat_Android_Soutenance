package fr.projet2.what2eat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.viewmodel.IngredientViewModel;
import fr.projet2.what2eat.viewmodel.UtilisateurViewModel;

public class MainActivity extends AppCompatActivity {

    private UtilisateurViewModel mUtilisateurVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViewModel();

        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        int userId = sharedPref.getInt("userId", -1);

        if(token != null && userId != -1){
            verifyToken(token, userId);
        }else {
            goToLoginActivity();
        }
    }

    private void verifyToken(String token, int userId){
        mUtilisateurVM.verifyToken(token, userId).observe(this, isValid -> {

            if(isValid != null && isValid){
                Intent myIntent = new Intent(this, FrigoActivity.class);
                startActivity(myIntent);
            }else {
                goToLoginActivity();
            }

            mUtilisateurVM.verifyToken(token, userId).removeObservers(this);
        });
    }

    private void goToLoginActivity(){
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        mUtilisateurVM = new ViewModelProvider(this, viewModelFactory).get(UtilisateurViewModel.class);
    }

}