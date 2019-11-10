package com.mel.mvvmrecyclerview.datasource.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class},version = 1)
public abstract class UserDataBase extends RoomDatabase {
    public abstract UserDAO taskDAO();
    private static volatile UserDataBase instance;
    public static UserDataBase getDatabase(Context context){
        if (instance==null){
            synchronized (UserDataBase.class){
                if (instance==null){
                    instance= Room.databaseBuilder(context, UserDataBase.class,"TaskDB").build();
                }
            }
        }
        return instance;
    }
}
