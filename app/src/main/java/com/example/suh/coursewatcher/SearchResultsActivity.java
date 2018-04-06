package com.example.suh.coursewatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h1>class SearchResultsActivity</h1>
 * <p>This activity handles the logic associated with the search result interface</p>
 */
public class SearchResultsActivity extends AppCompatActivity{

    public static final String TAG = "SearchResultsActivity";

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        final ListView lv = findViewById(R.id.search_list);
        adapter = new ArrayAdapter<String>(
                SearchResultsActivity.this,
                android.R.layout.simple_list_item_1);

        lv.setAdapter(adapter);
        adapter.addAll(Arrays.asList(getResources().getStringArray(R.array.array_courses)));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String courseCode = lv.getItemAtPosition(position).toString().trim();

                Intent intent = new Intent(SearchResultsActivity.this, SectionListActivity.class);
                String semester = "2018;SP";
                intent.putExtra("courseCode", courseCode);
                intent.putExtra("semester", semester);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
