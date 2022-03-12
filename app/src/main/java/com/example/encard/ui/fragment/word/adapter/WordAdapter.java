package com.example.encard.ui.fragment.word.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.encard.databinding.ItemWordBinding;
import com.example.encard.model.Image.Hit;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Hit> list = new ArrayList<>();

    public void setList(List<Hit> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWordBinding binding = ItemWordBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.onBind(list.get(position).getLargeImageURL() , list.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        ItemWordBinding binding;

        public WordViewHolder(@NonNull ItemWordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(String image , String title) {
            Glide.with(binding.itemImage).load(image).into(binding.itemImage);
            binding.itemTxtTitle.setText(title);
        }
    }
}
