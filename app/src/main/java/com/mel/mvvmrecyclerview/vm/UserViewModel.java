package com.mel.mvvmrecyclerview.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mel.mvvmrecyclerview.model.User;
import com.mel.mvvmrecyclerview.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel{
    private LiveData<List<User>> liveListUsers;
    private UserRepository userRepository;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        liveListUsers =new MutableLiveData<>();
        liveListUsers = userRepository.getAllTasks();
    }

    public LiveData<List<User>> getAllTasks(){
        return liveListUsers;
    }

    public void insertListTasks(List<User> userList){
        userRepository.insertListTasks(userList);
    }
}
