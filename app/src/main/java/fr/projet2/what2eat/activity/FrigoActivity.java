package fr.projet2.what2eat.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.projet2.what2eat.R;
import fr.projet2.what2eat.adapter.FrigoAdapter;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.viewmodel.UtilisateurViewModel;

public class FrigoActivity extends AppCompatActivity {

    private RecyclerView mFrigoRV;
    private List<Ingredient> ingredientList;
    private FrigoAdapter frigoAdapter;

    private UtilisateurViewModel mUserVM;

    private CircleImageView mProfileBtn;
    private FloatingActionButton mFAB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frigo);

        mFrigoRV = findViewById(R.id.frigoRV);
        mProfileBtn = findViewById(R.id.profile_image_toolbar);
        mFAB = findViewById(R.id.frigoFAB);

        configureViewModel();
        getIngredients();

        mProfileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });

        mFAB.setOnClickListener(v -> {
            // check si l'utilisateur a une camera
            if (getApplicationContext().getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FRONT)) {

                // check de la persmission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
                }else {
                    // permission deja accordee
                    Intent intent = new Intent(this, AddIngredientCameraActivity.class);
                    startActivity(intent);
                }
            }else {
                // ajout manuel de l'ingredient
            }
        });
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        mUserVM = new ViewModelProvider(this, viewModelFactory).get(UtilisateurViewModel.class);
    }

    private void getIngredients(){
        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        int userId = sharedPref.getInt("userId", -1);

        mUserVM.getIngredients(token, userId).observe(this, ingredients ->  {
            updateUI(ingredients);
            mUserVM.getIngredients(token, userId).removeObservers(this);
        });
    }

    private void configureRecyclerView(){
        this.frigoAdapter = new FrigoAdapter(this.ingredientList);
        this.mFrigoRV.setAdapter(this.frigoAdapter);
        this.mFrigoRV.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void updateUI(List<Ingredient> ingredients){
        if(ingredients != null) {
            this.ingredientList = ingredients;
            configureRecyclerView();
            this.frigoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // depuis AddIngredientCameraActivity
        if(requestCode == 45){
            if(data != null){
                Bundle bundle = data.getExtras();
                Ingredient ingredientAdded = (Ingredient) bundle.getSerializable("INGREDIENT_ADDED");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //on verifie que le code correspond bien a la requete
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(this, AddIngredientCameraActivity.class);
                startActivityForResult(intent, 45);
            }
        }
    }
}