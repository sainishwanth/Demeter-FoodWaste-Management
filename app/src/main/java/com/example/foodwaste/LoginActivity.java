package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton login = findViewById(R.id.LoginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("test") && password.getText().toString().equals("test")){
                    Toast.makeText(LoginActivity.this, "Logged In! ", Toast.LENGTH_SHORT).show();
                    MainPageActivity();
                }else{
                    Toast.makeText(LoginActivity.this, "Wrong Username or Password! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void MainPageActivity(){
        Intent intent = new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }
}