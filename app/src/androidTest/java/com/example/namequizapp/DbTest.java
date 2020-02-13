package com.example.namequizapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.namequizapp.database.QuizRoomDatabase;
import com.example.namequizapp.interfaces.PersonDao;
import com.example.namequizapp.models.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
@RunWith(AndroidJUnit4.class)
public class DbTest {
    private QuizRoomDatabase db;
    private PersonDao personDao;
    private Context ctx;


    @Before
    public void setUp(){
        ctx = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(ctx, QuizRoomDatabase.class).build();
        personDao = db.personDAO();
    }

    @After
    public void tearDown(){
        db.close();
    }

    @Test
    public void testAdd(){
        Resources res = ctx.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.vicious_dog_0);
        byte[] bytearr = convertToByteArray(bitmap);
        Person person = new Person(0, bytearr, "test");
        int sizeBefore = personDao.getAllPersons().size();
        personDao.insertPerson(person);
        int sizeAfter = personDao.getAllPersons().size();
        assert(sizeBefore + 1 == sizeAfter);
    }

    public byte[] convertToByteArray(Bitmap bitmap) {

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
        byte[] bitmapdata = blob.toByteArray();

        return bitmapdata;
    }
}
