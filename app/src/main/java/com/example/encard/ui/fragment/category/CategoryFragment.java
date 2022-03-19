package com.example.encard.ui.fragment.category;


import androidx.navigation.NavDirections;

import com.example.encard.databinding.FragmentCategoryBinding;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.ui.bottom_sheet_dialog.category.AddCategoryFragment;
import com.example.encard.ui.fragment.category.adapter.CategoryAdapter;
import com.example.encard.utils.EndPoints;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryFragment extends BaseFragment<FragmentCategoryBinding>
        implements AddCategoryFragment.Result, CategoryAdapter.Result {
    private CategoryAdapter categoryAdapter;
    @Inject
    public CategoryViewModel categoryViewModel;

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
    }

    private void initBtn() {
        binding.btnAddCategory.setOnClickListener(v ->
                new AddCategoryFragment(this).show(requireActivity().getSupportFragmentManager(),
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
}