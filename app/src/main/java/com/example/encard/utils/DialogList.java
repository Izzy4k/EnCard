package com.example.encard.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.encard.R;
import com.example.encard.model.Image.Hit;
import com.example.encard.ui.fragment.word.adapter.WordAdapter;

import java.util.List;

public class DialogList {
    private final Dialog dialog;
    private final WordAdapter wordAdapter;
    private final View view;

    public DialogList(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        view = activity.getLayoutInflater().inflate(R.layout.dialog_picture,null);
        builder.setView(view);
        dialog = builder.create();
        wordAdapter = new WordAdapter();
    }

    public void init(List<Hit> list) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_dialog);
        wordAdapter.setList(list);
        recyclerView.setAdapter(wordAdapter);
        dialog.show();
    }
}
