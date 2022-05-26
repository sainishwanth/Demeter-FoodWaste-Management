package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.transition.MaterialElevationScale;

public class settings_address extends AppCompatActivity {
    private EditText change_address;
    private DBHelper DB;
    private MaterialButton submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_address);
        change_address = findViewById(R.id.settings_new_address);
        DB = new DBHelper(this);
        submitbtn = findViewById(R.id.address_reset_button);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(change_address.getText().toString().equals(""))){
                    DB.changeAdd(DBHelper.checkuser, change_address.getText().toString());
                    Toast.makeText(getApplicationContext(), "Address has been changed", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Address field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}