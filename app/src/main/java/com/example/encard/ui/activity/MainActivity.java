package com.example.encard.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.encard.R;
import com.example.encard.databinding.ActivityMainBinding;
import com.example.encard.data.local.utils.Pref;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController controller;
    @Inject
    public Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavigation();
        initNavigationListener();
        navigateListener();
    }

    private void navigateListener() {
        if (!pref.isBoardShow()) {
            controller.navigate(R.id.onBoardFragment);
        }
    }

    private void initNavigation() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        controller = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, controller);
    }

    private void initNavigationListener() {
        binding.bottomNavigation.setItemIconTintList(null);
        controller.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.onBoardFragment) {
                binding.bottomNavigation.setVisibility(View.GONE);
            } else {
                binding.bottomNavigation.setVisibility(View.VISIBLE);
            }
        });
    }
}