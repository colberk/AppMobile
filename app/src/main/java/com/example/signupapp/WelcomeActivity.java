package com.example.signupapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button getStartedButton = findViewById(R.id.get_started_button);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");
        if (!email.equals("")) {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        getStartedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button alreadyHaveAccountButton = findViewById(R.id.already_have_account_button);
        alreadyHaveAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}