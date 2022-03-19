package com.example.encard.ui.fragment.category;


import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encard.databinding.FragmentCategoryBinding;
import com.example.encard.domain.model.category.entity.Category;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.ui.bottom_sheet_dialog.category.AddCategoryFragment;
import com.example.encard.ui.fragment.category.adapter.CategoryAdapter;
import com.example.encard.ui.fragment.category.utils.SwipeToDeleteCallBack;
import com.example.encard.utils.EndPoints;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryFragment extends BaseFragment<FragmentCategoryBinding>
        implements AddCategoryFragment.Result, CategoryAdapter.Result {
    private CategoryAdapter categoryAdapter;
    @Inject
    public CategoryViewModel categoryViewModel;
    private SwipeToDeleteCallBack swipeToDeleteCallBack;

    @Override
    protected FragmentCategoryBinding getBinding() {
        return FragmentCategoryBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initAdapter();
        initBtn();
    }

    @Override
    protected void setupObservers() {
        initListener();
        initSwipeListener();
        initTouchHelperListener();
    }

    private void initBtn() {
        binding.btnAddCategory.setOnClickListener(v ->
                new AddCategoryFragment(this).show(requireActivity()
                                .getSupportFragmentManager(),
                        EndPoints.AZA));
    }

    private void initAdapter() {
        categoryAdapter = new CategoryAdapter(this);
        binding.rvCategory.setAdapter(categoryAdapter);
    }

    private void initListener() {
        categoryViewModel.getList().observe(getViewLifecycleOwner(), categories ->
                categoryAdapter.setList(categories));
    }

    @Override
    public void transaction(String word) {
        categoryViewModel.createCategory(word);
    }

    @Override
    public void addTag(String categoryTag) {
        NavDirections action =
                CategoryFragmentDirections.actionCategoryFragmentToWordFragment()
                        .setCategory(categoryTag);
        controller.navigate(action);
    }

    private void initSwipeListener() {
        swipeToDeleteCallBack = new SwipeToDeleteCallBack(requireContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int pos = viewHolder.getAdapterPosition();
                final Category category = categoryAdapter.getList().get(pos);
                categoryViewModel.deleteCategory(category);
            }
        };
    }

    private void initTouchHelperListener() {
        ItemTouchHelper itemTouchHelper =
                new ItemTouchHelper(swipeToDeleteCallBack);
        itemTouchHelper.attachToRecyclerView(binding.rvCategory);
    }
}