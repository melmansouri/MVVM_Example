package com.mel.mvvmrecyclerview.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.OwnViewHolder> {

    private List<Task> taskList;

    public TasksAdapter(List<Task> taskList){
        this.taskList=taskList;
    }

    @NonNull
    @Override
    public OwnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_list_task, parent, false);
        return new OwnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnViewHolder holder, int position) {
        Task task=taskList.get(position);
        holder.bindData(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public void onViewRecycled(@NonNull OwnViewHolder holder) {
        super.onViewRecycled(holder);
        //Release Resources
    }

    public static class OwnViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtItemTaskTitle)
        TextView txtItemTaskTitle;
        @BindView(R.id.txtItemTaskContent)
        TextView txtItemTaskContent;
        public OwnViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindData(Task task){
            txtItemTaskTitle.setText(task.getTitle());
            txtItemTaskContent.setText(task.getContent());
        }

        private void unBindData(){

        }
    }
}
