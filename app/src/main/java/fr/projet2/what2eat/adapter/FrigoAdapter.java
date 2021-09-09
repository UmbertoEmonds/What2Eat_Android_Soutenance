package fr.projet2.what2eat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.model.Ingredient;

public class FrigoAdapter extends RecyclerView.Adapter<IngredientViewHolder> {

    private List<Ingredient> ingredients;

    public FrigoAdapter(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_line, parent, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bindView(this.ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return this.ingredients.size();
    }

}

class IngredientViewHolder extends RecyclerView.ViewHolder{

    private ImageView mIngredientImageIV;
    private TextView mQteTV;
    private TextView mNameTV;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        mIngredientImageIV = itemView.findViewById(R.id.ingredientImg);
        mQteTV = itemView.findViewById(R.id.qteIngredient);
        mNameTV = itemView.findViewById(R.id.name);
    }

    public void bindView(Ingredient ingredient){

        Picasso.Builder builder = new Picasso.Builder(itemView.getContext());

        builder.listener((picasso, uri, exception) -> mIngredientImageIV.setImageResource(R.drawable.outline_broken_image_black_20));
        builder.build().load(ingredient.getUrlImage()).into(mIngredientImageIV);

        int qte = ingredient.getQuantite();

        if(qte == 0){
            mQteTV.setText(String.valueOf(1));
        }else {
            mQteTV.setText(String.valueOf(ingredient.getQuantite()));
        }

        mNameTV.setText(ingredient.getNom());
    }

}