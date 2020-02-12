package com.example.namequizapp.database;

import android.content.Context;

import com.example.namequizapp.interfaces.PersonDao;
import com.example.namequizapp.models.Person;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Person.class, version = 1, exportSchema = false)
public abstract class QuizRoomDatabase extends RoomDatabase {

    public abstract PersonDao personDAO();

    private static volatile QuizRoomDatabase personRoomInstance;

    static QuizRoomDatabase getDatabase (final Context context) {
        if (personRoomInstance == null) {
            synchronized (QuizRoomDatabase.class) {
                if (personRoomInstance == null) {
                    personRoomInstance = Room.databaseBuilder(context.getApplicationContext(), QuizRoomDatabase.class, "person_database").build();
                }
            }
        }
        return personRoomInstance;
    }

}
