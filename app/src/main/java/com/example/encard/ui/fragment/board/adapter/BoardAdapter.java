package com.example.encard.ui.fragment.board.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encard.R;
import com.example.encard.databinding.ItemPagerBinding;


public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private final int[] animate = {R.raw.first, R.raw.second, R.raw.third, R.raw.fourth,R.raw.last};
    private final String[] titles = {"Категории", "Слова", "Изучение", "Личный кабинет",
    "Давайте начнём !"};
    private final String[] descriptions = {"Создавайте собственные категории для слов"
            , "Создавайте слова на английском и \nкликайте на карточку чтобы увидеть его \n" +
            "перевод и картинку для ассоциации" ,
            "Свайпайте карточку вправо если вы \n запомнили,влево если пока ещё не уверены",
            "Следите за своими ачивками и \n количеством очков опыта",""};

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPagerBinding binding = ItemPagerBinding.inflate(LayoutInflater
                .from(parent.getContext()),parent,false);
        return new BoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
            holder.onBind(animate[position],titles[position],descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return animate.length;
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
         final ItemPagerBinding binding;

        public BoardViewHolder(@NonNull ItemPagerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(int i, String title, String description) {
            binding.animateViewBoard.setAnimation(i);
            binding.txtTitleBoard.setText(title);
            binding.txtDescriptionBoard.setText(description);
        }
    }
}
