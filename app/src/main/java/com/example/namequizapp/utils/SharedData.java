package com.example.namequizapp.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.namequizapp.models.Person;
import com.example.namequizapp.R;

import java.util.ArrayList;

public class SharedData extends Application {
    public ArrayList<Person> sharedData = new ArrayList<>();
    private Context context;
    private Bitmap bMap, bitmap;
    private Person kitty, dog;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat);
        bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vicious_dog_0);
        //kitty = new Person(bitmap, "Cat");
        //dog = new Person(bMap, "Dog");

        sharedData.add(kitty);
        sharedData.add(dog);
    }
}
