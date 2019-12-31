package com.example.daggermvvmex.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.daggermvvmex.R;
import com.example.daggermvvmex.base.BaseFragment;
import com.example.daggermvvmex.util.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailFragment extends BaseFragment {

    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @Inject
    ViewModelFactory viewModelFactory;
    private DetailViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.lay_detail_adapter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailViewModel.class);
        detailsViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_detail_adapter, container, false);

        textView1 = view.findViewById(R.id.textView1);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);


        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailViewModel.class);
        detailsViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    private void displayRepo() {
        detailsViewModel.getSelectedRepo().observe(this, repo -> {
            if (repo != null) {
                textView1.setText(repo.name);
                textView2.setText(repo.description);
                textView3.setText(String.valueOf(repo.forks));
              //  starsTextView.setText(String.valueOf(repo.stars));
            }
        });
    }
}
