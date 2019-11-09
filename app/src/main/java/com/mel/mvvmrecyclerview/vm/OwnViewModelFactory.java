package com.mel.mvvmrecyclerview.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.mel.mvvmrecyclerview.repository.TaskRepository;

public class OwnViewModelFactory implements ViewModelProvider.Factory {
    private final TaskRepository taskRepository;
    public OwnViewModelFactory(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)){
            return (T) new TaskViewModel(taskRepository);
        }
        throw new IllegalArgumentException("Unknown Viewmodel class");
    }
}
