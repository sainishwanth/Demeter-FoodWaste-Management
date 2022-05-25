package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Settings_Activity extends AppCompatActivity {
    private MaterialButton password;
    private MaterialButton email;
    private MaterialButton phno;
    private MaterialButton address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        password = findViewById(R.id.settings_password);
        email = findViewById(R.id.settings_email);
        phno = findViewById(R.id.settings_phno);
        address = findViewById(R.id.settings_add);

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), settings_password.class);
                startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), settings_email.class);
                startActivity(intent);
            }
        });
        phno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), settings_phno.class);
                startActivity(intent);
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), settings_address.class);
                startActivity(intent);
            }
        });
    }
}