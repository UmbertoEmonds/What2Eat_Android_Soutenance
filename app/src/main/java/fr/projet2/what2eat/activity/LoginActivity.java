package fr.projet2.what2eat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.projet2.what2eat.R;

public class LoginActivity extends AppCompatActivity {

    private TextView mForgotPasswordTV;
    private TextView mRegisterTV;
    private Button mLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mForgotPasswordTV = findViewById(R.id.forgot_password);
        mRegisterTV = findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.mLoginBtn);

        mForgotPasswordTV.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        mRegisterTV.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        mLoginBtn.setOnClickListener(v -> {
            //TODO("Implementer la logique de connexion")
            Intent intent = new Intent(this, FrigoActivity.class);
            startActivity(intent);
        });
    }
}