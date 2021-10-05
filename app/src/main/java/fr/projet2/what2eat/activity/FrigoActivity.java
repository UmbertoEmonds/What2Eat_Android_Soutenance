package fr.projet2.what2eat.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<Ingredient> ingredientsFilter = new ArrayList<>();
    private FrigoAdapter frigoAdapter;

    private UtilisateurViewModel mUserVM;

    private CircleImageView mProfileBtn;
    private FloatingActionButton mFAB;

    private LinearLayout mBoissonsLL;
    private LinearLayout mLegumesLL;
    private LinearLayout mViandesLL;
    private LinearLayout mConservesLL;
    private LinearLayout mFruitsLL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frigo);

        mFrigoRV = findViewById(R.id.frigoRV);
        mProfileBtn = findViewById(R.id.profile_image_toolbar);
        mFAB = findViewById(R.id.frigoFAB);

        mBoissonsLL = findViewById(R.id.BoissonsLayout);
        mLegumesLL = findViewById(R.id.LegumesLayout);
        mViandesLL = findViewById(R.id.ViandesLayout);
        mConservesLL = findViewById(R.id.ConservesLayout);
        mFruitsLL = findViewById(R.id.FruitsLayout);

        configureViewModel();
        getIngredients();

        mProfileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });

        mBoissonsLL.setOnClickListener(v -> {

            if(mBoissonsLL.getBackground() == null){
                setBackgroundTo(mBoissonsLL);
                removeBackgroundTo(mLegumesLL, mViandesLL, mConservesLL, mFruitsLL);
                ingredientsFilter.clear();

                for (Ingredient ingredient : ingredientList){
                    if(ingredient.getCategorie() != null){
                        String categoriesAccurate = ingredient.getCategorie().split(",")[1].toLowerCase();
                        if(categoriesAccurate.contains("boisson") || categoriesAccurate.contains("drink")){
                            ingredientsFilter.add(ingredient);
                        }
                    }

                }

                frigoAdapter.setIngredients(ingredientsFilter);
            }else {
                removeBackgroundTo(mLegumesLL, mViandesLL, mConservesLL, mFruitsLL, mBoissonsLL);
                frigoAdapter.setIngredients(ingredientList);
            }

            frigoAdapter.notifyDataSetChanged();

        });

        mLegumesLL.setOnClickListener(v -> {
            if(mLegumesLL.getBackground() == null){
                setBackgroundTo(mLegumesLL);
                removeBackgroundTo(mBoissonsLL, mViandesLL, mConservesLL, mFruitsLL);
                ingredientsFilter.clear();

                for (Ingredient ingredient : ingredientList){
                    if(ingredient.getCategorie() != null){
                        String categoriesAccurate = ingredient.getCategorie().split(",")[1].toLowerCase();

                        if(categoriesAccurate.contains("legume") || categoriesAccurate.contains("légume")){
                            ingredientsFilter.add(ingredient);
                        }
                    }

                }

                frigoAdapter.setIngredients(ingredientsFilter);

            }else {
                removeBackgroundTo(mBoissonsLL, mViandesLL, mConservesLL, mFruitsLL, mLegumesLL);
                frigoAdapter.setIngredients(ingredientList);
            }

            frigoAdapter.notifyDataSetChanged();

        });

        mViandesLL.setOnClickListener(v -> {
            if(mViandesLL.getBackground() == null){
                setBackgroundTo(mViandesLL);
                removeBackgroundTo(mBoissonsLL, mLegumesLL, mConservesLL, mFruitsLL);
                ingredientsFilter.clear();

                for (Ingredient ingredient : ingredientList){
                    if(ingredient.getCategorie() != null) {
                        String categoriesAccurate = ingredient.getCategorie().split(",")[1].toLowerCase();

                        if (categoriesAccurate.contains("viande")) {
                            ingredientsFilter.add(ingredient);
                        }
                    }
                }

                frigoAdapter.setIngredients(ingredientsFilter);

            }else {
                removeBackgroundTo(mBoissonsLL, mViandesLL, mConservesLL, mFruitsLL, mLegumesLL);
                frigoAdapter.setIngredients(ingredientList);
            }

            frigoAdapter.notifyDataSetChanged();

        });

        mConservesLL.setOnClickListener(v -> {
            if(mConservesLL.getBackground() == null){
                setBackgroundTo(mConservesLL);
                removeBackgroundTo(mLegumesLL, mViandesLL, mBoissonsLL, mFruitsLL);
                ingredientsFilter.clear();

                for (Ingredient ingredient : ingredientList){

                    if(ingredient.getCategorie() != null){
                        String categoriesAccurate = ingredient.getCategorie().split(",")[1].toLowerCase();

                        if(categoriesAccurate.contains("conserve")){
                            ingredientsFilter.add(ingredient);
                        }
                    }
                }

                frigoAdapter.setIngredients(ingredientsFilter);

            }else {
                removeBackgroundTo(mBoissonsLL, mViandesLL, mConservesLL, mFruitsLL, mLegumesLL);
                frigoAdapter.setIngredients(ingredientList);
            }

            frigoAdapter.notifyDataSetChanged();

        });

        mFruitsLL.setOnClickListener(v -> {
            if(mFruitsLL.getBackground() == null){
                setBackgroundTo(mFruitsLL);
                removeBackgroundTo(mConservesLL, mViandesLL, mBoissonsLL, mLegumesLL);
                ingredientsFilter.clear();

                for (Ingredient ingredient : ingredientList){

                    if(ingredient.getCategorie() != null){
                        String categoriesAccurate = ingredient.getCategorie().split(",")[1].toLowerCase();

                        if(categoriesAccurate.contains("fruit")){
                            ingredientsFilter.add(ingredient);
                        }
                    }
                }

                frigoAdapter.setIngredients(ingredientsFilter);
                frigoAdapter.notifyDataSetChanged();

            }else {
                removeBackgroundTo(mBoissonsLL, mViandesLL, mConservesLL, mFruitsLL, mLegumesLL);
                frigoAdapter.setIngredients(ingredientList);
                frigoAdapter.notifyDataSetChanged();
            }

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

    private void setBackgroundTo(LinearLayout linearLayout){
        linearLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_produit_item));
    }

    private void removeBackgroundTo(LinearLayout ...linearLayouts){
        for (LinearLayout e : linearLayouts){
            e.setBackground(null);
        }
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onIngredientAddedEvent(Ingredient ingredient){

        boolean isAlreadyAdded = false;

        for (Ingredient i : this.ingredientList){
            if(ingredient.getCode().equals(i.getCode())){
                int position = this.ingredientList.indexOf(i);

                i.setQuantite(i.getQuantite() + 1);

                this.ingredientList.set(position, i);
                isAlreadyAdded = true;
                break;
            }
        }
        if(!isAlreadyAdded){
            this.ingredientList.add(ingredient);
        }

        this.frigoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //on verifie que le code correspond bien a la requete
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(this, AddIngredientCameraActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}