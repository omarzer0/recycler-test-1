package com.example.android.recylertest1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.android.recylertest1.utils.Constants.BOOLEAN_EXTRA;
import static com.example.android.recylertest1.utils.Constants.EMAIL_EXTRA;
import static com.example.android.recylertest1.utils.Constants.MY_PREFS_NAME;
import static com.example.android.recylertest1.utils.Constants.PASSWORD_EXTRA;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailEd, passwordEd, confirmPasswordEd;
    private Button signUpBtn;
    private TextView loginTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        setListeners();
    }

    private void initViews() {
        emailEd = findViewById(R.id.activity_sign_up_ed_email_edit_text);
        passwordEd = findViewById(R.id.activity_sign_up_ed_password_edit_text);
        confirmPasswordEd = findViewById(R.id.activity_sign_up_ed_confirm_password_edit_text);
        signUpBtn = findViewById(R.id.activity_sign_up_btn_sign_up_button);
        loginTv = findViewById(R.id.activity_sign_up_tv_login_text_view);


    }

    private void setListeners() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEd.getText().toString();
                String password = passwordEd.getText().toString();
                String confirmPassword = confirmPasswordEd.getText().toString();

                if (email.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!email.contains("@")) {
                        Toast.makeText(SignUpActivity.this, "please enter a valid email that contains @ symbol", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!password.equals(confirmPassword)) {
                            Toast.makeText(SignUpActivity.this, "password and confirm password does not match", Toast.LENGTH_SHORT).show();
                        } else {
                            saveToPreference(email, password);
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            Toast.makeText(SignUpActivity.this, "signed up successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }

    private void saveToPreference(String email, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(EMAIL_EXTRA, email);
        editor.putString(PASSWORD_EXTRA, password);
        editor.apply();
    }
}
