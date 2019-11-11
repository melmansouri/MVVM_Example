package com.mel.mvvmrecyclerview.ui.adapter;

import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

import butterknife.Action;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.OwnViewHolder> {
    private List<User> userList;
    private static ActionModeCallback actionMode;
    private

    public UserAdapter(List<User> userList) {
        this.userList = userList;
        actionMode=new ActionModeCallback();
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
        private User user;

        public OwnViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.startActionMode(actionMode);
                    if (user.isChecked()){
                        user.setChecked(false);
                        imgCheck.setImageResource(R.drawable.ic_img_user_default);
                    }else{
                        imgCheck.setImageResource(R.drawable.ic_check_circle);
                        user.setChecked(true);
                    }
                    return true;
                }
            });
        }

        private void bindData(User user) {
            this.user=user;
            txtItemTaskTitle.setText(user.getName());
            txtItemTaskContent.setText(user.getDescription());
        }
    }

    private static class ActionModeCallback implements android.view.ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_contextual_action_mode,menu);
            Log.d("asda","asdfasfd");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.itemMenuDelete){
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mode=null;
        }
    }
}
