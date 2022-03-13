package com.example.encard.ui.fragment.card;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.R;
import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentCardBinding;


public class CardFragment extends BaseFragment<FragmentCardBinding> {
    private float xDelta = 0.0f;
    private float yDelta = 0.0f;

    @Override
    protected FragmentCardBinding getBinding() {
        return FragmentCardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTouchListener();
        initView();
    }

    private void initView() {
        binding.imageCard.setImageResource(R.drawable.ic_book_open);
        binding.txtCard.setText("Book");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initTouchListener() {
        binding.cardLearning.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDelta = binding.cardLearning.getX() - motionEvent.getRawX();
                    yDelta = binding.cardLearning.getY() - motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    binding.cardLearning.animate().x(motionEvent.getRawX() + xDelta)
                            .y(motionEvent.getRawY() + yDelta).setDuration(0)
                            .start();
                    break;
                default:
                    return false;
            }
            return true;
        });
    }
}