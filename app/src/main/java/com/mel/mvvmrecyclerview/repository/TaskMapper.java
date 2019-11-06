package com.mel.mvvmrecyclerview.repository;

import com.mel.mvvmrecyclerview.datasource.local.TaskEntity;
import com.mel.mvvmrecyclerview.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {
    public TaskEntity convertTaskToTAskEntity(Task task) {
        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setTitle(task.getTitle());
        taskEntity.setContent(task.getContent());
        return taskEntity;
    }

    public List<Task> convertTaskEntityToTask(List<TaskEntity> aLlTasks) {
        List<Task> tasks=new ArrayList<>();
        for (TaskEntity taskEntity:aLlTasks){
            Task task=new Task();
            task.setContent(taskEntity.getContent());
            task.setTitle(taskEntity.getTitle());
            tasks.add(task);
        }
        return tasks;
    }
}
