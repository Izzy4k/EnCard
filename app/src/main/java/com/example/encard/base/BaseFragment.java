package com.example.encard.base;

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

   protected   abstract VB getBinding();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getBinding();
        return binding.getRoot();
    }
    protected void navigate(int id, Bundle bundle) {
        NavController controller = Navigation.findNavController
                (requireActivity(), R.id.fragment_container);
        controller.navigate(id, bundle);
    }

    protected void navigate(int id) {
        NavController controller = Navigation.findNavController
                (requireActivity(), R.id.fragment_container);
        controller.navigate(id);
    }
}
