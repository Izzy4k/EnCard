package com.example.encard.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.base.BaseBottomSheetDialogFragment;
import com.example.encard.databinding.FragmentAddVideoBinding;


public class AddVideoFragment extends BaseBottomSheetDialogFragment<FragmentAddVideoBinding> {
    private final Result result;
    private final String EMPTY = "";

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
        binding.btnCreateVideo.setOnClickListener(view -> {
            String word = binding.editVideo.getText().toString().trim();
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