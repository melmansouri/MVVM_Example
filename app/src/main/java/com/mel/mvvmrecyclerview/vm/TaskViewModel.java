package com.mel.mvvmrecyclerview.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mel.mvvmrecyclerview.model.Task;
import com.mel.mvvmrecyclerview.repository.TaskMapper;
import com.mel.mvvmrecyclerview.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends ViewModel {
    private MutableLiveData<List<Task>> listTasks;
    private TaskRepository taskRepository;
    public TaskViewModel(){
        taskRepository=new TaskRepository(new TaskMapper());
        listTasks=new MutableLiveData<>();
    }

    public LiveData<List<Task>> getAllTasks(){
        listTasks.postValue(taskRepository.getAllTasks());
        return listTasks;
    }
}
