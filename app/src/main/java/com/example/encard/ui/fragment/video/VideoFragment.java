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
public class VideoFragment extends BaseFragment<FragmentVideoBinding> implements
        AddVideoFragment.Result, VideoViewModel.Exception {
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
        initViewModel();
        initListener();
    }

    private void initViewModel() {
        videoViewModel.setException(this);
    }

    private void initListener() {
        binding.rvVideo.setAdapter(videoAdapter);
        videoViewModel.getPixaBoyVideoMutableLiveData().observe(getViewLifecycleOwner()
                , pixaBoyVideo -> videoAdapter.setList(pixaBoyVideo.getHits()));
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

    @Override
    public void errorVideo() {
        Toast.makeText(requireContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();
    }
}