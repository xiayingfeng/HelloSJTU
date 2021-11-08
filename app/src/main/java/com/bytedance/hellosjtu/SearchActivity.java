package com.bytedance.hellosjtu;
/**
 * @author Orange
 * @date 2021/11/1 22:10
 */

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter = new SearchAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchAdapter);
        List<String> allItems = notifyChanged();

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.search_view, new Fragment()).commitAllowingStateLoss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> subItems = new ArrayList<>();
                for (String str : allItems) {
                    if (str.contains(newText)){
                        subItems.add(str);
                    }
                }
                mSearchAdapter.notifyItems(subItems);
                return false;
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    private List<String> notifyChanged (){
        String extra = getIntent().getStringExtra("extra");
        List<String> items = new ArrayList<>();
        String[] template = {"这是第","行"};
        for (int i = 0; i < 100; i++) {
            items.add(template[0] + String.valueOf(i) + template[1]);
        }

        mSearchAdapter.notifyItems(items);
        return items;

    }
}