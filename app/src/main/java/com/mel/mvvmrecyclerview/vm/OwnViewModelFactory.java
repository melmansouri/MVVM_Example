package com.mel.mvvmrecyclerview.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mel.mvvmrecyclerview.repository.UserRepository;

public class OwnViewModelFactory implements ViewModelProvider.Factory {
    private final UserRepository userRepository;
    public OwnViewModelFactory(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)){
            return (T) new UserViewModel(userRepository);
        }
        throw new IllegalArgumentException("Unknown Viewmodel class");
    }
}
