package com.mel.mvvmrecyclerview.repository;

import com.mel.mvvmrecyclerview.datasource.local.UserEntity;
import com.mel.mvvmrecyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public UserEntity convertTaskToTAskEntity(User user) {
        UserEntity userEntity =new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setDescription(user.getDescription());
        return userEntity;
    }

    public List<User> convertTaskEntityListToTaskList(List<UserEntity> aLlTasks) {
        List<User> users =new ArrayList<>();
        for (UserEntity userEntity :aLlTasks){
            User user =new User();
            user.setId(userEntity.getId());
            user.setDescription(userEntity.getDescription());
            user.setName(userEntity.getName());
            users.add(user);
        }
        return users;
    }
    public List<UserEntity> convertTaskListToTaskEntityList(List<User> aLlUsers) {
        List<UserEntity> tasks=new ArrayList<>();
        for (User user : aLlUsers){
            UserEntity userEntity =new UserEntity();
            userEntity.setDescription(user.getDescription());
            userEntity.setName(user.getName());
            tasks.add(userEntity);
        }
        return tasks;
    }

}
