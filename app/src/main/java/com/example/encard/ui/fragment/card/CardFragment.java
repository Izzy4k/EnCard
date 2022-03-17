package com.example.encard.ui.fragment.card;

import static com.example.encard.R.id.*;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.constraintlayout.motion.widget.MotionLayout;

import com.example.encard.R;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.databinding.FragmentCardBinding;


public class CardFragment extends BaseFragment<FragmentCardBinding> {
    private float xDelta;
    private float yDelta;

    @Override
    protected FragmentCardBinding getBinding() {
        return FragmentCardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initView();
    }

    @Override
    protected void setupObservers() {
        initTouchListener();
        initListener();
    }


    private void initListener() {
        binding.motionCard.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout,
                                            int startId,
                                            int endId) {
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout,
                                           int startId,
                                           int endId,
                                           float progress) {
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout,
                                              int currentId) {
                if (currentId == end) {
                    Toast.makeText(requireContext(),
                            "Свайп вправо",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout,
                                            int triggerId,
                                            boolean positive,
                                            float progress) {
            }
        });
    }


    private void initView() {
        String book = "Book";
        binding.imageCard.setImageResource(R.drawable.ic_book_open);
        binding.txtCard.setText(book);
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