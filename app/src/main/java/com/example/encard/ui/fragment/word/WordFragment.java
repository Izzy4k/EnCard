package com.example.encard.ui.fragment.word;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.encard.R;
import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentWordBinding;
import com.example.encard.ui.bottom_sheet_dialog.word.AddWordsFragment;
import com.example.encard.ui.dialog.dialog_list.DialogList;
import com.example.encard.utils.KeyString;


public class WordFragment extends BaseFragment<FragmentWordBinding> implements AddWordsFragment.Result,
        DialogList.Result {
    private WordViewModel wordViewModel;
    private final String AZA = "Aza";
    private DialogList dialogList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        dialogList = new DialogList(requireActivity(), this);
    }

    @Override
    protected FragmentWordBinding getBinding() {
        return FragmentWordBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBtn();
        initListener();
    }

    private void initListener() {
        if (wordViewModel.getResponseMutableLiveData() != null) {
            wordViewModel.getResponseMutableLiveData().observe(getViewLifecycleOwner()
                    , pixaBayResponse -> dialogList.init(pixaBayResponse.getHits()));
        }
    }

    private void initBtn() {
        binding.btnNewWord.setOnClickListener(view ->
                new AddWordsFragment(this).show(requireActivity()
                        .getSupportFragmentManager(), AZA));
    }

    @Override
    public void putWord(String word) {
        wordViewModel.init(word);
    }

    @Override
    public void transfer(String image, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(KeyString.IMAGE, image);
        bundle.putString(KeyString.TITLE, title);
        navigate(R.id.action_wordFragment_to_fullFragment, bundle);
    }


}
