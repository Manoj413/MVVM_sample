package com.example.daggermvvmex.ui.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggermvvmex.R;
import com.example.daggermvvmex.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private RepoSelectedListener repoSelectedListener;
    private  List<Movie> data;
    Context context;

    RepoListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        Log.e("constructor_adapter","constructor_adapter");
        viewModel.getRepos().observe(lifecycleOwner, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                data.clear();
                Log.e("MOVIES","MOVIES = "+movies);
                if (movies != null) {
                    data.addAll(movies);
                    RepoListAdapter.this.notifyDataSetChanged();
                }
            }
        });
        setHasStableIds(true);
    }

    public RepoListAdapter(Context context, List<Movie> data) {
        this.repoSelectedListener = repoSelectedListener;
        this.data = data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_repo_adapter, parent, false);
        Log.e("ONcreate_adapter","ONcreate_adapter");
        return new ViewHolder(view,repoSelectedListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("ONbind_adapter","ONbind_adapter");
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position) {
        return Long.parseLong(String.valueOf(data.get(position).id));
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.artistName)
        TextView artistName;
        @BindView(R.id.movieName)
        TextView movieName;
        @BindView(R.id.cadView)
        CardView cardView;

        private Movie movie;

        ViewHolder(View itemView, final RepoSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            artistName = itemView.findViewById(R.id.artistName);
            movieName = itemView.findViewById(R.id.movieName);

           /* cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (movie != null) {
                        repoSelectedListener.onRepoSelected(movie);
                    }
                }
            });*/
        }

        void bind(Movie movie) {
            this.movie = movie;
            artistName.setText(movie.name);
            movieName.setText( movie.description);
            //forksTextView.setText(String.valueOf(repo.forks));
            //starsTextView.setText(String.valueOf(repo.stars));
        }
    }
}
