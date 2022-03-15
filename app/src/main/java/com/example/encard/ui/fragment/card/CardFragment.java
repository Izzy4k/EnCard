package com.example.encard.ui.fragment.card;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.example.encard.R;
import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentCardBinding;


public class CardFragment extends BaseFragment<FragmentCardBinding> {
    private float xDelta;
    private float yDelta;
    private boolean toast;
    private Handler handler;

    @Override
    protected FragmentCardBinding getBinding() {
        return FragmentCardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initTouchListener();
        initView();
    }

    private void initListener() {
        binding.motionCard.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
                Log.e("ABOBA", +endId + "started");
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                Log.e("ABOBA", endId + " change");
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                Log.e("ABOBA", currentId + "");
                if (currentId == R.id.end) {
                    Toast.makeText(requireContext(), "Свайп вправо", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
                Log.e("ABOBA", triggerId + " trigger");
            }
        });
    }


    private void initView() {
        binding.imageCard.setImageResource(R.drawable.ic_book_open);
        binding.txtCard.setText("Book");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initTouchListener() {
        binding.motionCard.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDelta = binding.motionCard.getX() - motionEvent.getRawX();
                    yDelta = binding.motionCard.getY() - motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("ABOBA", +motionEvent.getRawX() + xDelta + "");
                    binding.motionCard.animate().x(motionEvent.getRawX() + xDelta)
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