package com.example.signupapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);



        String firstName = preferences.getString("family_name", "");
        String familyName = preferences.getString("first_name", "");
        TextView welcomeTextView = findViewById(R.id.welcome_textview);
        welcomeTextView.setText("Hello " + firstName + " " + familyName);
    }


}