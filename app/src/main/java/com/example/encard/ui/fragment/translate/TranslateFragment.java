package com.example.encard.ui.fragment.translate;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.databinding.FragmentTranslateBinding;
import com.example.encard.ui.bottom_sheet_dialog.translate.AddTranslateFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@AndroidEntryPoint
public class TranslateFragment extends BaseFragment<FragmentTranslateBinding> implements
        AddTranslateFragment.Result, TranslateViewModel.Exception {
    @Inject
    public TranslateViewModel translateViewModel ;
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
    protected void setupUi() {
        initModel();
        initBtn();
    }

    @Override
    protected void setupObservers() {
        initImageListener();
        initTranslateListener();
    }


    private void initImageListener() {
        translateViewModel.getPixaBayResponseMutableLiveData().observe(getViewLifecycleOwner(),
                pixaBayResponse ->
                        Glide.with(binding.imageTranslate).load(pixaBayResponse.getHits()
                                .get(0).getLargeImageURL()).into(binding.imageTranslate));
        translateViewModel.getErrorMessageImage().observe(getViewLifecycleOwner(),
                s -> Toast.makeText(requireActivity(),s,Toast.LENGTH_LONG).show());

    }

    private void initTranslateListener() {
        translateViewModel.getTranslateMutableLiveData().observe(getViewLifecycleOwner()
                , translate ->
                        binding.txtTranslate.setText(translate.getText().get(0)));
        translateViewModel.getErrorMessageTranslate().observe(getViewLifecycleOwner(),
                s -> Toast.makeText(requireActivity(), s, Toast.LENGTH_LONG).show());

    }

    private void initModel() {
        translateViewModel.setException(this);
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

    @Override
    public void errorImage() {
        Toast.makeText(requireActivity(),
                "Изоброжение не найдено!",
                Toast.LENGTH_LONG).show();
    }
}