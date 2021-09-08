package fr.projet2.what2eat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.projet2.what2eat.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView mEditPasswordTV;
    private ImageView mEditProfileInfosIV;
    private EditText mFirstNameET;
    private EditText mLastNameET;
    private EditText mMailET;

    private boolean isOnEditMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mEditPasswordTV = findViewById(R.id.editPasswordProfile);
        mEditProfileInfosIV = findViewById(R.id.editProfileInfo);
        mFirstNameET = findViewById(R.id.editTextFirstnameProfile);
        mLastNameET = findViewById(R.id.editTextLastnameProfile);
        mMailET = findViewById(R.id.editTextMailProfile);

        mEditPasswordTV.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(myIntent);
        });

        mEditProfileInfosIV.setOnClickListener(v -> {
            if(!isOnEditMode){
                mEditProfileInfosIV.setImageResource(R.drawable.outline_done_black_20);
                mFirstNameET.setEnabled(true);
                mLastNameET.setEnabled(true);
                mMailET.setEnabled(true);

                isOnEditMode = true;
            } else {

                // APPEL RESEAU

                mEditProfileInfosIV.setImageResource(R.drawable.outline_edit_black_20);
                mFirstNameET.setEnabled(false);
                mLastNameET.setEnabled(false);
                mMailET.setEnabled(false);

                isOnEditMode = false;
            }
        });
    }
}
