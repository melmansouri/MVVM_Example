package com.mel.mvvmrecyclerview.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
        Log.d("MiFragmento","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MiFragmento","onCreateView");
        View view = inflater.inflate(R.layout.fragment_list_user, container,false);
        ButterKnife.bind(this,view);
        initRecyclerList();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("MiFragmento","onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("MiFragmento","onActivityCreated");
        //Le pasamos getViewLifecycleOwner para observar el ciclo de vida de la vista del fragment y  asi cuando se destruya la vista  tambien que se elimine el observador
        //Ya que tras el ondestroyview volvera a entrar a onActivitycreated  y volvera a crear el observer
        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> newList) {
                Log.d("FragmentV","Changed liveData");
                adapter.addListUsers(newList);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("MiFragmento","onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MiFragmento","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MiFragmento","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MiFragmento","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MiFragmento","onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MiFragmento","onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MiFragmento","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MiFragmento","onDestroy");
    }

    private void initRecyclerList() {
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter = new UserAdapter(userList,userViewModel);
        recyclerViewUser.setAdapter(adapter);
    }

}
