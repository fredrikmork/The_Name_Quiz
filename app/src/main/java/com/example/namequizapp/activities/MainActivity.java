package com.example.namequizapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.namequizapp.database.QuizRoomDatabase;
import com.example.namequizapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button quiz, addImage, showImages, addUsername;
    private ImageButton settings;
    private String owner = "";
    private EditText usernameEditText;
    private TextView updateUsernameText;
    private Boolean status = false;
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private static final String SHARED_PREF_NAME = "";
    private static final String KEY_NAME = "key";

    public static QuizRoomDatabase quizRoomDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizRoomDatabase = Room.databaseBuilder(getApplicationContext(), QuizRoomDatabase.class, "persondb").allowMainThreadQueries().build();

        quiz = findViewById(R.id.quizButton);
        addImage = findViewById(R.id.addImage_button);
        showImages = findViewById(R.id.show_button);
        updateUsernameText = findViewById(R.id.add_Username_TextView);
        usernameEditText = findViewById(R.id.username_EditText);
        addUsername = findViewById(R.id.update_username_button);
        settings = findViewById(R.id.settingsButton);

        quiz.setOnClickListener(this);
        addImage.setOnClickListener(this);
        showImages.setOnClickListener(this);
        settings.setOnClickListener(this);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        spe = sp.edit();

        displayUsername();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUsername();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_button:
                Intent intent_show = new Intent(MainActivity.this, ShowAllActivity.class);
                startActivity(intent_show);
                break;
            case R.id.addImage_button:
                Intent intent_add = new Intent(MainActivity.this, AddImageActivity.class);
                startActivity(intent_add);
                break;
            case R.id.quizButton:
                Intent intent_quiz = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent_quiz);
                break;
            case R.id.update_username_button:
                saveUsername();
                break;
            case R.id.settingsButton:
                Intent intent_settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent_settings);
                break;
        }
    }

    public void displayUsername() {
        String name = sp.getString(KEY_NAME, null);
        Log.i("MainActivity", "onCreate: " + name);
        if (name != null) {
            updateUsernameText.setText("Welcome " + name);
            usernameEditText.setVisibility(View.GONE);
            addUsername.setVisibility(View.GONE);
        }

    }

    private void saveUsername() {
        String username = usernameEditText.getText().toString().trim();
        owner = username;
        Log.i("Username", ": " + owner);
        usernameEditText.setText("");

        //save the name
        spe.putString(KEY_NAME, username);
        spe.commit();

        //Update the welcome text
        displayUsername();
    }

}
