package fr.projet2.what2eat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.regex.Pattern;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.viewmodel.UtilisateurViewModel;

public class ProfileActivity extends AppCompatActivity {

    private TextView mEditPassword;
    private ImageView mEditProfileInfosIV;
    private EditText mFirstNameET;
    private EditText mLastNameET;
    private EditText mMailET;
    private Button mConfirmBtn;
    private Button mCancelBtn;
    private boolean isOnEditMode = false;
    private  TextView mEditErreur;
    private UtilisateurViewModel mUserVM;
    private ImageView mBackBtn;
    private EditText mPasswordET;
    private EditText mPasswordConfirmET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mEditPassword = findViewById(R.id.editPasswordProfile);
        mEditProfileInfosIV = findViewById(R.id.editProfileInfo);
        mFirstNameET = findViewById(R.id.editTextFirstnameProfile);
        mLastNameET = findViewById(R.id.editTextLastnameProfile);
        mMailET = findViewById(R.id.editTextMailProfile);
        mConfirmBtn = findViewById(R.id.mConfirmBtn);
        mCancelBtn = findViewById(R.id.mCancelBtn);
        mEditErreur = findViewById(R.id.editErreur);
        mBackBtn = findViewById(R.id.BackBtn);
        mPasswordET = findViewById(R.id.password);
        mPasswordConfirmET = findViewById(R.id.passwordConfirm);

        mBackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FrigoActivity.class);
            startActivity(intent);
        });

        mEditProfileInfosIV.setOnClickListener(v -> {
            if(isOnEditMode){
                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                if (!p.matcher(mFirstNameET.getText().toString().trim()).find() &&
                        !p.matcher(mLastNameET.getText().toString().trim()).find() &&
                            !p.matcher(mMailET.getText().toString().trim()).find()) {
                    SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
                    String token = sharedPref.getString("token", null);
                    int userId = sharedPref.getInt("userId", -1);
                    mUserVM.updateUtilisateur(token, userId,mFirstNameET.getText().toString().trim(),mLastNameET.getText().toString().trim(),mMailET.getText().toString().trim()).observe(this, utilisateur -> {
                        if(utilisateur != null){
                            mFirstNameET.setText(utilisateur.getPrenom());
                            mLastNameET.setText(utilisateur.getNom());
                            mMailET.setText(utilisateur.getNom());
                            mUserVM.verifyToken(token, userId).removeObservers(this);
                        }
                    });
                    // APPEL RESEAU


                    mEditErreur.setVisibility(View.INVISIBLE);
                    mEditProfileInfosIV.setImageResource(R.drawable.outline_edit_black_20);
                    mFirstNameET.setEnabled(false);
                    mLastNameET.setEnabled(false);
                    mMailET.setEnabled(false);

                    isOnEditMode = false;
                }else {
                    mEditErreur.setVisibility(View.VISIBLE);
                    mEditErreur.setText("Erreur de Saisie (A-z 0-9)");
                }
            }else {
                mEditProfileInfosIV.setImageResource(R.drawable.outline_done_black_20);
                mFirstNameET.setEnabled(true);
                mLastNameET.setEnabled(true);
                mMailET.setEnabled(true);
                isOnEditMode = true;
            }
        });
        mEditPassword.setOnClickListener(v -> {
            changeVisibily(View.VISIBLE);

        });
        mConfirmBtn.setOnClickListener(v -> {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            //faire l'egalite
            if (!p.matcher(mPasswordET.getText().toString().trim()).find() &&
                    !p.matcher(mPasswordConfirmET.getText().toString().trim()).find()) {
                SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
                String token = sharedPref.getString("token", null);
                int userId = sharedPref.getInt("userId", -1);
                mUserVM.updatePassword(token, userId, mPasswordET.getText().toString().trim()).observe(this, utilisateur -> {
                    if (utilisateur != null) {
                        //Faire un message de notification
                    }
                });
            }
        });
        mCancelBtn.setOnClickListener(v -> {
            changeVisibily(View.INVISIBLE);

        });

        configureViewModel();
        getIngredients();

    }
    private void changeVisibily(int change){
        findViewById(R.id.linearPassword).setVisibility(change);
        findViewById(R.id.linearPasswordConfirm).setVisibility(change);
        findViewById(R.id.linearBtn).setVisibility(change);
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        mUserVM = new ViewModelProvider(this, viewModelFactory).get(UtilisateurViewModel.class);
    }

    private void getIngredients(){
        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        int userId = sharedPref.getInt("userId", -1);

        mUserVM.getUtilisateur(token, userId).observe(this, utilisateur ->  {
            if(utilisateur != null){
            mFirstNameET.setText(utilisateur.getPrenom());
            mLastNameET.setText(utilisateur.getNom());
            mMailET.setText(utilisateur.getNom());
            mUserVM.verifyToken(token, userId).removeObservers(this);
            }
        });
    }


}
