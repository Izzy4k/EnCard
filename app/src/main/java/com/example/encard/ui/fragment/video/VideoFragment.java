package com.example.encard.ui.fragment.video;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentVideoBinding;
import com.example.encard.ui.dialog.AddVideoFragment;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;


public class VideoFragment extends BaseFragment<FragmentVideoBinding> implements AddVideoFragment.Result {
    private VideoViewModel videoViewModel;
    private final String OROZBEK = "Orozbek";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
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
        if (videoViewModel.getPixaBoyVideoMutableLiveData() != null) {
            videoViewModel.getPixaBoyVideoMutableLiveData().observe(getViewLifecycleOwner()
                    , pixaBoyVideo ->
                            setupVideoView(pixaBoyVideo.getHits()
                                    .get(2).getVideos().getSmall().getUrl()));
        }
    }

    private void setupVideoView(String url) {
        ExoPlayer player = new ExoPlayer.Builder(requireContext()).build();
        binding.videoCont.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();


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