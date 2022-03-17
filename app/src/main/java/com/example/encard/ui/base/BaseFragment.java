package com.example.encard.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.encard.R;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    protected VB binding;
    protected NavController controller;

    protected abstract VB getBinding();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = getBinding();
        initNavigation();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi();
        setupObservers();
    }

    private void initNavigation() {
        controller = Navigation.findNavController
                (requireActivity(), R.id.fragment_container);
    }

    protected abstract void setupUi();

    protected abstract void setupObservers();
}


