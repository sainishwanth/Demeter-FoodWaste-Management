package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class settings_password extends AppCompatActivity {
    private EditText old_password;
    private EditText new_password;
    private EditText re_password;
    private MaterialButton submitbtn;
    private DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_password);
        old_password = findViewById(R.id.settings_old_password);
        new_password = findViewById(R.id.settings_new_password);
        re_password = findViewById(R.id.settings_re_password);
        submitbtn = findViewById(R.id.password_reset_button);
        DB = new DBHelper(this);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((old_password.getText().toString().equals(""))||(new_password.getText().toString()).equals("")||(re_password.getText().toString().equals("")))) {
                    if (DBHelper.checkpass.equals(old_password.getText().toString())) {
                        if (DBHelper.checkpass.equals(new_password.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "New password cannot be the same as old password", Toast.LENGTH_SHORT).show();
                        } else if (new_password.getText().toString().equals(re_password.getText().toString())) {
                            DB.changePass(DBHelper.checkuser, new_password.getText().toString());
                            DBHelper.checkpass = new_password.getText().toString();
                            Toast.makeText(getApplicationContext(), "Passwords has been reset", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}