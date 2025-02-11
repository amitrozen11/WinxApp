package com.example.winx_recycleview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> arr;
    private RecyclerView recyclerView;
    private CustomeAdapter customeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvcon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arr = new ArrayList<>();

        for (int i = 0; i < MyData.nameArray.length; i++) {
            arr.add(new Data(
                    MyData.nameArray[i],
                    MyData.descriptionArray[i],
                    MyData.drawableArray[i],
                    MyData.id_[i]
            ));
        }

        customeAdapter = new CustomeAdapter(arr);
        recyclerView.setAdapter(customeAdapter);

        // חיבור לחיפוש
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // לא נדרש כאן
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customeAdapter.filterList(newText);
                return false;
            }
        });
    }
}
