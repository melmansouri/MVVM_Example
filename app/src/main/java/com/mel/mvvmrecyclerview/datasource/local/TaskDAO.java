package com.mel.mvvmrecyclerview.datasource.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    void insert(TaskEntity taskEntity);

    @Query("Select * from Task")
    List<TaskEntity> getALlTasks();

    @Delete
    void deleteTask(TaskEntity task);
}
