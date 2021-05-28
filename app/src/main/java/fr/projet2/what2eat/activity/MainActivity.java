package fr.projet2.what2eat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fr.projet2.what2eat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SI L'UTILISATEUR N'EST PAS CONNECTE
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);



        // SI UTILISATEUR CONNECTE ENVOYER DIRECTEMENT VERS PAGE ACCUEIL (frigo ou recettes ?)


    }
}