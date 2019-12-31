package com.example.daggermvvmex.ui.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggermvvmex.data.model.Movie;
import com.example.daggermvvmex.data.rest.RepoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final RepoRepository repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Movie>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
   // private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    LiveData<List<Movie>> getRepos() {
        return repos;
    }
    LiveData<Boolean> getError() {
        return repoLoadError;
    }
   /* LiveData<Boolean> getLoading() {
        return loading;
    }*/

    private void fetchRepos() {
        //loading.setValue(true);
        disposable.add(repoRepository.getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Movie>>() {
                    @Override
                    public void onSuccess(List<Movie> value) {
                        Log.e("VALUE","VALUE  = "+value.toString());
                       // repoLoadError.setValue(false);
                        repos.setValue(value);
                        //loading.setValue(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                     //   loading.setValue(false);
                        Log.e("ERROR","ERROR  = "+e);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
