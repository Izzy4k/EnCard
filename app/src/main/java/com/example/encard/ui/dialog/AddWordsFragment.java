package com.example.encard.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.base.BaseBottomSheetDialogFragment;
import com.example.encard.databinding.FragmentAddWordsBinding;


public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {

    private final Result result;
    private final String EMPTY = "";

    public AddWordsFragment(Result result) {
        this.result = result;
    }

    @Override
    public FragmentAddWordsBinding getBinding() {
        return FragmentAddWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.btnCreate.setOnClickListener(view -> {
            String word = binding.editWord.getText().toString().trim();
            if (!word.equals(EMPTY)) {
                result.putWord(word);
                dismiss();
            } else {
                Toast.makeText(requireContext(), "Пусто", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface Result {
        void putWord(String word);
    }
}