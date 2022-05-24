package com.example.foodwaste.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodwaste.R;
import com.example.foodwaste.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private View view2;
    private ListView listView;
    private FloatingActionButton btn;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> itemAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        view2 = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = view2.findViewById(R.id.list);
        btn = view2.findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });
        ArrayList<String> itemList = new ArrayList<>();
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(view2.getContext(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(itemAdapter);
        setUpListViewListener();
        return root;
    }
    private void setUpListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view2.getContext(), "Item has been Marked", Toast.LENGTH_SHORT).show();
                itemList.remove(i);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    public void addItem(View view){
        EditText itemText = view2.findViewById(R.id.itemText);
        String item = itemText.getText().toString();
        if(!item.equals("")){
            itemAdapter.add(item);
            itemText.setText("");
        }else{
            Toast.makeText(view2.getContext(), "Please Add Something", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}