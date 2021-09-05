package fr.projet2.what2eat.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.squareup.picasso.Picasso;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.activity.AddIngredientCameraActivity;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.OpenFoodAPI.ProduitOpenFood;

public class ScannerResultDialogFragment extends DialogFragment {

    public static final String TAG = "ScannerResultDialogFragment.TAG";
    private Ingredient ingredient;
    private AddIngredientCameraActivity parentActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle bundle = getArguments();
        parentActivity = (AddIngredientCameraActivity) getActivity();

        if(bundle != null){
            ingredient = (Ingredient) bundle.getSerializable("INGREDIENT_FOUND");
        }

        if(ingredient != null){
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_camera_result, null);

            ImageView ingredientImg = view.findViewById(R.id.ingredientThumb);

            Picasso.Builder picassoBuilder = new Picasso.Builder(view.getContext());
            picassoBuilder.listener((picasso, uri, exception) -> ingredientImg.setImageResource(R.drawable.outline_broken_image_black_24));
            picassoBuilder.build().load(ingredient.getUrlImage()).into(ingredientImg);

            builder.setView(view)
                    .setTitle(R.string.dialog_title)
                    .setMessage(ingredient.getNom())
                    .setPositiveButton(R.string.dialog_add, (dialog, which) -> {

                    }).setNegativeButton(R.string.dialog_error_dismiss, (dialog, which) -> {
                        dialog.dismiss();
                    parentActivity.resumeScanning();
                    });
        }else{
            builder.setTitle(R.string.dialog_title_error)
                    .setMessage(R.string.dialog_title_error_message)
                    .setPositiveButton(R.string.dialog_error_add, (dialog, which) -> {

                    })
                    .setNegativeButton(R.string.dialog_error_cancel, (dialog, which) -> {
                        dialog.dismiss();
                        parentActivity.resumeScanning();
                    });
        }

        return builder.create();
    }
}
