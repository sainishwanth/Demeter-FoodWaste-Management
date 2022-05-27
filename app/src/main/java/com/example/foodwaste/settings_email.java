package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class settings_email extends AppCompatActivity {
    private EditText change_email;
    private DBHelper DB;
    private MaterialButton submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_email);
        change_email = findViewById(R.id.settings_new_email);
        submitbtn = findViewById(R.id.email_reset_button);
        DB = new DBHelper(this);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(change_email.getText().toString().equals(""))) {
                    if (isValidEmail(change_email.getText().toString())) {
                        if (!(DBHelper.checkemail.equals(change_email.getText().toString()))) {
                            DB.changeEmail(DBHelper.checkuser, change_email.getText().toString());
                            Toast.makeText(getApplicationContext(), "Email has been changed", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "New email is the same as old email", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Email field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}