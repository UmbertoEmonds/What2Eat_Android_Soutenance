package fr.projet2.what2eat.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.adapter.FrigoAdapter;
import fr.projet2.what2eat.service.FrigoService;
import fr.projet2.what2eat.model.Ingredient;

public class FrigoActivity extends AppCompatActivity {

    private RecyclerView mFrigoRV;
    private FrigoService frigoService;
    private List<Ingredient> ingredientList;
    private FrigoAdapter frigoAdapter;

    public FrigoActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frigo);

        mFrigoRV = findViewById(R.id.frigoRV);
        frigoService = new FrigoService(this);

        frigoService.getIngredients();
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