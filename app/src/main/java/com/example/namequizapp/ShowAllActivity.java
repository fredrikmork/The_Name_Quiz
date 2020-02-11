package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAllActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lst;
    private ImageButton addEntry;
    ArrayList<Person> liste = (ArrayList<Person>) MainActivity.quizRoomDatabase.personDAO().getAllPersons();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all);
        SharedData app = (SharedData) getApplication();

        addEntry = findViewById(R.id.addImage_button);
        addEntry.setOnClickListener(this);

        lst=findViewById(R.id.listView);


        CustomListView customListView = new CustomListView(this, liste);
        lst.setAdapter(customListView);
    }

    @Override
    public void onClick(View v) {
        Intent intent_add = new Intent(ShowAllActivity.this, AddImageActivity.class);
        startActivity(intent_add);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedData app = (SharedData) getApplication();
        CustomListView customListView = new CustomListView(this, liste);
        lst.setAdapter(customListView);
    }
}
