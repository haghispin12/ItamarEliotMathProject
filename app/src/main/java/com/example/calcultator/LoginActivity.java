package com.example.calcultator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.username);
        loginButton = findViewById(R.id.loginButton);

        // מאזין ללחיצה על כפתור Login
        loginButton.setOnClickListener(v -> {
            String user = username.getText().toString().trim();

            if (user.isEmpty()) {
                Toast.makeText(this, "אנא הזן שם משתמש", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });



    }

    private void putExtraData(EditText username) {
    }


}