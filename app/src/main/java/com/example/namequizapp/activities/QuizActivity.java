package com.example.namequizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namequizapp.interfaces.PersonDao;
import com.example.namequizapp.models.Person;
import com.example.namequizapp.R;
import com.example.namequizapp.utils.SharedData;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText quizName;
    private Button submitButton;
    private ImageView quizImage;
    private TextView scoreText, feedBackText;
    private SharedData app;
    private Random random = new Random();
    private int score, questions, arraySize, randomInteger;
    final PersonDao personDao = MainActivity.quizRoomDatabase.personDAO();
    final ArrayList<Person> persons = (ArrayList<Person>) personDao.loadAllPersons();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (SharedData) getApplication();
        arraySize = persons.size();

        if (arraySize < 1) {
            Toast.makeText(this, "No questions in the database. Create a new entry", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, AddImageActivity.class);
            startActivity(intent);
            // setContentView(R.layout.activity_main); // Redirect
        } else {
            setContentView(R.layout.activity_quiz);

            randomInteger = random.nextInt(arraySize);
            submitButton = findViewById(R.id.submit_button);
            quizImage = findViewById(R.id.quizImage);
            quizName = findViewById(R.id.editText);
            scoreText = findViewById(R.id.scoreText);
            feedBackText = findViewById(R.id.feedBackText);

            submitButton.setOnClickListener(this);

            // Collects the imageView from the "database"-images
            Bitmap bitmap = convertToBitmap(persons.get(randomInteger).getImage());
            quizImage.setImageBitmap(bitmap);
        }

    }

    public Bitmap convertToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    @Override
    public void onClick(View v) {
        questions += 1;
        String correctName = persons.get(randomInteger).getName(); // The correct name for the picture.
        Log.i("QuizName: ", quizName.getText().toString());
        Log.i("CorrectName: ", correctName);

        if (quizName.getText().toString().toUpperCase().equals(correctName.toUpperCase())) { // Correct answer
            score += 1;
            feedBackText.setText("Correct answer!");
        } else {
            feedBackText.setText("The correct answer is " + correctName); // Correct answer
        }

        quizName.setText(""); // Resets the edit-text
        randomInteger = random.nextInt(arraySize); // New random index
        //quizImage.setImageBitmap(questionList.get(randomInteger).getBitmap()); // Collecting a new image
        scoreText.setText("Score: " + score + "/" + questions); // Updating the score list


    }
}
