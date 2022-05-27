package com.example.foodwaste;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class don_itemList extends AppCompatActivity {
    private DBForm Db;
    private List<String> list_item;
    private List<String> list_purchaseDate;
    private List<String> list_expDate;
    private List<String> list_address;
    private List<String> list_phno;
    private List<String> list_uid;
    private AlertDialog.Builder DialogBuilder;
    private AlertDialog Dialog;
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
        list_uid = new ArrayList<String>();
        list_uid = Db.getUID();
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
            view_array[i].setBackgroundResource(R.drawable.custom_input2);
            view_array[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            view_array[i].setTextColor(Color.BLACK);
            int temp = i;
            view_array[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogBuilder = new AlertDialog.Builder(don_itemList.this);
                    final View contact = getLayoutInflater().inflate(R.layout.popup2, null);
                    TextView itemName2 = contact.findViewById(R.id.form_itemname);
                    TextView bought_date2 = contact.findViewById(R.id.form_purchased_date);
                    TextView exp_date2 = contact.findViewById(R.id.form_exp_date);
                    TextView phno2 = contact.findViewById(R.id.form_number);
                    TextView address2 = contact.findViewById(R.id.form_Address);
                    MaterialButton submitbtn = contact.findViewById(R.id.form_submit);
                    TextView unique_id = contact.findViewById(R.id.UID);
                    unique_id.setText("ID#- " + list_uid.get(temp));
                    phno2.setText(list_phno.get(temp));
                    address2.setText(list_address.get(temp));
                    itemName2.setText(list_item.get(temp));
                    exp_date2.setText(list_expDate.get(temp));
                    bought_date2.setText(list_purchaseDate.get(temp));
                    DialogBuilder.setView(contact);
                    Dialog = DialogBuilder.create();
                    Dialog.show();
                    submitbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog.hide();
                        }
                    });
                }
            });
            linearLayout.addView(view_array[i]);
        }
    }
}