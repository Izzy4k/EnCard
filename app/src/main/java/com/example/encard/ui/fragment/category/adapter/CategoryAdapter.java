package com.example.encard.ui.fragment.category.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encard.databinding.ItemCategoryBinding;
import com.example.encard.domain.model.category.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> list = new ArrayList<>();
    private final Result result;

    public CategoryAdapter(Result result) {
        this.result = result;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Category> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Category> getList() {
        return list;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(list.get(position).getCategory());
        holder.itemView.setOnClickListener(view ->
                result.addTag(list.get(position).getCategory()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public CategoryViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(String category) {
            binding.itemTxtCategory.setText(category);
        }
    }

    public interface Result {
        void addTag(String categoryTag);
    }
}
