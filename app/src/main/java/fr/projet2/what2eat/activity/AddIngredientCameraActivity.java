package fr.projet2.what2eat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import fr.projet2.what2eat.fragment.ScannerResultDialogFragment;
import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.OpenFoodAPI.OpenFoodResponseAPI;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.viewmodel.IngredientViewModel;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class AddIngredientCameraActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private IngredientViewModel mIngredientVM;
    private ZBarScannerView mScannerView;

    public IngredientViewModel getmIngredientVM() {
        return mIngredientVM;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);

        configureViewModel();
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();

        mIngredientVM = new ViewModelProvider(this, viewModelFactory).get(IngredientViewModel.class);
    }

    private void getIngredient(String barcode){
        mIngredientVM.getIngredientFromBarcode(barcode).observe(this, this::updateUI);
    }

    public void updateUI(OpenFoodResponseAPI ingredient){
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

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

        Log.v("AZERTY", "onResume");
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

    public void addIngredient(String token, int userId, Ingredient ingredientToAdd){
        mIngredientVM.addIngredient(token, userId, ingredientToAdd).observe(this, ingredient -> {

            Intent intent = new Intent();
            intent.putExtra("INGREDIENT_ADDED", ingredient);

            setResult(45, intent);
            finish();

            mIngredientVM.addIngredient(token, userId, ingredientToAdd).removeObservers(this);
        });
    }

}
