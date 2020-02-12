package com.example.namequizapp.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Iterator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


import com.example.namequizapp.R;

import androidx.test.filters.LargeTest;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void stubCameraIntent() {
        Intents.init();

        Instrumentation.ActivityResult result = createImageCaptureActivityResultStub();
        Intents.intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
    }


    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {
        // Put the drawable in a bundle.
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(mActivityTestRule.getActivity().getResources(), R.drawable.cat));
        // Create the Intent that will include the bundle.
        Intent resultData = new Intent();
        resultData.putExtras(bundle);
        // Create the ActivityResult with the Intent.
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    @org.junit.Test
    public void test() {

        getActivityInstance();

        onView(withId(R.id.addImage_button)).perform(click());
        onView(withId(R.id.camera_button)).perform(click());
        onView(withId(R.id.name_edit)).perform(typeText("test"));

        onView(withId(R.id.add_PersonName)).perform(click());

        int dbSize = MainActivity.quizRoomDatabase.personDAO().getAllPersons().size();
        Truth.assertThat(dbSize).isEqualTo(1);
    }


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
}