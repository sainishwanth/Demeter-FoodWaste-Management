package com.example.foodwaste.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Dictionary;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodwaste.DBForm;
import com.example.foodwaste.DBHelper;
import com.example.foodwaste.R;
import com.example.foodwaste.databinding.FragmentHomeBinding;
import com.example.foodwaste.ngo_home;
import com.google.android.material.button.MaterialButton;
import android.view.ViewGroup;
import android.content.ClipboardManager;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private View view;
    private AlertDialog.Builder DialogBuilder;
    private AlertDialog Dialog;
    private EditText itemName, bought_date, exp_date, phno, address;
    private MaterialButton submitbtn;
    private DBForm Db;
    private List<String> list_item;
    private List<String> list_purchaseDate;
    private List<String> list_expDate;
    private List<String> list_address;
    private List<String> list_phno;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        if(DBHelper.checktype.equals("Donator")) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }else if(DBHelper.checktype.equals("NGO")){
            view = inflater.inflate(R.layout.fragment_home_ngo, container, false);
        }
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setupViews(view);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void setupViews(View v) {
        switch (DBHelper.checktype) {
            case "Donator":
                MaterialButton new_item = v.findViewById(R.id.don_home_newitem);
                MaterialButton existing_item = v.findViewById(R.id.don_home_existingitem);

                new_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    createNewContactDialog();
                    }
                });
                existing_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ngo_home.class);
                        startActivity(intent);
                    }
                });
                break;
            case "NGO":
                ScrollView home_ngo = v.findViewById(R.id.nav_home);
                LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                home_ngo.setLayoutParams(scrollParams);
                LinearLayout linearLayout = new LinearLayout(getActivity());
                LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(linearParams);
                home_ngo.addView(linearLayout);
                Db = new DBForm(getActivity());
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
                for(int i = 0; i < count; ++i){
                    view_array[i] = new TextView(getActivity());
                }
                for(int i = 0; i < count; ++i){
                    TextView text_view = new TextView(getActivity());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10,20,1,50);
                    view_array[i].setLayoutParams(params);
                    String Text = "Item- " + list_item.get(i) + "\n" +
                            "Purchase Date- " + list_purchaseDate.get(i) + "\n"+
                            "Expiry Date- " + list_expDate.get(i) + "\n"+
                            "Address- " + list_address.get(i) + "\n" +
                            "Ph No- " + list_phno.get(i);
                    view_array[i].setText(Text);
                    view_array[i].setClickable(true);
                    view_array[i].setBackgroundResource(R.drawable.custom_input2);
                    view_array[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    view_array[i].setTextColor(Color.BLACK);
                    view_array[i].setId(i);
                    int temp = i;
                    view_array[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_SHORT).show();
                            DialogBuilder = new AlertDialog.Builder(getActivity());
                            final View contact = getLayoutInflater().inflate(R.layout.popup2, null);
                            TextView itemName2 = contact.findViewById(R.id.form_itemname);
                            TextView bought_date2 = contact.findViewById(R.id.form_purchased_date);
                            TextView exp_date2 = contact.findViewById(R.id.form_exp_date);
                            TextView phno2 = contact.findViewById(R.id.form_number);
                            TextView address2 = contact.findViewById(R.id.form_Address);
                            submitbtn = contact.findViewById(R.id.form_submit);
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
                break;
        }
    }
    public void createNewContactDialog(){
        DialogBuilder = new AlertDialog.Builder(getActivity());
        final View contact = getLayoutInflater().inflate(R.layout.popup, null);
        itemName = contact.findViewById(R.id.form_itemname);
        bought_date = contact.findViewById(R.id.form_purchased_date);
        exp_date = contact.findViewById(R.id.form_exp_date);
        phno = contact.findViewById(R.id.form_number);
        address = contact.findViewById(R.id.form_Address);
        submitbtn = contact.findViewById(R.id.form_submit);
        phno.setText(DBHelper.checkphno);
        address.setText(DBHelper.checkaddress);
        Db = new DBForm(getActivity());
        DialogBuilder.setView(contact);
        Dialog = DialogBuilder.create();
        Dialog.show();
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemName.getText().toString().equals("")||bought_date.getText().toString().equals("")||exp_date.getText().toString().equals("")||address.getText().toString().equals("")||phno.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(Db.insertData(itemName.getText().toString(),bought_date.getText().toString(),exp_date.getText().toString(),address.getText().toString(),phno.getText().toString())){
                        Toast.makeText(getActivity(), "Created Successfully", Toast.LENGTH_SHORT).show();
                        Dialog.hide();
                    }else{
                        Toast.makeText(getActivity(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
