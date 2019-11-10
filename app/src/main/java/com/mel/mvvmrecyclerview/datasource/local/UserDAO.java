package com.mel.mvvmrecyclerview.datasource.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    long insert(UserEntity userEntity);

    @Insert
    List<Long> insertAll(List<UserEntity> userEntityList);

    @Query("Select * from User")
    List<UserEntity> getALlTasks();

    @Delete
    void deleteTask(UserEntity task);
}
