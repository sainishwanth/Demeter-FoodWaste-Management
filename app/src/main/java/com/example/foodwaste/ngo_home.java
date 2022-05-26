package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ngo_home extends AppCompatActivity {
    private DBForm Db;
    private List<String> list_item;
    private List<String> list_purchaseDate;
    private List<String> list_expDate;
    private List<String> list_address;
    private List<String> list_phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_home);
        ScrollView home_ngo = findViewById(R.id.activity_ngo_home);
        home_ngo.setBackgroundResource(R.drawable.background2gg);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);
        home_ngo.addView(linearLayout);
        Db = new DBForm(this);
        list_item = new ArrayList<String>();
        list_item = Db.getItem();
        list_purchaseDate = new ArrayList<String>();
        list_purchaseDate = Db.get_purchaseDate();
        list_expDate = new ArrayList<String>();
        list_expDate = Db.get_expDate();
        list_address = new ArrayList<String>();
        list_address = Db.get_address();
        list_phno = new ArrayList<String>();
        list_phno = Db.get_phno();
        int count = Db.getCount();
        Log.d("Count: ", String.valueOf(count));
        TextView[] view_array = new TextView[count];
        for (int i = 0; i < count; ++i) {
            view_array[i] = new TextView(this);
        }
        for (int i = 0; i < count; ++i) {
            TextView text_view = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 20, 1, 50);
            view_array[i].setLayoutParams(params);
            String Text = "Item- " + list_item.get(i) + "\n" +
                    "Purchase Date- " + list_purchaseDate.get(i) + "\n" +
                    "Expiry Date- " + list_expDate.get(i) + "\n" +
                    "Address- " + list_address.get(i) + "\n" +
                    "Ph No- " + list_phno.get(i);
            view_array[i].setText(Text);
            view_array[i].setClickable(true);
            view_array[i].setBackgroundResource(R.drawable.custom_input);
            view_array[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            view_array[i].setTextColor(Color.BLACK);
            linearLayout.addView(view_array[i]);
        }
    }
}