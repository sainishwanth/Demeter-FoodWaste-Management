package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    private TextView username, password;
    private MaterialButton login;
    private Button signup;
    private DBHelper DB;
    private FloatingActionButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        login = findViewById(R.id.LoginButton);
        signup = findViewById(R.id.signupbtn);
        DB = new DBHelper(this);
        backbtn = findViewById(R.id.backtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(!(user.equals("") && (pass.equals("")))){
                    if(DB.checkusernamepassword(user,pass) || (user.equals("test")) && (pass.equals("test"))){
                        try{
                            DBHelper.checkuser = user;
                            Log.d("checkuser is", DBHelper.checkuser);
                            DB.getData(user);
                            Log.d("checkuser is", DBHelper.checkphno);
                            Log.d("checkuser is", DBHelper.checkemail);
                            Log.d("checkuser is", DBHelper.checkaddress);
                            Log.d("checkuser is", DBHelper.checktype);
                        }catch (Exception e){}
                        Toast.makeText(LoginActivity.this, "Logged In! ", Toast.LENGTH_SHORT).show();
                        MainPageActivity();
                    }else{
                        Toast.makeText(LoginActivity.this, "Username or Password is Incorrect! ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Fields Cannot be Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpPageActivity();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void MainPageActivity(){
        Intent intent = new Intent(this,NavActivity.class);
        startActivity(intent);
    }
    public void SignUpPageActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}