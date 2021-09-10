package fr.projet2.what2eat.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;

import fr.projet2.what2eat.R;
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
    private boolean isScanned = false;

    // on declare l'observer final et en variable globale pour être sur qu'il ne soit appelé qu'une seule fois !!
    private final Observer<Ingredient> observeAddIngredient = ingredient -> {
        if(ingredient != null){
            EventBus.getDefault().postSticky(ingredient);
            finish();
        }
    };

    private final Observer<OpenFoodResponseAPI> observeOpenFoodResponseAPI = this::updateUI;

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
        mIngredientVM.getIngredientFromBarcode(barcode).observe(this, observeOpenFoodResponseAPI);
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

        if(ingredient != null){
            resultDialog.show(getSupportFragmentManager(), ScannerResultDialogFragment.TAG);
        }else {
            Snackbar.make(mScannerView, R.string.error_add_ingredient, Snackbar.LENGTH_SHORT).show();
            resumeScanning();
        }
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
        if(!isScanned){
            String barcode = rawResult.getContents();
            getIngredient(barcode);
            isScanned = true;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void resumeScanning(){
        mScannerView.resumeCameraPreview(this);
    }

    public void addIngredient(String token, int userId, Ingredient ingredientToAdd){
        mIngredientVM.addIngredient(token, userId, ingredientToAdd).observe(this, observeAddIngredient);
    }
}