package com.example.namequizapp.utils;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.namequizapp.models.Person;
import com.example.namequizapp.interfaces.PersonDao;
import com.example.namequizapp.database.QuizRoomDatabase;

import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private PersonDao personDAO;
    QuizRoomDatabase personDB;

    public MainViewModel (Application application) {
        super(application);

        personDAO = personDB.personDAO();
       // personDB = QuizRoomDatabase.getDatabase(application);

    }

    public void insertPerson(Person person) {

        new InsertAsyncTask(personDAO) {
            @Override
            protected Void doInBackground(Person... people) {
                return null;
            }
        }.execute(person);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "Viewmodel destroyed");
    }

    private abstract class InsertAsyncTask extends AsyncTask<Person, Void, Void> {
        PersonDao personDAO;

        public InsertAsyncTask(PersonDao personDAO) {
            this.personDAO = personDAO;
        }
    }
}
