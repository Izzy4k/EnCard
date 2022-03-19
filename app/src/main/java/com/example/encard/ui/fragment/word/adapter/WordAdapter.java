package com.example.encard.ui.fragment.word.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.encard.databinding.ItemWordBinding;
import com.example.encard.domain.model.word.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<WordEntity> list = new ArrayList<>();
    private final Result result;

    public WordAdapter(Result result) {
        this.result = result;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<WordEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<WordEntity> getList() {
        return list;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWordBinding binding = ItemWordBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.onBind(list.get(position).getWord(), list.get(position).getImage());
        holder.itemView.setOnClickListener(view ->
                result.openDialog(list.get(position).getImage(),list.get(position).getWord()));
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


        public void onBind(String word, String image) {
            Glide.with(binding.itemImage).load(image).into(binding.itemImage);
            binding.itemTxtTitle.setText(word);
        }
    }

    public interface Result {
        void openDialog(String image, String title);
    }
}
