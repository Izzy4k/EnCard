package com.example.encard.ui.fragment.video;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentVideoBinding;
import com.example.encard.ui.bottom_sheet_dialog.video.AddVideoFragment;
import com.example.encard.ui.fragment.video.adapter.VideoAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VideoFragment extends BaseFragment<FragmentVideoBinding> implements AddVideoFragment.Result {
    @Inject
    public VideoViewModel videoViewModel;
    private final String OROZBEK = "Orozbek";
    private VideoAdapter videoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoAdapter = new VideoAdapter(requireContext());
    }

    @Override
    protected FragmentVideoBinding getBinding() {
        return FragmentVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBtn();
        initListener();
    }

    private void initListener() {
        String NOT_FOUND = "Ничего не найдено";
        binding.rvVideo.setAdapter(videoAdapter);
        if (videoViewModel.getPixaBoyVideoMutableLiveData() != null) {
            videoViewModel.getPixaBoyVideoMutableLiveData().observe(getViewLifecycleOwner()
                    , pixaBoyVideo -> videoAdapter.setList(pixaBoyVideo.getHits()));
        } else {
            Toast.makeText(requireContext(), NOT_FOUND, Toast.LENGTH_LONG).show();
        }
    }


    private void initBtn() {
        binding.btnNewVideo.setOnClickListener(view ->
                new AddVideoFragment(this)
                        .show(requireActivity().getSupportFragmentManager(), OROZBEK));
    }

    @Override
    public void putWord(String word) {
        videoViewModel.init(word);
    }
}