package fr.projet2.what2eat.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.adapter.FrigoAdapter;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.viewmodel.IngredientViewModel;

public class FrigoActivity extends AppCompatActivity {

    private RecyclerView mFrigoRV;
    private List<Ingredient> ingredientList;
    private FrigoAdapter frigoAdapter;

    private IngredientViewModel mIngredientVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frigo);

        mFrigoRV = findViewById(R.id.frigoRV);

        configureViewModel();
        getIngredients();
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        mIngredientVM = new ViewModelProvider(this, viewModelFactory).get(IngredientViewModel.class);
    }

    private void getIngredients(){
        mIngredientVM.getIngredients().observe(this, this::updateUI);
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