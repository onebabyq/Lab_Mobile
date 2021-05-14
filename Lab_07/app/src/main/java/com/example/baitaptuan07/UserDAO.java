package com.example.baitaptuan07;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDAO {
    @Query("Select * from users WHERE id = :userId")
    Flowable<User> getUserByUserId(int userId);

    @Query("SELECT * FROM users")
    public List<User> findAllUser();

    @Insert
    void insertUser(User users);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM users where name like :mName")
    void deleteUserByName(String mName);
}

