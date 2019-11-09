package com.mel.mvvmrecyclerview.repository;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mel.mvvmrecyclerview.MyApp;
import com.mel.mvvmrecyclerview.datasource.local.TaskDAO;
import com.mel.mvvmrecyclerview.datasource.local.TaskDataBase;
import com.mel.mvvmrecyclerview.datasource.local.TaskEntity;
import com.mel.mvvmrecyclerview.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private TaskDAO taskDAO;
    private TaskMapper taskMapper;
    private MutableLiveData<List<Task>> listMutableLiveData;
    public TaskRepository(Context context, TaskMapper taskMapper){
        taskDAO= TaskDataBase.getDatabase(context).taskDAO();
        this.taskMapper=taskMapper;
        listMutableLiveData=new MutableLiveData<>();
    }

    public void insertTask(Task task){
        List<Task> tasks=new ArrayList<>();
        tasks.add(task);
        insertListTasks(tasks);
    }

    public void insertListTasks(List<Task> taskList){
        new InsertListAsyncTask(taskDAO,taskMapper).execute(taskList);
    }

    public void deleteTask(Task task){
    }

    public MutableLiveData<List<Task>> getAllTasks(){
        listMutableLiveData.setValue(taskMapper.convertTaskEntityToTask(taskDAO.getALlTasks()));
        return listMutableLiveData;
    }

    private static class InsertListAsyncTask extends AsyncTask<List<Task>,Void,Void>{
        private TaskDAO taskDAO;
        private TaskMapper taskMapper;
        public InsertListAsyncTask(TaskDAO taskDAO,TaskMapper taskMapper){
            this.taskDAO=taskDAO;
            this.taskMapper=taskMapper;
        }
        @Override
        protected Void doInBackground(List<Task>... lists) {
            List<Task> taskList=lists[0];
            for (Task task:taskList){
                taskDAO.insert(taskMapper.convertTaskToTAskEntity(task));
            }
            return null;
        }
    }
}
