package com.mel.mvvmrecyclerview.model;

import androidx.annotation.Nullable;

public class User {
    private long id;
    private String name;
    private String description;
    private boolean checked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj==null)return false;
        if (obj==this) return true;
        if (!(obj instanceof User))return false;
        User otherUser =(User) obj;
        return id== otherUser.id && name.equals(otherUser.name) && description.equals(otherUser.description);
    }
}
