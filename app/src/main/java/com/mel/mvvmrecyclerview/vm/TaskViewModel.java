package com.mel.mvvmrecyclerview.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mel.mvvmrecyclerview.model.Task;
import com.mel.mvvmrecyclerview.repository.TaskMapper;
import com.mel.mvvmrecyclerview.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends ViewModel{
    private LiveData<List<Task>> liveListTasks;
    private TaskRepository taskRepository;

    public TaskViewModel(TaskRepository taskRepository) {
        this.taskRepository=taskRepository;
        liveListTasks=new MutableLiveData<>();
        liveListTasks=taskRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return liveListTasks;
    }

    public void insertListTasks(List<Task> taskList){
        taskRepository.insertListTasks(taskList);
    }
}
