package com.example.baitaptuan07;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlaceDAO {

    @Query("SELECT * FROM places where id = :id")
    public Place findPlaceById(int id);

    @Query("SELECT * FROM places")
    public List<Place> findAllPlace();
    @Insert
    void insertPlace(Place place);

    @Delete
    void deletePalce(Place place);

    @Query("DELETE  FROM places where id = :id")
    void  deletePlaceById(int id);

    @Update
    void updatePlace(Place place);
}
