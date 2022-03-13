package com.example.encard.ui.bottom_sheet_dialog.video;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.base.BaseBottomSheetDialogFragment;
import com.example.encard.databinding.FragmentAddVideoBinding;

import java.util.Timer;
import java.util.TimerTask;


public class AddVideoFragment extends BaseBottomSheetDialogFragment<FragmentAddVideoBinding> {
    private final Result result;
    private Timer timer = new Timer();
    private final long INTERVAL = 2000;

    public AddVideoFragment(Result result) {
        this.result = result;
    }

    @Override
    protected FragmentAddVideoBinding getBinding() {
        return FragmentAddVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.editVideo.addTextChangedListener(new TextWatcher() {
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
                        String word = binding.editVideo.getText().toString();
                        if (!word.isEmpty()) {
                            result.putWord(word);
                            dismiss();
                        }
                    }
                }, INTERVAL);
            }
        });
    }

    public interface Result {
        void putWord(String word);
    }
}