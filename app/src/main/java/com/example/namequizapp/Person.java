package com.example.namequizapp;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "persons")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    //@NonNull
    // @ColumnInfo(name = "image")
    //private Byte[] photo;

    @NonNull
    @ColumnInfo(name = "nickname")
    private String name;

    public Person(int id, String name) {
        this.id = id;
        //this.photo = photo;
        this.name = name;
    }

    public int getId() {
        return id;
    }

   /* @NonNull
    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(@NonNull Byte[] photo) {
        this.photo = photo;
    }
*/
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
