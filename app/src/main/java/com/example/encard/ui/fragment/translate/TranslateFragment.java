package com.example.encard.ui.fragment.translate;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentTranslateBinding;
import com.example.encard.ui.bottom_sheet_dialog.translate.AddTranslateFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TranslateFragment extends BaseFragment<FragmentTranslateBinding> implements AddTranslateFragment.Result {
    @Inject
    public TranslateViewModel translateViewModel;
    private final String AZA = "Aza";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentTranslateBinding getBinding() {
        return FragmentTranslateBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBtn();
        initListener();
    }

    private void initListener() {
        if (translateViewModel.getTranslateMutableLiveData() != null) {
            translateViewModel.getTranslateMutableLiveData().observe(getViewLifecycleOwner()
                    , translate ->
                            binding.txtTranslate.setText(translate.getText().get(0)));
        }
        if (translateViewModel.getPixaBayResponseMutableLiveData() != null) {
            translateViewModel.getPixaBayResponseMutableLiveData().observe(getViewLifecycleOwner(),
                    pixaBayResponse ->
                            Glide.with(binding.imageTranslate).load(pixaBayResponse.getHits()
                                    .get(0).getLargeImageURL()).into(binding.imageTranslate));
        }
    }

    private void initBtn() {
        binding.btnNewTranslate.setOnClickListener(view ->
                new AddTranslateFragment(this).show(requireActivity().
                                getSupportFragmentManager()
                        , AZA));
    }

    @Override
    public void putWord(String word) {
        translateViewModel.initTranslate(word);
        translateViewModel.initImage(word);
    }
}