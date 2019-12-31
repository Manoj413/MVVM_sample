package com.example.daggermvvmex.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggermvvmex.R;
import com.example.daggermvvmex.base.BaseFragment;
import com.example.daggermvvmex.data.model.Movie;
import com.example.daggermvvmex.di.module.ViewModelModule;
import com.example.daggermvvmex.util.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ListFragment extends BaseFragment implements RepoSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
   /* @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;*/

    @Inject
    ViewModelFactory viewModelFactory;

   private ListViewModel viewModel;
   RepoListAdapter repoListAdapter;
   RecyclerView.LayoutManager layoutManager;


    @Override
    protected int layoutRes() {
        return R.layout.lay_list_fragment;
    }

   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);

        viewModel.getRepos().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                Log.e("MOVIES","MOVIES = "+movies.toString());
                if (movies != null) {

                    setAdapter(movies);

                }
            }
        });
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_list_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
        viewModel.getRepos().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                Log.e("MOVIES","MOVIES = "+movies.toString());
                if (movies != null) {

                    setAdapter(movies);

                }
            }
        });

        return view;
    }

    private void setAdapter(List<Movie> movies) {

        Log.e("List_inside","List_inside = "+movies.get(0).description);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        repoListAdapter=new RepoListAdapter(getContext(),movies,this);
        recyclerView.setAdapter(repoListAdapter);
    }



    @Override
    public void onRepoSelected(Movie movie) {
        DetailViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailViewModel.class);
        detailsViewModel.setSelectedRepo(movie);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new DetailFragment())
                .addToBackStack(null).commit();

    }

   /* private void observableViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if(repos != null) recyclerView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }*/
}
