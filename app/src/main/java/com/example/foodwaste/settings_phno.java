package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class settings_phno extends AppCompatActivity {
    private EditText change_phno;
    private DBHelper DB;
    private MaterialButton submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_phno);
        change_phno = findViewById(R.id.settings_new_phno);
        submitbtn = findViewById(R.id.phno_reset_button);
        DB = new DBHelper(this);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(change_phno.getText().toString().equals(""))) {
                    if (!(DBHelper.checkphno.equals(change_phno.getText().toString()))) {
                        DB.changePhno(DBHelper.checkuser, change_phno.getText().toString());
                        Toast.makeText(getApplicationContext(), "Phone Number has been changed", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "New phone number is the same as phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Phone Number field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}