package fr.projet2.what2eat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.projet2.what2eat.R;
import fr.projet2.what2eat.adapter.FrigoAdapter;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.viewmodel.IngredientViewModel;
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
            Intent intent = new Intent(this, AddIngredientActivity.class);
            startActivity(intent);
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

        mUserVM.getIngredients(token, userId).observe(this, this::updateUI);
    }

    private void configureRecyclerView(){
        this.frigoAdapter = new FrigoAdapter(this.ingredientList);
        this.mFrigoRV.setAdapter(this.frigoAdapter);
        this.mFrigoRV.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void updateUI(List<Ingredient> ingredients){
        this.ingredientList = ingredients;
        configureRecyclerView();
        this.frigoAdapter.notifyDataSetChanged();
    }

}