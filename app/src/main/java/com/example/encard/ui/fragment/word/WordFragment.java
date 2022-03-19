package com.example.encard.ui.fragment.word;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encard.domain.model.word.entity.WordEntity;
import com.example.encard.ui.base.BaseFragment;
import com.example.encard.databinding.FragmentWordBinding;
import com.example.encard.ui.bottom_sheet_dialog.word.AddWordsFragment;
import com.example.encard.ui.dialog.dialog_list.DialogFull;
import com.example.encard.ui.fragment.category.utils.SwipeToDeleteCallBack;
import com.example.encard.ui.fragment.translate.TranslateViewModel;
import com.example.encard.ui.fragment.word.adapter.WordAdapter;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WordFragment extends BaseFragment<FragmentWordBinding>
        implements AddWordsFragment.Result,
        WordViewModel.Error, WordAdapter.Result {
    @Inject
    public WordViewModel wordViewModel;
    private String categoryTag;
    private WordAdapter wordAdapter;
    private DialogFull dialogFull;
    private SwipeToDeleteCallBack swipeToDeleteCallBack;


    @Override
    protected FragmentWordBinding getBinding() {
        return FragmentWordBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUi() {
        initModel();
        initBtn();
        initAdapter();
        initDialog();
    }

    private void initDialog() {
        dialogFull = new DialogFull(requireActivity());
    }

    private void initAdapter() {
        wordAdapter = new WordAdapter(this);
        binding.rvWord.setAdapter(wordAdapter);
    }

    @Override
    protected void setupObservers() {
        initArguments();
        initListener();
        initSwipeListener();
        initTouchHelperListener();
    }

    private void initTouchHelperListener() {
        ItemTouchHelper itemTouchHelper =
                new ItemTouchHelper(swipeToDeleteCallBack);
        itemTouchHelper.attachToRecyclerView(binding.rvWord);
    }

    private void initSwipeListener() {
        swipeToDeleteCallBack = new SwipeToDeleteCallBack(requireActivity()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int pos = viewHolder.getAdapterPosition();
                final WordEntity wordEntity = wordAdapter.getList().get(pos);
                wordViewModel.delete(wordEntity);
            }
        };
    }


    private void initListener() {
        wordViewModel.getWords(categoryTag).observe(getViewLifecycleOwner(), wordEntities ->
                wordAdapter.setList(wordEntities));
    }

    private void initArguments() {
        categoryTag = WordFragmentArgs.fromBundle(getArguments()).getCategory();
    }


    private void initModel() {
        wordViewModel.setError(this);
    }


    private void initBtn() {
        binding.btnNewWord.setOnClickListener(view ->
                new AddWordsFragment(this).show(requireActivity()
                        .getSupportFragmentManager(), categoryTag));
    }

    @Override
    public void putWord(String word, int page, String categoryTag) {
        wordViewModel.init(word, page, categoryTag);
    }


    @Override
    public void nullPointer() {
        Toast.makeText(requireActivity(), "Ничего не найдено", Toast.LENGTH_LONG).show();
    }

    @Override
    public void openDialog(String image, String title) {
        dialogFull.open(image, title);
    }
}
