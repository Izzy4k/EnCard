package com.example.encard.ui.bottom_sheet_dialog.category;

import com.example.encard.databinding.FragmentAddCategoryBinding;
import com.example.encard.ui.base.BaseBottomSheetDialogFragment;


public class AddCategoryFragment extends BaseBottomSheetDialogFragment<FragmentAddCategoryBinding> {
    private final Result result;

    public AddCategoryFragment(Result result) {
        this.result = result;
    }

    @Override
    protected FragmentAddCategoryBinding getBinding() {
        return FragmentAddCategoryBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initBtn();
    }

    private void initBtn() {
        binding.categoryBtn.setOnClickListener(view -> {
            String word = binding.editCategory.getText().toString();
            if (!word.isEmpty()) {
                result.transaction(word);
                dismiss();
            }
        });
    }

    public interface Result {
        void transaction(String word);
    }
}