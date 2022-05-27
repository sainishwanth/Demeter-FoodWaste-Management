package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText username, password, phno, email, address;
    private Button signup;
    private DBHelper DB;
    private FloatingActionButton backtn;
    private Spinner spinner;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spinner = findViewById(R.id.typespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try {
            spinner.setAdapter(adapter);
        }catch(NullPointerException e){}
        username = findViewById(R.id.supusername);
        password = findViewById(R.id.suppassword);
        signup = findViewById(R.id.signupbutton);
        phno = findViewById(R.id.supph);
        email = findViewById(R.id.supemail);
        address = findViewById(R.id.supadd);
        DB = new DBHelper(this);
        backtn = findViewById(R.id.backtn);
        backtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinner.setOnItemSelectedListener(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String dbpno = phno.getText().toString();
                String dbemail = email.getText().toString();
                String dbaddress = address.getText().toString();
                if (!(user.equals("") || (pass.equals("")) || (dbpno.equals("")) || (dbemail.equals("")) || (dbaddress.equals("")) || type.equals("Select"))) {
                    if (!DB.checkusername(user)) {
                        if (isValidEmail(dbemail)) {
                            if (DB.insertData(user, pass, dbpno, dbemail, dbaddress, type)) {
                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}