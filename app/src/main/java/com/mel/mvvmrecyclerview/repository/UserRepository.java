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

    public void insertUser(User user){
        new InsertUserAsyncUser(userDAO, userMapper){
            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                List<User> taskListCloneMoreTheNewUser =new ArrayList<>();
                List<User> listaActualUsers =listMutableLiveData.getValue();
                for (User userLoop :
                        listaActualUsers) {
                    taskListCloneMoreTheNewUser.add(userLoop);
                }
                taskListCloneMoreTheNewUser.add(user);
                listMutableLiveData.setValue(taskListCloneMoreTheNewUser);
            }
        }.execute(user);
    }

    public void insertListUsers(List<User> userList){
        new InsertListUsersAsyncUser (userDAO, userMapper){
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

    public MutableLiveData<List<User>> getAllUsers(){
        new SelectUsersAsyncUser(userDAO, userMapper){
            @Override
            protected void onPostExecute(List<User> userList) {
                super.onPostExecute(userList);
                listMutableLiveData.setValue(userList);
            }
        }.execute();
        return listMutableLiveData;
    }

    public void deleteListUsers(List<User> listUsers) {
        new DeleteUsersAsyncTask(userDAO,userMapper){
            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                List<User> listUsersBeforeDeleteAction=listMutableLiveData.getValue();
                List<User> newList=new ArrayList<>();
                listUsersBeforeDeleteAction.removeAll(users);
                newList.addAll(listUsersBeforeDeleteAction);
                listMutableLiveData.setValue(newList);
            }
        }.execute(listUsers);
    }

    private static class InsertListUsersAsyncUser extends AsyncTask<List<User>,Void,List<User>> {
        private UserDAO userDAO;
        private UserMapper userMapper;
        public InsertListUsersAsyncUser(UserDAO userDAO, UserMapper userMapper){
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }
        @Override
        protected List<User> doInBackground(List<User>... lists) {
            List<User> userList =lists[0];
            List<Long> ids= userDAO.insertAll(userMapper.convertUserListToUserEntityList(userList));
            int sizeIds=ids.size();
            for (int i=0;i<sizeIds;i++){
                userList.get(i).setId((ids.get(i)));
            }
            return userList;
        }
    }

    private static class InsertUserAsyncUser extends AsyncTask<User,Void, User>{
        private UserDAO userDAO;
        private UserMapper userMapper;
        public InsertUserAsyncUser(UserDAO userDAO, UserMapper userMapper){
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }
        @Override
        protected User doInBackground(User... params) {
            User user =params[0];
            long id= userDAO.insert(userMapper.convertUserToTAskEntity(user));
            user.setId(id);
            return user;
        }
    }

    private static class SelectUsersAsyncUser extends AsyncTask<Void,Void,List<User>>{
        private UserDAO userDAO;
        private UserMapper userMapper;
        public SelectUsersAsyncUser(UserDAO userDAO, UserMapper userMapper){
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }
        @Override
        protected List<User> doInBackground(Void... voids) {

            return userMapper.convertUserEntityListToUserList(userDAO.getALlUsers());
        }
    }

    private static class DeleteUsersAsyncTask extends AsyncTask<List<User>,Void,List<User>>{
        private UserDAO userDAO;
        private UserMapper userMapper;

        public DeleteUsersAsyncTask(UserDAO userDAO, UserMapper userMapper) {
            this.userDAO = userDAO;
            this.userMapper = userMapper;
        }

        @Override
        protected List<User> doInBackground(List<User>... lists) {
            List<User> listUsersDeleted=lists[0];
            userDAO.deleteListUsers(userMapper.convertUserListToUserEntityList(listUsersDeleted));
            return listUsersDeleted;
        }
    }
}
