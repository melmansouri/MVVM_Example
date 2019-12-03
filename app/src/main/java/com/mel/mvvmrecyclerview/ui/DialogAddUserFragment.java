package com.mel.mvvmrecyclerview.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mel.mvvmrecyclerview.R;
import com.mel.mvvmrecyclerview.model.User;
import com.mel.mvvmrecyclerview.repository.UserMapper;
import com.mel.mvvmrecyclerview.repository.UserRepository;
import com.mel.mvvmrecyclerview.vm.OwnViewModelFactory;
import com.mel.mvvmrecyclerview.vm.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogAddUserFragment extends DialogFragment {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtDescription)
    EditText edtDescription;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    private UserViewModel userViewModel;
    private ViewModelProvider.Factory ownViewModelFactory;
    private UserRepository userRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRepository = new UserRepository(getContext(), new UserMapper());
        ownViewModelFactory = new OwnViewModelFactory(userRepository);
        userViewModel = new ViewModelProvider(requireActivity(), ownViewModelFactory).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_fragment_add_user, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel.getCheckDataSingleEvent().observe(getViewLifecycleOwner(), s -> Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show());

        userViewModel.getSingleLiveEventAllOperationCorrect().observe(getViewLifecycleOwner(), o -> getDialog().dismiss());
    }

    @OnClick(R.id.btnAdd)
    public void addNewUser(){
        User user = new User();
        user.setName(edtName.getText().toString());
        user.setDescription(edtDescription.getText().toString());
        userViewModel.insertUser(user);
    }

    @OnClick(R.id.btnCancel)
    public void cancel(){
        getDialog().dismiss();
    }
}
