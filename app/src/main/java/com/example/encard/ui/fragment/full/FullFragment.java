package com.example.encard.ui.fragment.full;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentFullBinding;
import com.example.encard.utils.KeyString;

public class FullFragment extends BaseFragment<FragmentFullBinding> {

    @Override
    protected FragmentFullBinding getBinding() {
        return FragmentFullBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initArguments();
    }

    private void initArguments() {
        if (getArguments() != null) {
            String image = getArguments().getString(KeyString.IMAGE);
            String title = getArguments().getString(KeyString.TITLE);
            initView(image, title);
        }
    }

    private void initView(String image, String title) {
        Glide.with(binding.imageFull).load(image).into(binding.imageFull);
        binding.txtFull.setText(title);
    }
}