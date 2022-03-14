package com.example.encard.ui.fragment.card;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.encard.R;
import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentCardBinding;


public class CardFragment extends BaseFragment<FragmentCardBinding> {
    private float xDelta ;
    private float yDelta ;
    private final int  threshold = 100;
    private final int velocity_threshold = 100;
    private GestureDetector gestureDetector;

    @Override
    protected FragmentCardBinding getBinding() {
        return FragmentCardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTouchListener();
        initView();
        initGestureListener();
    }

    private void initGestureListener() {
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.
                SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                float xDiff = e2.getX() - e1.getX();
                float yDiff = e2.getY() - e2.getY();
                try {
                    if (Math.abs(xDiff) > Math.abs(yDiff)) {
                        if (Math.abs(xDiff) > threshold &&
                                Math.abs(velocityX) > velocity_threshold) {
                            if (xDiff > 0) {
                                Toast.makeText(requireContext(), "Свайп вправо",
                                        Toast.LENGTH_LONG)
                                        .show();
                            } else {
                                Toast.makeText(requireContext(), "Свайп влево",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        gestureDetector = new GestureDetector(listener);
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
            return gestureDetector.onTouchEvent(motionEvent);
        });
    }
}