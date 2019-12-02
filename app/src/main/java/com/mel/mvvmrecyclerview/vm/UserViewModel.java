package com.mel.mvvmrecyclerview.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mel.mvvmrecyclerview.common.SingleLiveEvent;
import com.mel.mvvmrecyclerview.model.User;
import com.mel.mvvmrecyclerview.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel{
    private LiveData<List<User>> liveListUsers;
    private UserRepository userRepository;
    private SingleLiveEvent<String> singleLiveEvent;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        liveListUsers =new MutableLiveData<>();
        singleLiveEvent=new SingleLiveEvent<>();
        liveListUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return liveListUsers;
    }

    public void insertListUsers(List<User> userList){
        userRepository.insertListUsers(userList);
    }

    public void deleteListUsers(List<User> listUsers){
        userRepository.deleteListUsers(listUsers);
    }
    public void insertUser(User user) {
        if ((user.getName()==null || user.getName().equals("")) || (user.getDescription()==null || user.getDescription().equals(""))){
            singleLiveEvent.setValue("Los campos no pueden estar vacios");
            return;
        }
        userRepository.insertUser(user);
    }

    public SingleLiveEvent<String> getCheckDataSingleEvemt() {
        return singleLiveEvent;
    }
}
