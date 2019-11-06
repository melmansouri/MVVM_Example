package com.mel.mvvmrecyclerview.datasource.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskEntity.class},version = 1)
public abstract class TaskDataBase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
    private static volatile TaskDataBase instance;
    public static TaskDataBase getDatabase(Context context){
        if (instance==null){
            synchronized (TaskDataBase.class){
                if (instance==null){
                    instance= Room.databaseBuilder(context,TaskDataBase.class,"TaskDB").build();
                }
            }
        }
        return instance;
    }
}
