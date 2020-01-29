package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ShowAllActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lst;
    Button addEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all);
        SharedData app = (SharedData) getApplication();

        addEntry = findViewById(R.id.addImage_button);
        addEntry.setOnClickListener(this);

        lst=findViewById(R.id.listView);
        CustomListView customListView = new CustomListView(this, app.sharedData);
        lst.setAdapter(customListView);
    }

    @Override
    public void onClick(View v) {
        Intent intent_add = new Intent(ShowAllActivity.this, AddImageActivity.class);
        startActivity(intent_add);
    }
}
