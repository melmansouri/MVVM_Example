package com.mel.mvvmrecyclerview.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.model.Task;
import com.mel.mvvmrecyclerview.ui.adapter.TasksAdapter;
import com.mel.mvvmrecyclerview.vm.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TasksFragment extends Fragment {

    @BindView(R.id.RecyclerViewTask)
    RecyclerView recyclerViewTask;
    private TaskViewModel taskViewModel;
    private TasksAdapter adapter;
    private List<Task> taskList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskViewModel= ViewModelProviders.of(this).get(TaskViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_tasks, container);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerList();
    }

    private void initRecyclerList(){
        taskList=new ArrayList<>();
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
