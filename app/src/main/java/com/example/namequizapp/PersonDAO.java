package com.example.namequizapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Query("SELECT * FROM persons WHERE nickname = :name")
    List<Person> findProduct(String name);

    @Query("DELETE FROM persons WHERE id = :id")
    void deleteProduct(String id);

    @Query("SELECT * FROM persons")
    LiveData<List<Person>> getAllProducts();
}
