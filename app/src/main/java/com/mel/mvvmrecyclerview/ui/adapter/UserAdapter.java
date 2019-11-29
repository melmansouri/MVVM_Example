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
import com.mel.mvvmrecyclerview.vm.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.OwnViewHolder> {
    private List<User> userList;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    private List<User> listUsersSelected;
    private boolean selectionEnabled;
    private UserViewModel userViewModel;

    public UserAdapter(List<User> userList, UserViewModel viewModel) {
        this.userList = userList;
        listUsersSelected=new ArrayList<>();
        actionModeCallback=new ActionModeCallback();
        userViewModel=viewModel;
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
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode==null){
                    actionMode=v.startActionMode(actionModeCallback);
                }
                selectionEnabled=true;
                manageSelection(holder,user);
                return true;
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectionEnabled){
                    manageSelection(holder,user);
                }
            }
        });
        //No quiero voy a comprobar si esta seleccionado o no.
        /*if (!user.isChecked()){
            holder.imgCheck.setImageResource(R.drawable.ic_check_circle);
        }*/
    }

    private void manageSelection(OwnViewHolder holder,User user){
        if (listUsersSelected.contains(user)){
            holder.imgCheck.setImageResource(R.drawable.ic_img_user_default);
            listUsersSelected.remove(user);
        }else{
            holder.imgCheck.setImageResource(R.drawable.ic_check_circle);
            listUsersSelected.add(user);
        }

        if (listUsersSelected.size()==0){
            actionMode.finish();
        }
        if (actionMode!=null){
            actionMode.setTitle(listUsersSelected.size()+"");
        }
    }

    private void clearListSelected(){
        listUsersSelected.clear();
        //Use diffutil with payload to refresh list without element selected
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

    public void addListUsers(List<User> newList) {
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new DiffUtilsCallbackUser(userList,newList));
        userList.clear();
        userList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class OwnViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtItemUserName)
        TextView txtItemUserTitle;
        @BindView(R.id.txtItemUserDescription)
        TextView txtItemUserContent;
        @BindView(R.id.imgCheck)
        ImageView imgCheck;
        private User user;
        View view;

        public OwnViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view=itemView;
        }

        private void bindData(User user) {
            this.user=user;
            txtItemUserTitle.setText(user.getName());
            txtItemUserContent.setText(user.getDescription());
        }
    }

    private class ActionModeCallback implements android.view.ActionMode.Callback {
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
                userViewModel.deleteListUsers(listUsersSelected);
                //listUsersSelected.clear();
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode=null;
            selectionEnabled=false;
            clearListSelected();
        }
    }
}
