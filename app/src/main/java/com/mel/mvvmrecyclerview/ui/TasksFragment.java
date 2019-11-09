package com.mel.mvvmrecyclerview.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.model.Task;
import com.mel.mvvmrecyclerview.repository.TaskMapper;
import com.mel.mvvmrecyclerview.repository.TaskRepository;
import com.mel.mvvmrecyclerview.ui.adapter.TasksAdapter;
import com.mel.mvvmrecyclerview.vm.OwnViewModelFactory;
import com.mel.mvvmrecyclerview.vm.TaskViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;

public class TasksFragment extends Fragment {

    @BindView(R.id.RecyclerViewTask)
    RecyclerView recyclerViewTask;
    private TaskViewModel taskViewModel;
    private TasksAdapter adapter;
    private List<Task> taskList;
    private OwnViewModelFactory ownViewModelFactory;
    private TaskRepository taskRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_tasks, container);
        initRecyclerList();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskRepository=new TaskRepository(getContext(),new TaskMapper());
        ownViewModelFactory=new OwnViewModelFactory(taskRepository);
        taskViewModel= new ViewModelProvider(getActivity(),ownViewModelFactory).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> adapter.addListTasks(tasks));
    }

    private void initRecyclerList(){
        taskList=new ArrayList<>();
        generateDummyList();
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTask.setHasFixedSize(true);
        adapter=new TasksAdapter(taskList);
        recyclerViewTask.setAdapter(adapter);
    }

    private void generateDummyList(){
        List<Task> dummyList=new ArrayList<>();
        String[] titles={"The sky", "above", "the port","was", "the color of television", "tuned", "to", "a dead channel",  "All", "this happened", "more or less","I", "had", "the story", "bit by bit", "from various people", "and", "as generally", "happens", "in such cases", "each time", "it", "was", "a different story","It", "was", "a pleasure", "to", "burn"};
        String[] contents={"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."
                ,"Donec nec justo eget felis facilisis fermentum. Aliquam porttitor mauris sit amet orci. Aenean dignissim pellentesque felis"
                ,"Morbi in sem quis dui placerat ornare. Pellentesque odio nisi, euismod in, pharetra a, ultricies in, diam. Sed arcu. Cras consequat."
                ,"Praesent dapibus, neque id cursus faucibus, tortor neque egestas auguae, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus"
                ,"Phasellus ultrices nulla quis nibh. Quisque a lectus. Donec consectetuer ligula vulputate sem tristique cursus. Nam nulla quam, gravida non, commodo a, sodales sit amet, nisi"};
        Random random=new Random();
        int sizeTitles=titles.length;
        int sizeContents=contents.length;
        for(int i=0;i<100;i++){
            Task task=new Task();
            task.setTitle(titles[random.nextInt(sizeTitles)]);
            task.setContent(contents[random.nextInt(sizeContents)]);
            dummyList.add(task);
        }
    }

    private void insertDummyListLocal(){
        
    }
}
