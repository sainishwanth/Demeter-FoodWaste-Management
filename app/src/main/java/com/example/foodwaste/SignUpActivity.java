package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignUpActivity extends AppCompatActivity {
    private EditText username, password;
    private Button signup;
    private DBHelper DB;
    private FloatingActionButton backtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.supusername);
        password = findViewById(R.id.suppassword);
        signup = findViewById(R.id.signupbutton);
        DB = new DBHelper(this);
        backtn = findViewById(R.id.backtn);

        backtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(!(user.equals("") && (pass.equals("")))){
                     if(!DB.checkusername(user)){
                         if(DB.insertData(user,pass)){
                             Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                             finish();
                         }else{
                             Toast.makeText(SignUpActivity.this, "Registration Failed ", Toast.LENGTH_SHORT).show();
                         }
                     }else{
                         Toast.makeText(SignUpActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                     }
                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}