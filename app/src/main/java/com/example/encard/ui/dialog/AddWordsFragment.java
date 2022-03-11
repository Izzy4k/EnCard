package com.example.encard.ui.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.base.BaseBottomSheetDialogFragment;
import com.example.encard.databinding.FragmentAddWordsBinding;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {

    private final Result result;
    private final String EMPTY = "";
    private Timer timer = new Timer();
    private final long INTERVAL = 2000;

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

        binding.editWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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