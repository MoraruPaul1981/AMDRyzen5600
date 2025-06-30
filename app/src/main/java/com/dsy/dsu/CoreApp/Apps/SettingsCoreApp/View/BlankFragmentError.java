package com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.View;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.ViewModel.BlankFragmentErrorViewModel;
import com.dsy.dsu.R;

public class BlankFragmentError extends Fragment {

    private BlankFragmentErrorViewModel mViewModel;

    public static BlankFragmentError newInstance() {
        return new BlankFragmentError();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank_fragment_error, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlankFragmentErrorViewModel.class);
        // TODO: Use the viewmodel
    }

}