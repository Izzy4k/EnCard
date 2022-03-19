package com.example.encard.ui.bottom_sheet_dialog.translate;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.encard.ui.base.BaseBottomSheetDialogFragment;
import com.example.encard.databinding.FragmentAddTranslateBinding;

import java.util.Timer;
import java.util.TimerTask;

public class AddTranslateFragment extends BaseBottomSheetDialogFragment<FragmentAddTranslateBinding> {
    private final Result result;
    private Timer timer = new Timer();
    private final long INTERVAL = 2000;

    public AddTranslateFragment(Result result) {
        this.result = result;
    }

    @Override
    protected FragmentAddTranslateBinding getBinding() {
        return FragmentAddTranslateBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initListener();
    }

    private void initListener() {
        binding.editTranslate.addTextChangedListener(new TextWatcher() {
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
                        String word = binding.editTranslate.getText().toString();
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