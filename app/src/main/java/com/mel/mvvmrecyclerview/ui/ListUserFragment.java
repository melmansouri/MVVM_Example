package com.mel.mvvmrecyclerview.ui;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.model.User;
import com.mel.mvvmrecyclerview.repository.UserMapper;
import com.mel.mvvmrecyclerview.repository.UserRepository;
import com.mel.mvvmrecyclerview.ui.adapter.UserAdapter;
import com.mel.mvvmrecyclerview.vm.OwnViewModelFactory;
import com.mel.mvvmrecyclerview.vm.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListUserFragment extends Fragment/* implements android.view.ActionMode.Callback*/{

    @BindView(R.id.RecyclerViewUsers)
    RecyclerView recyclerViewUser;
    private UserViewModel userViewModel;
    private UserAdapter adapter;
    private List<User> userList;
    private OwnViewModelFactory ownViewModelFactory;
    private UserRepository userRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRepository = new UserRepository(getContext(), new UserMapper());
        ownViewModelFactory = new OwnViewModelFactory(userRepository);
        userViewModel = new ViewModelProvider(requireActivity(), ownViewModelFactory).get(UserViewModel.class);
        userList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container,false);
        ButterKnife.bind(this,view);
        initRecyclerList();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> newList) {
                adapter.addListUsers(newList);
            }
        });
    }

    private void initRecyclerList() {
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter = new UserAdapter(userList,userViewModel);
        recyclerViewUser.setAdapter(adapter);
    }

    private List<User> generateDummyList() {
        List<User> dummyList = new ArrayList<>();
        String[] titles = {"John", "Manuel", "Melissa", "Ana","Amy","MachoMen69"};
        String[] contents = {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede."
                , "Donec nec justo eget felis facilisis fermentum. Aliquam porttitor mauris sit amet orci. Aenean dignissim pellentesque felis"
                , "Morbi in sem quis dui placerat ornare. Pellentesque odio nisi, euismod in, pharetra a, ultricies in, diam. Sed arcu. Cras consequat."
                , "Praesent dapibus, neque id cursus faucibus, tortor neque egestas auguae, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus"
                , "Phasellus ultrices nulla quis nibh. Quisque a lectus. Donec consectetuer ligula vulputate sem tristique cursus. Nam nulla quam, gravida non, commodo a, sodales sit amet, nisi"};
        Random random = new Random();
        int sizeTitles = titles.length;
        int sizeContents = contents.length;
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setName(titles[random.nextInt(sizeTitles)]);
            user.setDescription(contents[random.nextInt(sizeContents)]);
            dummyList.add(user);
        }
        return dummyList;
    }

    @OnClick(R.id.fabAddUser)
    public void createNewUser(){

    }

}
