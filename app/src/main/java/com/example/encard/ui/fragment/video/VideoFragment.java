package com.example.encard.ui.fragment.video;

import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.encard.base.BaseFragment;
import com.example.encard.databinding.FragmentVideoBinding;
import com.example.encard.ui.dialog.AddVideoFragment;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.TracksInfo;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AuxEffectInfo;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.exoplayer2.video.spherical.CameraMotionListener;

import java.util.List;


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