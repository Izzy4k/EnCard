package com.example.encard.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;


import com.example.encard.R;
import com.example.encard.databinding.DialogPictureBinding;
import com.example.encard.model.Image.Hit;
import com.example.encard.ui.fragment.word.adapter.WordAdapter;

import java.util.List;

public class DialogList {
    private final Dialog dialog;
    private final WordAdapter wordAdapter;
    private final DialogPictureBinding binding;

    public DialogList(Activity activity ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_picture, null);
        builder.setView(view);
        dialog = builder.create();
        wordAdapter = new WordAdapter();
        binding = DialogPictureBinding.bind(view);
    }

    public void init(List<Hit> list) {
        wordAdapter.setList(list);
        binding.rvDialog.setAdapter(wordAdapter);
        dialog.show();
    }
}
