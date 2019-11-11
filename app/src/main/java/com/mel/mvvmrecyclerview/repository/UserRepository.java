package com.mel.mvvmrecyclerview.repository;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.mel.mvvmrecyclerview.datasource.local.UserDAO;
import com.mel.mvvmrecyclerview.datasource.local.UserDataBase;
import com.mel.mvvmrecyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserDAO userDAO;
    private UserMapper userMapper;
    protected MutableLiveData<List<User>> listMutableLiveData;
    public UserRepository(Context context, UserMapper userMapper){
        userDAO = UserDataBase.getDatabase(context).taskDAO();
        this.userMapper = userMapper;
        listMutableLiveData=new MutableLiveData<>();
    }

    public void insertTask(User user){
        new InsertTaskAsyncTask(userDAO, userMapper){
            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                List<User> taskListCloneMoreTheNewUser =new ArrayList<>();
                taskListCloneMoreTheNewUser.add(user);
                List<User> listaActualUsers =listMutableLiveData.getValue();
                for (User userLoop :
                        listaActualUsers) {
                    taskListCloneMoreTheNewUser.add(userLoop);
                }
                listMutableLiveData.setValue(taskListCloneMoreTheNewUser);
            }
        }.execute(user);
    }

    public void insertListTasks(List<User> userList){
        new InsertListTasksAsyncTask (userDAO, userMapper){
            @Override
            protected void onPostExecute(List<User> userList) {
                super.onPostExecute(userList);
                List<User> taskListCloneMoreTheNewUser =new ArrayList<>();
                List<User> listaActualUsers =listMutableLiveData.getValue();
                for (User userLoop :
                        listaActualUsers) {
                    taskListCloneMoreTheNewUser.add(userLoop);
                }
                taskListCloneMoreTheNewUser.addAll(userList);
                listMutableLiveData.setValue(taskListCloneMoreTheNewUser);
            }
        }.execute(userList);
    }

    public void deleteTask(User user){
    }

    public MutableLiveData<List<User>> getAllTasks(){
        new SelectTasksAsyncTask(userDAO, userMapper){
            @Override
            protected void onPostExecute(List<User> userList) {
                super.onPostExecute(userList);
                listMutableLiveData.setValue(userList);
            }
        }.execute();
        return listMutableLiveData;
    }

    private static class InsertListTasksAsyncTask extends AsyncTask<List<User>,Void,List<User>>{
        private UserDAO userDAO;
        private UserMapper userMapper;
        public InsertListTasksAsyncTask(UserDAO userDAO, UserMapper userMapper){
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }
        @Override
        protected List<User> doInBackground(List<User>... lists) {
            List<User> userList =lists[0];
            List<Long> ids= userDAO.insertAll(userMapper.convertTaskListToTaskEntityList(userList));
            int sizeIds=ids.size();
            for (int i=0;i<sizeIds;i++){
                userList.get(i).setId((ids.get(i)));
            }
            return userList;
        }
    }

    private static class InsertTaskAsyncTask extends AsyncTask<User,Void, User>{
        private UserDAO userDAO;
        private UserMapper userMapper;
        public InsertTaskAsyncTask(UserDAO userDAO, UserMapper userMapper){
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }
        @Override
        protected User doInBackground(User... params) {
            User user =params[0];
            long id= userDAO.insert(userMapper.convertTaskToTAskEntity(user));
            user.setId(id);
            return user;
        }
    }

    private static class SelectTasksAsyncTask extends AsyncTask<Void,Void,List<User>>{
        private UserDAO userDAO;
        private UserMapper userMapper;
        public SelectTasksAsyncTask(UserDAO userDAO, UserMapper userMapper){
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }
        @Override
        protected List<User> doInBackground(Void... voids) {

            return userMapper.convertTaskEntityListToTaskList(userDAO.getALlTasks());
        }
    }
}
