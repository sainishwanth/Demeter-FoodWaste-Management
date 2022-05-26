package com.example.foodwaste.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodwaste.DBForm;
import com.example.foodwaste.DBHelper;
import com.example.foodwaste.R;
import com.example.foodwaste.databinding.FragmentHomeBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private View view;
    private AlertDialog.Builder DialogBuilder;
    private AlertDialog Dialog;
    private EditText itemName, bought_date, exp_date, phno, address;
    private MaterialButton submitbtn;
    private DBForm Db;
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

                    }
                });
                break;
            case "NGO":
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
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemName.getText().toString().equals("")||bought_date.getText().toString().equals("")||exp_date.getText().toString().equals("")||address.getText().toString().equals("")||phno.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(Db.insertData(itemName.getText().toString(),bought_date.getText().toString(),exp_date.getText().toString(),address.getText().toString(),phno.getText().toString())){
                        Toast.makeText(getActivity(), "Created Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        DialogBuilder.setView(contact);
        Dialog = DialogBuilder.create();
        Dialog.show();
    }
}
