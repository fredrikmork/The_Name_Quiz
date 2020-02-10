package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    private Button btn;
    private static final String KEY_NAME = "key";
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tv = findViewById(R.id.changeUsernameTextView);
        et = findViewById(R.id.changeUsernameEditText);
        btn = findViewById(R.id.changeUsernameBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_name();
            }
        });
    }

    private void save_name() {

        if(et.getText().toString().isEmpty()) {
            et.setError("The name cannot be empty!");
        } else {
            spe = PreferenceManager.getDefaultSharedPreferences(this).edit();
            String updatedName = et.getText().toString().trim();
            spe.putString(KEY_NAME, updatedName);
            spe.commit();
            finish();
        }
    }
}
