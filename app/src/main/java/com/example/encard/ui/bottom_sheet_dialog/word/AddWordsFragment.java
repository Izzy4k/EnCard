package com.example.encard.ui.bottom_sheet_dialog.word;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.ui.base.BaseBottomSheetDialogFragment;
import com.example.encard.databinding.FragmentAddWordsBinding;


import java.util.Timer;
import java.util.TimerTask;


public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {

    private final Result result;
    private Timer timer = new Timer();
    private final long INTERVAL = 2000;
    private final int FIRST = 1;

    public AddWordsFragment(Result result) {
        this.result = result;
    }

    @Override
    public FragmentAddWordsBinding getBinding() {
        return FragmentAddWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initListener();
    }

    private void initListener() {

        binding.editWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                timer.cancel();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        String word = binding.editWord.getText().toString();
                        if (!word.isEmpty()) {
                            result.putWord(word, FIRST,getTag());
                            dismiss();
                        }
                    }
                }, INTERVAL);
            }
        });
    }

    public interface Result {
        void putWord(String word, int page , String category);
    }
}