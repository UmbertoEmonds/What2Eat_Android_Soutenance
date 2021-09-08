package fr.projet2.what2eat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import fr.projet2.what2eat.R;
import fr.projet2.what2eat.model.Utilisateur;
import fr.projet2.what2eat.util.injections.Injection;
import fr.projet2.what2eat.util.injections.ViewModelFactory;
import fr.projet2.what2eat.viewmodel.UtilisateurViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView mForgotPasswordTV;
    private TextView mRegisterTV;
    private Button mLoginBtn;
    private EditText mMailET;
    private EditText mPasswordET;
    private UtilisateurViewModel mUtilisateurVM;
    private View contextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mForgotPasswordTV = findViewById(R.id.forgot_password);
        mRegisterTV = findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.mLoginBtn);
        mMailET = findViewById(R.id.editTextEmail);
        mPasswordET = findViewById(R.id.editTextPassword);

        contextView = findViewById(R.id.root);

        configureViewModel();

        mForgotPasswordTV.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        mRegisterTV.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        mMailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = mMailET.getText().toString().trim();
                String password = mPasswordET.getText().toString().trim();

                mLoginBtn.setEnabled(!email.isEmpty() && !password.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = mMailET.getText().toString().trim();
                String password = mPasswordET.getText().toString().trim();

                mLoginBtn.setEnabled(!email.isEmpty() && !password.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLoginBtn.setOnClickListener(v -> {

            String email = mMailET.getText().toString().trim();
            String password = mPasswordET.getText().toString().trim();

            mUtilisateurVM.login(email, password).observe(this, utilisateur -> {
                onLoginResponse(utilisateur);

                // supprimer l'observeur pour eviter les executions multiples
                mUtilisateurVM.login(email, password).removeObservers(this);
            });
        });
    }

    private void onLoginResponse(Utilisateur user){
        if(user != null){
            saveTokenToSharedPrefs(user.getToken(), user.getId());

            Intent intent = new Intent(LoginActivity.this, FrigoActivity.class);
            intent.putExtra("utilisateur", user);
            startActivity(intent);
        }else {
            Snackbar.make(contextView, getString(R.string.id_mdp_error), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void saveTokenToSharedPrefs(String token, int userId){
        SharedPreferences sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("token", token);
        editor.putInt("userId", userId);
        editor.apply();
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory();
        mUtilisateurVM = new ViewModelProvider(this, viewModelFactory).get(UtilisateurViewModel.class);
    }

}