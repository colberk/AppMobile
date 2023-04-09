package com.example.signupapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
            }
        });
    }


    private void validateCredentials(String email, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://localhost /login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseReceived(response,email,password);
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error occurred, show error message
                        Toast.makeText(LoginActivity.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Set POST parameters
                Map<String, String> params = new HashMap();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

    }

    protected void responseReceived(String response,String em,String password) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            if (status.equals("success")) {
                JSONObject userObject = jsonObject.getJSONObject("user_info");
                String family_name = userObject.getString("family_name");
                String first_name = userObject.getString("first_name");
                String email = userObject.getString("email");
                int age = userObject.getInt("age");
                String address = userObject.getString("address");

                // Save user information to shared preferences
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("family_name", family_name);
                editor.putString("first_name", first_name);
                editor.putString("email", email);
                editor.putInt("age", age);
                editor.putString("address", address);
                editor.apply();

                // Start HomeActivity
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                String errorMessage = jsonObject.getString("message");
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}