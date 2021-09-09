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

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.viewmodel.UtilisateurViewModel;

public class ProfileActivity extends AppCompatActivity {

    private TextView mEditPassword;
    private ImageView mEditProfileInfosIV;
    private EditText mPrenomET;
    private EditText mNomET;
    private EditText mMailET;
    private Button mConfirmBtn;
    private Button mCancelBtn;
    private boolean isOnEditMode = false;
    private UtilisateurViewModel mUserVM;
    private ImageView mBackBtn;
    private EditText mPasswordET;
    private EditText mPasswordConfirmET;
    private Utilisateur mUtilisateur;
    private View contextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mEditPassword = findViewById(R.id.editPasswordProfile);
        mEditProfileInfosIV = findViewById(R.id.editProfileInfo);
        mPrenomET = findViewById(R.id.editTextPrenomProfile);
        mNomET = findViewById(R.id.editTextNomProfile);
        mMailET = findViewById(R.id.editTextMailProfile);
        mConfirmBtn = findViewById(R.id.mConfirmBtn);
        mCancelBtn = findViewById(R.id.mCancelBtn);
        mBackBtn = findViewById(R.id.BackBtn);
        mPasswordET = findViewById(R.id.password);
        mPasswordConfirmET = findViewById(R.id.passwordConfirm);
        contextView = findViewById(R.id.root);

        mBackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FrigoActivity.class);
            startActivity(intent);
        });

        mEditProfileInfosIV.setOnClickListener(v -> {
            if(isOnEditMode){
                if(!mUtilisateur.getPrenom().equals(mPrenomET.getText().toString().trim()) ||
                        !mUtilisateur.getNom().equals(mNomET.getText().toString().trim()) ||
                            !mUtilisateur.getMail().equals(mMailET.getText().toString().trim())) {
                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    if (!p.matcher(mPrenomET.getText().toString().trim()).find() && !p.matcher(mNomET.getText().toString().trim()).find() && !p.matcher(mMailET.getText().toString().trim()).find() ){
                        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
                        String token = sharedPref.getString("token", null);
                        int userId = sharedPref.getInt("userId", -1);
                        mUtilisateur.setPrenom(mPrenomET.getText().toString().trim());
                        mUtilisateur.setNom(mNomET.getText().toString().trim());
                        mUtilisateur.setMail(mMailET.getText().toString().trim());
                        mUserVM.updateUtilisateur(token, mUtilisateur).observe(this, utilisateurR -> {
                            if(utilisateurR != null){
                                mPrenomET.setText(utilisateurR.getPrenom());
                                mNomET.setText(utilisateurR.getNom());
                                mMailET.setText(utilisateurR.getMail());
                                Snackbar.make(contextView, getString(R.string.succes_modif_profil), Snackbar.LENGTH_SHORT).show();
                            }else{
                                Snackbar.make(contextView, getString(R.string.error_modif_profil), Snackbar.LENGTH_SHORT).show();
                            }
                            mUserVM.verifyToken(token, userId).removeObservers(this);
                        });


                    }else {
                        Snackbar.make(contextView, getString(R.string.error_saisie), Snackbar.LENGTH_SHORT).show();
                    }
                }
                mEditProfileInfosIV.setImageResource(R.drawable.outline_edit_black_20);
                mPrenomET.setEnabled(false);
                mNomET.setEnabled(false);
                mMailET.setEnabled(false);
                isOnEditMode = false;
            }else {
                mEditProfileInfosIV.setImageResource(R.drawable.outline_done_black_20);
                mPrenomET.setEnabled(true);
                mNomET.setEnabled(true);
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
        getUtilisateur();

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

    private void getUtilisateur(){
        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        int userId = sharedPref.getInt("userId", -1);

        mUserVM.getUtilisateur(token, userId).observe(this, utilisateur ->  {
            if(utilisateur != null){
            mPrenomET.setText(utilisateur.getPrenom());
            mNomET.setText(utilisateur.getNom());
            mMailET.setText(utilisateur.getMail());
            this.mUtilisateur=utilisateur;
            mUserVM.verifyToken(token, userId).removeObservers(this);
            }
        });
    }


}
