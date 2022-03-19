package com.example.encard.ui.dialog.dialog_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;


import com.bumptech.glide.Glide;
import com.example.encard.R;
import com.example.encard.databinding.DialogPictureBinding;
import com.example.encard.ui.fragment.word.adapter.WordAdapter;


public class DialogFull {
    private final Dialog dialog;
    private final DialogPictureBinding binding;


    public DialogFull(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_picture, null);
        builder.setView(view);
        dialog = builder.create();
        binding = DialogPictureBinding.bind(view);
    }

    public void open(String image, String word) {
        Glide.with(binding.imageFull).load(image).into(binding.imageFull);
        binding.dialogTxtWord.setText(word);
        initBtn();
        show();
    }

    private void initBtn() {
        binding.btnClosed.setOnClickListener(view -> dismiss());
    }

    public void show() {
        dialog.show();
    }


    public void dismiss() {
        dialog.dismiss();
    }


}

