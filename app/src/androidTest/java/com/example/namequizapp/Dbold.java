package com.example.namequizapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import com.example.namequizapp.activities.MainActivity;
import com.example.namequizapp.database.QuizRoomDatabase;
import com.example.namequizapp.interfaces.PersonDao;
import com.example.namequizapp.models.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class Dbold {
    private QuizRoomDatabase db;
    private PersonDao personDao;
    private Context ctx;


    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

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


    @Test
    public void testAddAndRemove(){
        Resources res = ctx.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.vicious_dog_0);
        byte[] bytearr = convertToByteArray(bitmap);
        Person person = new Person(0, bytearr, "test");
        int sizeBefore = personDao.getAllPersons().size();
        personDao.insertPerson(person);
        int sizeAfter = personDao.getAllPersons().size();
        assert(sizeBefore + 1 == sizeAfter);
        personDao.deletePerson(person);
        sizeAfter = personDao.getAllPersons().size();
        assert (sizeAfter == 0);
    }

    /**
     * is the score updated correctly
     * (submit right/wrong answer and check if the score is correct afterwards)
     */

    @Test
    public void testScore () {
        //Adding to database
        Resources res = ctx.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.vicious_dog_0);
        byte[] bytearr = convertToByteArray(bitmap);
        Person person = new Person(0, bytearr, "test");
        Person person1 = new Person(0, bytearr, "test");
        personDao.insertPerson(person1);
        personDao.insertPerson(person);

        ArrayList<Person> persons = (ArrayList<Person>) personDao.getAllPersons();
        int score = 0;

        String try1 = "test";

        for(Person p : persons) {
            if(p.getName().equals(try1)){
                score++;
            }
        }
        assert (score == 1);
    }
/*
    @Test
    public void scoreTest() {
        onView(withId(R.id.quizButton)).perform(click());
        QuizActivity quizActivity = (QuizActivity) getActivityInstance();

        onView(withId(R.id.editText)).perform(typeText("test"));
        onView(withId(R.id.submit_button)).perform(click());
        int score = quizActivity.score;

        assert (score == 1);
    }
*/
    private Activity getActivityInstance() {
        final Activity[] currentActivity = {null};

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                Iterator<Activity> it = resumedActivity.iterator();
                currentActivity[0] = it.next();
            }
        });

        return currentActivity[0];
    }

    public byte[] convertToByteArray(Bitmap bitmap) {

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
        byte[] bitmapdata = blob.toByteArray();

        return bitmapdata;
    }
}
