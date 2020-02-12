package com.example.namequizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.namequizapp.adapters.CustomListView;
import com.example.namequizapp.models.Person;
import com.example.namequizapp.R;
import com.example.namequizapp.utils.SharedData;

import java.util.ArrayList;

public class ShowAllActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lst;
    private ImageButton addEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all);
        SharedData app = (SharedData) getApplication();

        addEntry = findViewById(R.id.addImage_button);
        addEntry.setOnClickListener(this);

        lst=findViewById(R.id.listView);

        ArrayList<Person> liste = (ArrayList<Person>) MainActivity.quizRoomDatabase.personDAO().getAllPersons();
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
        //SharedData app = (SharedData) getApplication();
        ArrayList<Person> liste = (ArrayList<Person>) MainActivity.quizRoomDatabase.personDAO().getAllPersons();
        CustomListView customListView = new CustomListView(this, liste);
        customListView.notifyDataSetChanged();
        lst.setAdapter(customListView);
    }
}
