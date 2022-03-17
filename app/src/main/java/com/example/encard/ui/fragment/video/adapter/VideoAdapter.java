package com.example.encard.ui.fragment.video.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encard.databinding.ItemVideoBinding;
import com.example.encard.domain.model.video.entity.HitVideo;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<HitVideo> list = new ArrayList<>();
    private final Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<HitVideo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding binding = ItemVideoBinding.inflate(LayoutInflater.from(context));
        return new VideoViewHolder(binding, context);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.onBind(list.get(position).getVideos().getSmall().getUrl(),
                list.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        ItemVideoBinding binding;
        Context context;

        public VideoViewHolder(@NonNull ItemVideoBinding binding, Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.context = context;
        }

        public void onBind(String url, String user) {
            ExoPlayer player = new ExoPlayer.Builder(context).build();
            binding.itemVideoPlayer.setPlayer(player);
            MediaItem mediaItem = MediaItem.fromUri(url);
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
            binding.itemTxtTitleVideo.setText(user);
        }
    }
}
