package com.example.daggermvvmex.data.rest;

import com.example.daggermvvmex.data.model.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RepoRepository {

    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Movie>> getRepositories() {
        return repoService.getartistData();
    }


}