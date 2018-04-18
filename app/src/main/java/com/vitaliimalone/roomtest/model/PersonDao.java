package com.vitaliimalone.roomtest.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPeron(Person person);

    @Query("DELETE FROM person")
    void deleteAll();

    @Query("SELECT * FROM person")
    List<Person> getAllPeople();
}
