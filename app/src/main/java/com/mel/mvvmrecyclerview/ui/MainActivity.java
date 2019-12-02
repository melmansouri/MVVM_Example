package com.mel.mvvmrecyclerview.ui;

import android.app.Dialog;
import android.os.Bundle;
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
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UserRepository userRepository = new UserRepository(this, new UserMapper());
        OwnViewModelFactory ownViewModelFactory = new OwnViewModelFactory(userRepository);
        userViewModel = new ViewModelProvider(this, ownViewModelFactory).get(UserViewModel.class);
        fragmentManager=getSupportFragmentManager();
        addFragment();
    }

    private void addFragment(){
        ListUserFragment fragment=new ListUserFragment();
        fragmentManager.beginTransaction().add(R.id.container,fragment).commit();
    }

    @OnClick(R.id.fabAddUser)
    public void createNewUser(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment=fragmentManager.findFragmentByTag("dialog");
        if (fragment!=null){
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.addToBackStack(null);
        DialogAddUserFragment dialogAddUserFragment=new DialogAddUserFragment();
        dialogAddUserFragment.show(fragmentTransaction,"dialog");
    }

}
