package fr.projet2.what2eat.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import fr.projet2.what2eat.fragment.ScannerResultDialogFragment;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.OpenFoodAPI.ProduitOpenFood;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.viewmodel.IngredientOpenFoodViewModel;
import fr.projet2.what2eat.viewmodel.IngredientViewModel;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class AddIngredientCameraActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private List<ProduitOpenFood> produits;
    private IngredientOpenFoodViewModel mIngredientOpenFoodVM;
    private IngredientViewModel mIngredientVM;
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);

        configureViewModel();
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        mIngredientOpenFoodVM = new ViewModelProvider(this, viewModelFactory).get(IngredientOpenFoodViewModel.class);

        mIngredientVM = new ViewModelProvider(this, viewModelFactory).get(IngredientViewModel.class);
    }

    private void getIngredient(String barcode){
        Log.v("AZERTY", barcode);

        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        int userId = sharedPref.getInt("userId", -1);

        mIngredientVM.getIngredientFromBarcode(barcode, token, userId).observe(this, produit -> {
            updateUI(produit);

            mIngredientVM.getIngredientFromBarcode(barcode, token, userId).removeObservers(this);
        });

        /*
        mIngredientOpenFoodVM.getProduits(barcode).observe(this, produitOpenFoods -> {
            updateUI(produitOpenFoods);
            mIngredientOpenFoodVM.getProduits(barcode).removeObservers(this);
        });
        */
    }

    public void updateUI(Ingredient ingredient){
        ScannerResultDialogFragment resultDialog = new ScannerResultDialogFragment();
        Bundle bundle = new Bundle();

        try {
            bundle.putSerializable("INGREDIENT_FOUND", ingredient);
        }catch (IndexOutOfBoundsException e){
            bundle.putSerializable("INGREDIENT_FOUND", null);
        }

        resultDialog.setArguments(bundle);

        resultDialog.show(getSupportFragmentManager(), ScannerResultDialogFragment.TAG);
    }

    public void updateUI(List<ProduitOpenFood> produitOpenFoods){
        this.produits = produitOpenFoods;

        ScannerResultDialogFragment resultDialog = new ScannerResultDialogFragment();
        Bundle bundle = new Bundle();

        try {
            bundle.putSerializable("INGREDIENT_FOUND", produitOpenFoods.get(0));
        }catch (IndexOutOfBoundsException e){
            bundle.putSerializable("INGREDIENT_FOUND", null);
        }

        resultDialog.setArguments(bundle);

        new ScannerResultDialogFragment().show(getSupportFragmentManager(), ScannerResultDialogFragment.TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String barcode = rawResult.getContents();
        getIngredient(barcode);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void resumeScanning(){
        mScannerView.resumeCameraPreview(this);
    }

}
