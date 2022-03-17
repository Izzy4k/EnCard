package com.example.encard.ui.fragment.board;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.encard.R;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.databinding.FragmentOnBoardBinding;
import com.example.encard.ui.fragment.board.adapter.BoardAdapter;
import com.example.encard.data.local.utils.Pref;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OnBoardFragment extends BaseFragment<FragmentOnBoardBinding> {
    @Inject
    public Pref pref;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentOnBoardBinding getBinding() {
        return FragmentOnBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initViewPager();
        initPager();
        initBtn();
        initBack();
    }

    @Override
    protected void setupObservers() {
        initTabListener();
        initListenerPager();
    }


    private void initViewPager() {
        BoardAdapter boardAdapter = new BoardAdapter();
        binding.viewPagerBoard.setAdapter(boardAdapter);
    }

    private void initTabListener() {
        binding.tabBoard.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(R.drawable.ic_ellipse_light);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(R.drawable.ic_ellipse);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initListenerPager() {
        binding.viewPagerBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 4) {
                    binding.btnFinish.setVisibility(View.VISIBLE);
                    binding.btnFinish.setEnabled(true);
                } else {
                    binding.btnFinish.setVisibility(View.GONE);
                    binding.btnFinish.setEnabled(false);
                }
            }
        });
    }

    private void initPager() {
        new TabLayoutMediator(binding.tabBoard, binding.viewPagerBoard,
                (tab, position) -> {
                    if (position == 0) tab.setIcon(R.drawable.ic_ellipse_light);
                    else tab.setIcon(R.drawable.ic_ellipse);
                    tab.view.setEnabled(false);
                }).attach();
    }

    private void initBack() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    private void initBtn() {
        binding.btnFinish.setOnClickListener(view -> {
            pref.saveState();
            controller.navigateUp();
        });
    }
}