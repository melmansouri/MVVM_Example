package com.mel.mvvmrecyclerview.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.repository.UserMapper;
import com.mel.mvvmrecyclerview.repository.UserRepository;
import com.mel.mvvmrecyclerview.vm.OwnViewModelFactory;
import com.mel.mvvmrecyclerview.vm.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout container;
    private FragmentManager fragmentManager;

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MiFragmento","->onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MiFragmento","->onCreate");
        ButterKnife.bind(this);
        fragmentManager=getSupportFragmentManager();
        addFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MiFragmento","->onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MiFragmento","->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MiFragmento","->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MiFragmento","->onDestroy");
    }

    private void addFragment(){
        ListUserFragment fragment=new ListUserFragment();
        fragmentManager.beginTransaction().replace(R.id.container,fragment).commit();
    }

    @OnClick(R.id.fabAddUser)
    public void createNewUser(){
        /*FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment=fragmentManager.findFragmentByTag("dialog");
        if (fragment!=null){
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.addToBackStack(null);*/
        DialogAddUserFragment dialogAddUserFragment=new DialogAddUserFragment();
        dialogAddUserFragment.show(getSupportFragmentManager(),"dialog");
    }

}
