package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button quiz, addImage, showImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz = findViewById(R.id.quizButton);
        addImage = findViewById(R.id.addImage_button);
        showImages = findViewById(R.id.show_button);

        quiz.setOnClickListener(this);
        addImage.setOnClickListener(this);
        showImages.setOnClickListener(this);
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
        }
    }
}
