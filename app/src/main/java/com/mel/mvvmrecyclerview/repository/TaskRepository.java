package com.mel.mvvmrecyclerview.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mel.mvvmrecyclerview.MyApp;
import com.mel.mvvmrecyclerview.datasource.local.TaskDAO;
import com.mel.mvvmrecyclerview.datasource.local.TaskDataBase;
import com.mel.mvvmrecyclerview.datasource.local.TaskEntity;
import com.mel.mvvmrecyclerview.model.Task;

import java.util.List;

public class TaskRepository {

    private TaskDAO taskDAO;
    private TaskMapper taskMapper;
    public TaskRepository(TaskMapper taskMapper){
        taskDAO= TaskDataBase.getDatabase(MyApp.getContext()).taskDAO();
        this.taskMapper=taskMapper;

    }

    public void insertTask(Task task){
        //Convert task to TaskEntity
        TaskEntity taskEntity=taskMapper.convertTaskToTAskEntity(task);
        taskDAO.insert(taskEntity);
    }

    public void deleteTask(Task task){

    }

    public List<Task> getAllTasks(){
        return taskMapper.convertTaskEntityToTask(taskDAO.getALlTasks());
    }


}
