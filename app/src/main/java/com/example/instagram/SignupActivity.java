package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etEmail;
    private EditText etFullName;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = findViewById(R.id.etEmail);
        etFullName = findViewById(R.id.etFullName);
        etUsername = findViewById(R.id.etUsername_Signup);
        etPassword = findViewById(R.id.etPassword_Signup);
        btnSignup = findViewById(R.id.btnSignup);
        
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signup button");
                String email = etEmail.getText().toString();
                String fullName = etFullName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signupUser(email, fullName, username, password);
            }
        });
    }

    private void signupUser(String email, String fullName, String username, String password) {
        Log.i(TAG, "Attempting to signup user " + username);

        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setEmail(email);
        user.put("fullName", fullName);
        user.setUsername(username);
        user.setPassword(password);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with signup", e);
                    return;
                }
                goMainActivity();
                Toast.makeText(SignupActivity.this, "Successfully signed up!", Toast.LENGTH_LONG).show();
            }
            });


    }

    private void goMainActivity() {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);

        // To remove this activity from the stack so user can not go back to login screen
        finish();
    }
}