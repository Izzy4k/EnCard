package com.example.encard.ui.dialog.dialog_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Handler;
import android.view.View;


import com.example.encard.R;
import com.example.encard.databinding.DialogPictureBinding;
import com.example.encard.domain.model.Image.entity.Hit;
import com.example.encard.ui.fragment.word.adapter.WordAdapter;

import java.util.List;

public class DialogList implements WordAdapter.Result {
    private final Dialog dialog;
    private final WordAdapter wordAdapter;
    private final DialogPictureBinding binding;
    private final Result result;
    private int page = 1;

    public DialogList(Activity activity, Result result) {
        this.result = result;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_picture, null);
        builder.setView(view);
        dialog = builder.create();
        wordAdapter = new WordAdapter(this);
        binding = DialogPictureBinding.bind(view);
    }

    public void init(List<Hit> list) {
        wordAdapter.setList(list);
        binding.rvDialog.setAdapter(wordAdapter);
        show();
        initSwipeListener();
    }

    private void initSwipeListener() {
        binding.dialogSwipeRefresh.setOnRefreshListener(() ->
                new Handler().postDelayed(()
                        -> {
                    result.slide(++page);
                    binding.dialogSwipeRefresh.setRefreshing(false);
                }, 1000));
        binding.dialogSwipeRefresh.setColorSchemeResources(R.color.black,
                R.color.white, R.color.blue);
    }

    public void show() {
        dialog.show();
    }

    @Override
    public void transaction(String image, String title) {
        result.transfer(image, title);
        dismiss();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public interface Result {
        void transfer(String image, String title);

        void slide(int page);
    }
}

