package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
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
        setUpListViewListener();

    }
    private void setUpListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Item has been Marked", Toast.LENGTH_SHORT).show();
                itemList.remove(i);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
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