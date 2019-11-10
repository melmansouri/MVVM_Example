package com.mel.mvvmrecyclerview.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.OwnViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public OwnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_list_user, parent, false);
        return new OwnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bindData(user);
    }

    /**
     * Used to getChangePayload and update the specific view
     * @param holder
     * @param position
     * @param payloads
     */
    @Override
    public void onBindViewHolder(@NonNull OwnViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addListTasks(List<User> newList) {
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new DiffUtilsCallbackUser(userList,newList));
        userList.clear();
        userList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class OwnViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtItemUserName)
        TextView txtItemTaskTitle;
        @BindView(R.id.txtItemUserDescription)
        TextView txtItemTaskContent;
        @BindView(R.id.imgCheck)
        ImageView imgCheck;

        public OwnViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    imgCheck.setVisibility(View.VISIBLE);
                    return true;
                }
            });
        }

        private void bindData(User user) {
            txtItemTaskTitle.setText(user.getName());
            txtItemTaskContent.setText(user.getDescription());
        }
    }
}
