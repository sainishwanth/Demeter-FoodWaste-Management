package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton btn;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        listView = findViewById(R.id.list);
        btn = findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });
        itemList = new ArrayList<>();
        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(itemAdapter);

    }
    public void addItem(View view){
        EditText itemText = findViewById(R.id.itemText);
        String item = itemText.getText().toString();
        if(!item.equals("")){
            itemAdapter.add(item);
            itemText.setText("");
        }else{
            Toast.makeText(this, "Please Add Something", Toast.LENGTH_SHORT).show();
        }
    }
}