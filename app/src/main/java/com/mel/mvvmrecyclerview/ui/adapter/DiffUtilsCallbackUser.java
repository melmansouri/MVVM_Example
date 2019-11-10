package com.mel.mvvmrecyclerview.ui.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.mel.mvvmrecyclerview.model.User;

import java.util.List;

public class DiffUtilsCallbackUser extends DiffUtil.Callback {
    private List<User> oldList;
    private List<User> newList;

    public DiffUtilsCallbackUser(List<User> oldList, List<User> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    /**
     *  Always check unique ids
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()==newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        User oldUser =oldList.get(oldItemPosition);
        User newUser =newList.get(newItemPosition);
        return oldUser.getName().equals(newUser.getName()) && oldUser.getDescription().equals(newUser.getDescription());
    }

    /**
     * Called when areItemTheSame return true and areContentsTheSame return false
     * Used to get what's the difference between old a new data
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
