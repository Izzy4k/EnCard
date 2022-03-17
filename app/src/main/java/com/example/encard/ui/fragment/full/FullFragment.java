package com.example.encard.ui.fragment.full;


import com.bumptech.glide.Glide;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.databinding.FragmentFullBinding;
import com.example.encard.utils.KeyString;

public class FullFragment extends BaseFragment<FragmentFullBinding> {

    @Override
    protected FragmentFullBinding getBinding() {
        return FragmentFullBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initArguments();
    }

    @Override
    protected void setupObservers() {

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